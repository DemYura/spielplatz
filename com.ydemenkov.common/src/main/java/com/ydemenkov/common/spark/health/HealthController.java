package com.ydemenkov.common.spark.health;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;
import static spark.Spark.get;

import com.ydemenkov.common.spark.annotations.CommonAnnotations.GeneralExecutor;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import com.ydemenkov.common.health.HealthCheckReport;
import com.ydemenkov.common.health.HealthCheckResponse;
import com.ydemenkov.common.spark.SparkController;
import com.ydemenkov.common.health.HealthCheckReport.CheckStatus;
import com.ydemenkov.common.spark.annotations.CommonAnnotations.HealthChecks;
import com.ydemenkov.common.spark.annotations.CommonAnnotations.ProtoToJsonTransformer;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import spark.Request;
import spark.Response;
import spark.ResponseTransformer;

/**
 * General Health controller that handles health request, performs all registered in the context
 * health checks and generates a report.
 */
public final class HealthController implements SparkController {

  private final Set<HealthCheck> checks;
  private final ResponseTransformer responseTransformer;
  private final ExecutorService executor;

  @Inject
  public HealthController(
      @HealthChecks Set<HealthCheck> checks,
      @ProtoToJsonTransformer ResponseTransformer responseTransformer,
      @GeneralExecutor ExecutorService executor) {
    this.checks = checks;
    this.responseTransformer = responseTransformer;
    this.executor = executor;
  }

  @Override
  public void initiate() {
    get("/api/v1/health", this::checkHealth, responseTransformer);
    get("/api/v1/health/", this::checkHealth, responseTransformer);
  }

  /**
   * Checks if application is healthy and responds 200 or 503.
   */
  private HealthCheckResponse checkHealth(Request request, Response response) {
    List<HealthCheckReport> reports =
        checks.stream()
            .map(healthCheck -> CompletableFuture.supplyAsync(healthCheck::performCheck, executor))
            .map(CompletableFuture::join)
            .collect(Collectors.toUnmodifiableList());
    String message = "Application is Healthy";
    if (reports.stream().allMatch(report -> CheckStatus.SUCCESS.equals(report.getStatus()))) {
      response.status(HTTP_OK);
    } else {
      response.status(HTTP_UNAVAILABLE);
      message = "Application is not health";
    }
    return HealthCheckResponse.newBuilder().setMessage(message).addAllReports(reports).build();
  }
}
