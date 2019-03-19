package com.ydemenkov.posts.app.controller;

import static com.ydemenkov.common.tools.RequestParameters.getLongValue;

import com.google.gson.JsonObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.ydemenkov.common.spark.SparkController;
import com.ydemenkov.common.spark.annotations.CommonAnnotations.ProtoToJsonTransformer;
import com.ydemenkov.common.spark.exceptions.EntityNotFoundException;
import com.ydemenkov.posts.ListPostsResponse;
import com.ydemenkov.posts.Post;
import com.ydemenkov.posts.app.service.PostsService;
import javax.inject.Inject;
import javax.inject.Singleton;
import spark.Request;
import spark.Response;
import spark.ResponseTransformer;
import spark.Spark;

/**
 * Controller that handles posts-related requests.
 */
@Singleton
public final class PostsController implements SparkController {

  private final ResponseTransformer responseTransformer;
  private final PostsService postsService;

  @Inject
  public PostsController(
      @ProtoToJsonTransformer ResponseTransformer responseTransformer,
      PostsService postsService) {
    this.responseTransformer = responseTransformer;
    this.postsService = postsService;
  }

  @Override
  public void initiate() {
    Spark.get("/api/v1/posts", this::listPosts, responseTransformer);
    Spark.get("/api/v1/posts/", this::listPosts, responseTransformer);

    Spark.get("/api/v1/post/:id", this::getPost, responseTransformer);
    Spark.get("/api/v1/post/:id/", this::getPost, responseTransformer);

    Spark.post("/api/v1/post", this::createPost, responseTransformer);
    Spark.post("/api/v1/post/", this::createPost, responseTransformer);

    Spark.put("/api/v1/post/:id", this::updatePost, responseTransformer);
    Spark.put("/api/v1/post/:id/", this::updatePost, responseTransformer);

    Spark.delete("/api/v1/post/:id", this::deletePost);
    Spark.delete("/api/v1/post/:id/", this::deletePost);
  }

  private JsonObject deletePost(Request request, Response response) {
    response.type("application/json");
    postsService.deletePost(getLongValue(request, "id"));
    return new JsonObject();
  }

  private Post updatePost(Request request, Response response) {
    response.type("application/json");
    return postsService.update(
        getLongValue(request, "id"), getPostFromBody(request));
  }

  private Post createPost(Request request, Response response) {
    response.type("application/json");
    return postsService.create(getPostFromBody(request));
  }

  private Post getPostFromBody(Request request) {
    Post.Builder postBuilder = Post.newBuilder();
    try {
      JsonFormat.parser().merge(request.body(), postBuilder);
    } catch (InvalidProtocolBufferException e) {
      throw new IllegalArgumentException("Incorrect post format.");
    }
    return postBuilder.build();
  }

  private Post getPost(Request request, Response response) {
    response.type("application/json");
    Long id = getLongValue(request, "id");
    return postsService.getPost(id)
        .orElseThrow(() -> new EntityNotFoundException(id));
  }

  private ListPostsResponse listPosts(Request request, Response response) {
    response.type("application/json");
    return ListPostsResponse.newBuilder().addAllPosts(postsService.listAllPosts()).build();
  }
}
