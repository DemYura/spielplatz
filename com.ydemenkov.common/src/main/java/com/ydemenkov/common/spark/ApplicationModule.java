package com.ydemenkov.common.spark;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.multibindings.Multibinder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import com.ydemenkov.common.spark.annotations.CommonAnnotations.Controllers;
import com.ydemenkov.common.spark.annotations.CommonAnnotations.GeneralExecutor;
import com.ydemenkov.common.spark.annotations.CommonAnnotations.HealthChecks;
import com.ydemenkov.common.spark.annotations.CommonAnnotations.ProtoToJsonTransformer;
import com.ydemenkov.common.spark.health.HealthCheck;
import com.ydemenkov.common.spark.health.HealthController;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Singleton;
import spark.ResponseTransformer;

/**
 * Abstract Guice module that configures specific {@link spark.Spark} application. Allows to bind
 * {@link SparkController}s that will be later initiated by {@link SparkApp}, and allows to bind
 * {@link HealthCheck} that should be performed by the SparkApp.
 */
public abstract class ApplicationModule extends AbstractModule {

  private Multibinder<SparkController> controllers;
  private Multibinder<HealthCheck> healthChecks;

  @Override
  protected void configure() {
    controllers = Multibinder.newSetBinder(binder(), SparkController.class, Controllers.class);
    healthChecks = Multibinder.newSetBinder(binder(), HealthCheck.class, HealthChecks.class);
    bind(SparkApp.class).in(Singleton.class);
    bindController().to(HealthController.class).in(Singleton.class);
    configureApplication();
  }

  protected abstract void configureApplication();

  protected LinkedBindingBuilder<SparkController> bindController() {
    return controllers.addBinding();
  }

  protected LinkedBindingBuilder<HealthCheck> bindHealthCheck() {
    return healthChecks.addBinding();
  }

  @Provides
  @Singleton
  @ProtoToJsonTransformer
  ResponseTransformer provideJsonToProtoTransformer() {
    // TODO(ydemenkov): Get rid of JsonFormat, switch to Gson TypeAdapter for protos.
    return response -> JsonFormat.printer().print((MessageOrBuilder) response);
  }

  @Provides
  @Singleton
  @GeneralExecutor
  ExecutorService provideGeneralExecutor() {
    return Executors.newFixedThreadPool(10);
  }
}
