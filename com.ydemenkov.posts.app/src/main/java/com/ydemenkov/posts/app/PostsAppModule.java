package com.ydemenkov.posts.app;

import com.ydemenkov.common.spark.ApplicationModule;
import com.ydemenkov.posts.app.controller.PostsController;
import com.ydemenkov.posts.app.database.DatabaseHealthCheck;
import com.ydemenkov.posts.app.database.PostsDataSourceModule;
import com.ydemenkov.posts.app.database.SchemaUpdateHealthCheck;
import com.ydemenkov.posts.app.service.JdbcPostsService;
import com.ydemenkov.posts.app.service.PostsService;

/**
 * Guice module that has all posts-related controllers and services set up.
 */
final class PostsAppModule extends ApplicationModule {

  @Override
  protected void configureApplication() {
    install(new PostsDataSourceModule());
    bind(PostsService.class).to(JdbcPostsService.class);
    bindController().to(PostsController.class);

    bindHealthCheck().to(SchemaUpdateHealthCheck.class);
    bindHealthCheck().to(DatabaseHealthCheck.class);
  }
}
