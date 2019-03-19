package com.ydemenkov.common.spark.health;

import com.ydemenkov.common.health.HealthCheckReport;
import com.ydemenkov.common.spark.ApplicationModule;

/**
 * A health check that checks the state of some part of the application - database, network,
 * external API availability. The implementation has to be bound within {@link ApplicationModule} by
 * using corresponding binding method.
 */
public interface HealthCheck {

  /**
   * Performs the health check. Returns a report for specific part of the application, which will
   * be used to generate overall Application Health report.
   */
  HealthCheckReport performCheck();
}
