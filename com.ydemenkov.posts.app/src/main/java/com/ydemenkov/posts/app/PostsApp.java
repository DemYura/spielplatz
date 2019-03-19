package com.ydemenkov.posts.app;

import com.ydemenkov.common.spark.SparkApp;

/** Posts application entry point. */
public class PostsApp {

  public static void main(String[] args) {
    // TODO(ydemenkov): Parse args to get flags values instead of system properties.
    SparkApp.run(new PostsAppModule());
  }
}
