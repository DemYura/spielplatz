package com.ydemenkov.posts.app.database;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.common.io.Resources;
import com.ydemenkov.common.spark.annotations.CommonAnnotations.GeneralExecutor;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import com.ydemenkov.posts.app.annotations.PostsAnnotations.PostsDataSource;
import com.ydemenkov.posts.app.database.PostsDataSourceModule.PostsSchemaFileLocation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import javax.sql.DataSource;

/**
 * Service that initializes the database schema based on provided sql file. Also has a special
 * completable future that indicates if the update operation is finished and if it is finished
 * successfully.
 */
@Singleton
final class SchemaInitializer {

  private static final Logger LOGGER = Logger.getLogger(SchemaInitializer.class.getName());

  private final DataSource dataSource;
  private final String schemaFileLocation;

  private final CompletableFuture<Boolean> schemaUpdatedReadiness;

  @Inject
  SchemaInitializer(
      @PostsDataSource DataSource dataSource,
      @PostsSchemaFileLocation String schemaFileLocation,
      @GeneralExecutor ExecutorService executorService) {
    this.dataSource = dataSource;
    this.schemaFileLocation = schemaFileLocation;

    schemaUpdatedReadiness =
        CompletableFuture.supplyAsync(this::initializeSchema, executorService);
  }

  private boolean initializeSchema() {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            Resources.toString(Resources.getResource(schemaFileLocation), UTF_8))) {
      preparedStatement.execute();
      return true;
    } catch (SQLException | IOException e) {
      LOGGER.log(Level.SEVERE, "Could not initialize database schema", e);
    }
    return false;
  }

  /**
   * Returns a completable future that identifies if the schema was updated or not.
   */
  CompletableFuture<Boolean> getSchemaUpdatedReadiness() {
    return schemaUpdatedReadiness;
  }
}
