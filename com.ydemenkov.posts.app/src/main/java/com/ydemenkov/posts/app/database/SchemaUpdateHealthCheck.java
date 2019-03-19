package com.ydemenkov.posts.app.database;

import com.ydemenkov.common.health.HealthCheckReport;
import com.ydemenkov.common.health.HealthCheckReport.CheckStatus;
import com.ydemenkov.common.spark.health.HealthCheck;
import com.ydemenkov.posts.app.annotations.PostsAnnotations.DatabaseSchemaIsUpdated;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * A health check that checks if the database schema was updated on the startup of the application.
 */
@Singleton
public final class SchemaUpdateHealthCheck implements HealthCheck {

  public static final String SCHEMA_UPDATE_HEALTH_CHECK_NAME = "schemaUpdate";

  private final CompletableFuture<Boolean> schemaUpdateReadiness;

  @Inject
  public SchemaUpdateHealthCheck(
      @DatabaseSchemaIsUpdated CompletableFuture<Boolean> schemaUpdateReadiness) {
    this.schemaUpdateReadiness = schemaUpdateReadiness;
  }

  @Override
  public HealthCheckReport performCheck() {
    HealthCheckReport.Builder resultBuilder =
        HealthCheckReport.newBuilder().setCheckName(SCHEMA_UPDATE_HEALTH_CHECK_NAME);
    if (schemaUpdateReadiness.isDone()) {
      if (schemaUpdateReadiness.join()) {
        resultBuilder.setStatus(CheckStatus.SUCCESS).addDetails("Schema was successfully updated.");
      } else {
        resultBuilder.setStatus(CheckStatus.FAILED).addDetails("Schema was not updated.");
      }
    } else {
      resultBuilder
          .setStatus(CheckStatus.WARNINGS)
          .addDetails("Schema update is still in progress.");
    }
    return resultBuilder.build();
  }
}
