package com.ydemenkov.posts.app;

import static com.google.common.truth.Truth.assertThat;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.ydemenkov.common.health.HealthCheckReport;
import com.ydemenkov.common.health.HealthCheckResponse;
import com.ydemenkov.common.tools.Flags;
import com.ydemenkov.posts.Post;
import com.ydemenkov.posts.app.database.DatabaseHealthCheck;
import com.ydemenkov.posts.app.database.SchemaUpdateHealthCheck;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Spark;

class PostsAppTest {

  @BeforeAll
  static void runServer() throws Exception {
    System.setProperty(Flags.SERVER_PORT, "12345");
    PostsApp.main(new String[]{});
    Spark.awaitInitialization();
  }

  @AfterAll
  static void killServer() {
    Spark.stop();
  }

  @Test
  void healthShouldBeFineOnStartup() throws Exception {
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:12345/api/v1/health"))
                .GET()
                .build(),
            BodyHandlers.ofString());

    assertThat(response.statusCode()).isEqualTo(HTTP_OK);
    assertThat(
        buildHealthResponse(response.body())
            .getReportsList()
            .stream()
            .map(HealthCheckReport::getCheckName)
            .collect(Collectors.toSet()))
        .containsExactly(
            DatabaseHealthCheck.DATABASE_CONNECTION_HEALTH_CHECK_NAME,
            SchemaUpdateHealthCheck.SCHEMA_UPDATE_HEALTH_CHECK_NAME);
  }

  @Test
  void postCanBeCreated() throws Exception {
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:12345/api/v1/post"))
                .POST(BodyPublishers.ofString("{\"markup\":\"test\"}"))
                .build(),
            BodyHandlers.ofString());

    Post post = buildPost(response.body());
    assertThat(post.getId()).isNotEqualTo(0);
    assertThat(post.getMarkup()).isEqualTo("test");
    assertThat(getPost(post.getId())).isEqualTo(post);
  }

  @Test
  void postCanBeUpdated() throws Exception {
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:12345/api/v1/post"))
                .POST(BodyPublishers.ofString("{\"markup\":\"test\"}"))
                .build(),
            BodyHandlers.ofString());

    Post post = buildPost(response.body());
    assertThat(post.getId()).isNotEqualTo(0);
    assertThat(post.getMarkup()).isEqualTo("test");

    HttpResponse<String> updateResponse =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(
                    URI.create(
                        String.format("http://localhost:12345/api/v1/post/%d", post.getId())))
                .PUT(BodyPublishers.ofString("{\"markup\":\"new\"}"))
                .build(),
            BodyHandlers.ofString());

    Post updatedPost = buildPost(updateResponse.body());
    assertThat(updatedPost.getMarkup()).isEqualTo("new");
    assertThat(getPost(post.getId())).isEqualTo(updatedPost);
  }

  @Test
  void postCanBeDeleted() throws Exception {
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:12345/api/v1/post"))
                .POST(BodyPublishers.ofString("{\"markup\":\"test\"}"))
                .build(),
            BodyHandlers.ofString());

    Post post = buildPost(response.body());
    long postId = post.getId();
    assertThat(postId).isNotEqualTo(0);
    assertThat(post.getMarkup()).isEqualTo("test");

    HttpClient.newHttpClient().send(
        HttpRequest.newBuilder()
            .uri(
                URI.create(
                    String.format("http://localhost:12345/api/v1/post/%d", postId)))
            .DELETE()
            .build(),
        BodyHandlers.ofString());

    HttpResponse<String> getResponse =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://localhost:12345/api/v1/post/%d", postId)))
                .GET()
                .build(),
            BodyHandlers.ofString());
    assertThat(getResponse.statusCode()).isEqualTo(HTTP_NOT_FOUND);
  }

  private Post getPost(long id) throws Exception {
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://localhost:12345/api/v1/post/%d", id)))
                .GET()
                .build(),
            BodyHandlers.ofString());

    return buildPost(response.body());
  }

  private HealthCheckResponse buildHealthResponse(String body)
      throws InvalidProtocolBufferException {
    HealthCheckResponse.Builder responseBuilder = HealthCheckResponse.newBuilder();
    JsonFormat.parser().merge(body, responseBuilder);
    return responseBuilder.build();
  }

  private Post buildPost(String body)
      throws InvalidProtocolBufferException {
    Post.Builder postBuilder = Post.newBuilder();
    JsonFormat.parser().merge(body, postBuilder);
    return postBuilder.build();
  }
}