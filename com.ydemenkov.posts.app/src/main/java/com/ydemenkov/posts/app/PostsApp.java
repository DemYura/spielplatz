package com.ydemenkov.posts.app;

import static spark.Spark.get;

public class PostsApp {

  public static void main(String[] args) throws Exception {
    get("/hello", (req, res) -> "Hello World");
  }
}
