package com.ydemenkov.posts.app.database;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Provides;
import com.ydemenkov.common.tools.Flags;
import com.ydemenkov.posts.app.annotations.PostsAnnotations.DatabaseSchemaIsUpdated;
import com.ydemenkov.posts.app.annotations.PostsAnnotations.PostsDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.CompletableFuture;
import javax.sql.DataSource;

/**
 * Module that initializes {@link DataSource} for storing Posts. Uses <pre>--posts-db-url</pre> flag
 * value as url to database to connect. If flag is not set, uses in-memory Derby database.
 */
public final class PostsDataSourceModule extends AbstractModule {

  private static final String DEFAULT_INMEMORY_DATABASE_URL = "jdbc:derby:memory:test;create=true";
  private static final String POSTS_DATABASE_URL = "posts-db-url";
  private static final String SCHEMA_FILE_LOCATION = "schema.sql";

  @BindingAnnotation
  @Target({FIELD, PARAMETER, METHOD})
  @Retention(RUNTIME)
  @interface PostsSchemaFileLocation {
  }

  @Override
  protected void configure() {
    bindConstant().annotatedWith(PostsSchemaFileLocation.class).to(SCHEMA_FILE_LOCATION);
    bind(SchemaInitializer.class).asEagerSingleton();
  }

  @Provides
  @PostsDataSource
  DataSource provideDataSource() {
    HikariConfig config = new HikariConfig();

    config.setJdbcUrl(Flags.getStringOrDefault(POSTS_DATABASE_URL, DEFAULT_INMEMORY_DATABASE_URL));

    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    return new HikariDataSource(config);
  }

  @Provides
  @DatabaseSchemaIsUpdated
  CompletableFuture<Boolean> provideDatabaseReadiness(SchemaInitializer initializer) {
    return initializer.getSchemaUpdatedReadiness();
  }
}
