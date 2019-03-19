package com.ydemenkov.common.spark.exceptions;

/**
 * An exception that indicates that an entity with provided id was not found.
 */
public final class EntityNotFoundException extends RuntimeException {
  private final long id;

  public EntityNotFoundException(long id) {
    this.id = id;
  }

  public long getEntityId() {
    return id;
  }
}
