package com.ydemenkov.posts.app.service;

import com.google.common.collect.ImmutableList;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import javax.inject.Inject;
import com.ydemenkov.common.spark.exceptions.GenericServerException;
import com.ydemenkov.posts.Post;
import com.ydemenkov.posts.app.annotations.PostsAnnotations.PostsDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import javax.inject.Singleton;
import javax.sql.DataSource;

/**
 * An implementation of {@link PostsService} that uses provided {@link DataSource} and simple SQL
 * queries to interact with SQL storage that holds Posts.
 */
@Singleton
public class JdbcPostsService implements PostsService {

  private static final String LIST_POSTS_QUERY = "SELECT * FROM PostsTable";
  private static final String GET_POST_BY_ID_QUERY = "SELECT * FROM PostsTable WHERE id=?";
  private static final String DELETE_POST_BY_ID_QUERY = "DELETE FROM PostsTable WHERE id=?";
  private static final String INSERT_POST_QUERY = "INSERT INTO PostsTable VALUES (?,?)";
  private static final String UPDATE_POST_QUERY = "UPDATE PostsTable SET markup=? WHERE id=?";

  private static final Logger LOGGER = Logger.getLogger(JdbcPostsService.class.getName());

  private final DataSource dataSource;

  @Inject
  public JdbcPostsService(@PostsDataSource DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public ImmutableList<Post> listAllPosts() {
    ImmutableList.Builder<Post> resultBuilder = ImmutableList.builder();
    try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement =
            connection.prepareStatement(LIST_POSTS_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        resultBuilder.add(readPost(resultSet));
      }
    } catch (SQLException | IOException e) {
      LOGGER.severe(e.getMessage());
    }
    return resultBuilder.build();
  }

  @Override
  public Optional<Post> getPost(Long id) {
    try (Connection connection = dataSource.getConnection()) {
      return getPost(id, connection);
    } catch (SQLException | IOException e) {
      throw new GenericServerException(e, "Could not get Post with id=%d", id);
    }
  }

  private Optional<Post> getPost(Long id, Connection connection) throws SQLException, IOException {
    try (PreparedStatement preparedStatement =
        connection.prepareStatement(GET_POST_BY_ID_QUERY)) {
      preparedStatement.setLong(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return Optional.of(readPost(resultSet));
        }
      }
    }
    return Optional.empty();
  }

  @Override
  public Post create(Post post) {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_POST_QUERY)) {
      long id = UUID.randomUUID().getMostSignificantBits();
      ps.setLong(1, id);
      ps.setClob(2, CharSource.wrap(post.getMarkup()).openStream());

      ps.execute();
      return post.toBuilder().setId(id).build();
    } catch (SQLException | IOException e) {
      throw new GenericServerException(e, "Could not create new Post");
    }
  }

  @Override
  public Post update(Long id, Post post) {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_POST_QUERY)) {
      ps.setClob(1, CharSource.wrap(post.getMarkup()).openStream());
      ps.setLong(2, id);
      ps.executeUpdate();

      return getPost(id, conn)
          .orElseThrow(
              () -> new GenericServerException("Could not find Post with id=%d after update.", id));
    } catch (SQLException | IOException e) {
      throw new GenericServerException(e, "Could not create new Post");
    }
  }

  @Override
  public void deletePost(Long id) {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement =
            connection.prepareStatement(DELETE_POST_BY_ID_QUERY)) {
      preparedStatement.setLong(1, id);
      preparedStatement.execute();
    } catch (SQLException e) {
      throw new GenericServerException(e, "Could not delete Post with id=%d", id);
    }
  }

  private Post readPost(ResultSet resultSet) throws SQLException, IOException {
    return Post.newBuilder()
        .setId(resultSet.getLong("id"))
        .setMarkup(
            CharStreams.toString(
                resultSet.getClob("markup").getCharacterStream()))
        .build();
  }
}
