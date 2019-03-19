package com.ydemenkov.common.spark;

import static com.ydemenkov.common.tools.Flags.SERVER_PORT;
import static com.ydemenkov.common.tools.Flags.getIntOrDefault;
import static spark.Spark.port;

import com.google.inject.Guice;
import com.ydemenkov.common.spark.annotations.CommonAnnotations.Controllers;
import java.util.Set;
import javax.inject.Inject;

/**
 * An application that initiates Guice context, runs Spark on provided port and initiates all
 * {@link SparkController} in the context.
 */
public final class SparkApp {

  private static final int DEFAULT_PORT = 8080;
  private final Set<SparkController> controllers;

  @Inject
  private SparkApp(@Controllers Set<SparkController> controllers) {
    this.controllers = controllers;
  }

  private void run(int defaultPort) {
    port(getIntOrDefault(SERVER_PORT, defaultPort));
    ExceptionHandlers.initializeHandlers();
    controllers.forEach(SparkController::initiate);
  }

  /**
   * Runs the application using provided {@link ApplicationModule} on provided as
   * <pre>--server-port</pre> flag port, or if flag is missing, the provided default port value
   * is used.
   */
  public static void run(ApplicationModule mainModule, int defaultPort) {
    Guice.createInjector(mainModule)
        .getInstance(SparkApp.class)
        .run(defaultPort);
  }

  /**
   * Runs the application using provided {@link ApplicationModule} on provided as
   * <pre>--server_port</pre> flag port, or if flag is missing, on default 8080 port.
   */
  public static void run(ApplicationModule mainModule) {
    run(mainModule, DEFAULT_PORT);
  }
}
