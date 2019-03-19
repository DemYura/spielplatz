package com.ydemenkov.posts.app.database;

import com.ydemenkov.common.health.HealthCheckReport;
import com.ydemenkov.common.health.HealthCheckReport.CheckStatus;
import com.ydemenkov.common.spark.health.HealthCheck;
import com.ydemenkov.posts.app.annotations.PostsAnnotations.PostsDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

/**
 * A Health check that checks if the application is able to connect to the database where Posts are
 * stored.
 */
@Singleton
public final class DatabaseHealthCheck implements HealthCheck {

  private static final Logger LOGGER = Logger.getLogger(DatabaseHealthCheck.class.getName());
  public static final String DATABASE_CONNECTION_HEALTH_CHECK_NAME = "databaseConnection";

  private final DataSource dataSource;

  @Inject
  public DatabaseHealthCheck(
      @PostsDataSource DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public HealthCheckReport performCheck() {
    HealthCheckReport.Builder resultBuilder =
        HealthCheckReport.newBuilder().setCheckName(DATABASE_CONNECTION_HEALTH_CHECK_NAME);
    try {
      if (dataSource.getConnection().isValid(10)) {
        resultBuilder.setStatus(CheckStatus.SUCCESS).addDetails("Database is connected.");
      } else {
        resultBuilder.setStatus(CheckStatus.FAILED).addDetails("Could not connect to database.");
      }
    } catch (SQLException e) {
      LOGGER.log(Level.WARNING, e, () -> "Failed to get database connection status.");
      resultBuilder
          .setStatus(CheckStatus.WARNINGS)
          .addDetails("Could not get database connection status.")
          .addDetails(e.getMessage());
    }
    return resultBuilder.build();
  }
}
