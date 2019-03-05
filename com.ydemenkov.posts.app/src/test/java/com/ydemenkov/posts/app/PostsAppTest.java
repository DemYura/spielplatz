package com.ydemenkov.posts.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.port;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Spark;

/**
 * Simple Posts application test that should check that the server is up and running.
 */
class PostsAppTest {

  @BeforeAll
  static void runServer() throws Exception {
    port(12345);
    PostsApp.main(new String[]{});
    Spark.awaitInitialization();
  }

  @AfterAll
  static void killServer() {
    Spark.stop();
  }

  @Test
  void shouldBeRunning() throws Exeption {
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:12345/hello"))
                .GET()
                .build(),
            BodyHandlers.ofString());

    assertEquals(200, response.statusCode());
  }
}