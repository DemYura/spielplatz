package com.ydemenkov.posts.app.service;

import com.google.common.collect.ImmutableList;
import com.ydemenkov.posts.Post;
import java.util.Optional;

/**
 * A service that handles actions on {@link Post}s.
 */
public interface PostsService {

  /**
   * Lists all available posts.
   */
  ImmutableList<Post> listAllPosts();

  /**
   * Returns a Post by provided id. Result may be empty if Post was not found.
   */
  Optional<Post> getPost(Long id);

  /**
   * Creates new post. Returns created Post that contains generated id. Note that provided Post
   * should not contain Id.
   *
   * @throws IllegalArgumentException if provided Post contains id
   */
  Post create(Post post);

  /**
   * Updates a Post with provided Id with provided Post's data. Returns updated Post.
   *
   * @throws IllegalArgumentException if Post with provided Id does not exist
   */
  Post update(Long id, Post post);

  /**
   * Deletes a Post with provided id.
   *
   * @throws IllegalArgumentException if Post with provided id was not found
   */
  void deletePost(Long id);
}
