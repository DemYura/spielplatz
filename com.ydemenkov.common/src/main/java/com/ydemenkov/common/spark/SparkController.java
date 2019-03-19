package com.ydemenkov.common.spark;

/**
 * A single piece of configuration for Spark web application that is used within
 * {@link ApplicationModule}. Implementation should be bound within the module as controller.
 */
public interface SparkController {

  /**
   * This method is invoked on {@link SparkApp} start-up, if the implementation was bound
   * within the {@link ApplicationModule} that is passed to the application to run. This method
   * might use {@link spark.Spark} static methods to add new mappings.
   */
  void initiate();
}
