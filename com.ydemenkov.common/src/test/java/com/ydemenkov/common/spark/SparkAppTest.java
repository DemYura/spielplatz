package com.ydemenkov.common.spark;

import static com.google.common.truth.Truth.assertThat;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.truth.Truth;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ydemenkov.common.health.HealthCheckReport;
import com.ydemenkov.common.health.HealthCheckReport.CheckStatus;
import com.ydemenkov.common.spark.health.HealthCheck;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import spark.Spark;

@ExtendWith({MockitoExtension.class})
class SparkAppTest {

  private static final String HELLO_FROM_STUB_MESSAGE = "Hello from Stub.";

  static HealthCheck healthCheck;

  @BeforeAll
  static void setUp() {
    healthCheck = mock(HealthCheck.class);

    SparkApp.run(new ApplicationModule() {
      @Override
      protected void configureApplication() {
        bindController().toInstance(
            () -> Spark.get("/stub", (request, response) -> HELLO_FROM_STUB_MESSAGE));
        bindHealthCheck().toInstance(healthCheck);
      }
    }, 6789);
    Spark.awaitInitialization();
  }

  @Test
  void appIsRunning() throws Exception {
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:6789/api/v1/health"))
                .GET()
                .build(),
            BodyHandlers.ofString());

    assertThat(response.statusCode()).isEqualTo(HTTP_OK);
  }

  @Test
  void customControllerResponds() throws Exception {
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:6789/stub"))
                .GET()
                .build(),
            BodyHandlers.ofString());

    assertThat(response.statusCode()).isEqualTo(HTTP_OK);
    assertThat(response.body()).isEqualTo(HELLO_FROM_STUB_MESSAGE);
  }

  @Test
  void customHealthCheckSuccessful_healthShouldBeSuccessfulToo() throws Exception {
    when(healthCheck.performCheck())
        .thenReturn(
            HealthCheckReport.newBuilder()
                .setCheckName("test1")
                .setStatus(CheckStatus.SUCCESS)
                .build());
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:6789/api/v1/health"))
                .GET()
                .build(),
            BodyHandlers.ofString());

    assertThat(response.statusCode()).isEqualTo(HTTP_OK);

    JsonObject expectedResponse = buildExpectedHealthReport();
    assertThat(new JsonParser().parse(response.body())).isEqualTo(expectedResponse);
  }

  private JsonObject buildExpectedHealthReport() {
    JsonObject expectedResponse = new JsonObject();
    expectedResponse.addProperty("message", "Application is Healthy");

    JsonArray reports = new JsonArray();

    JsonObject report = new JsonObject();
    report.addProperty("checkName", "test1");
    report.addProperty("status", "SUCCESS");

    reports.add(report);

    expectedResponse.add("reports", reports);
    return expectedResponse;
  }

  @Test
  void customHealthCheckError_failsHealthCheck() throws Exception {
    when(healthCheck.performCheck())
        .thenReturn(
            HealthCheckReport.newBuilder()
                .setCheckName("test1")
                .setStatus(CheckStatus.FAILED)
                .build());
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:6789/api/v1/health"))
                .GET()
                .build(),
            BodyHandlers.ofString());

    assertThat(response.statusCode()).isEqualTo(HTTP_UNAVAILABLE);
  }

  @AfterAll
  static void tearDown() {
    Spark.stop();
  }
}