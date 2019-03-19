package com.ydemenkov.common.spark.exceptions;

/**
 * A RuntimeException that accepts String format with arguments to quickly construct message.
 */
public class GenericServerException extends RuntimeException {

  public GenericServerException(String format, Object... args) {
    super(String.format(format, args));
  }

  public GenericServerException(Exception cause, String format, Object... args) {
    super(String.format(format, args), cause);
  }
}