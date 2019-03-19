package com.ydemenkov.common.tools;

import spark.Request;

/** A set of helper methods to extract parameters from {@link spark.Request}. */
public final class RequestParameters {

  private RequestParameters() {
  }

  /**
   * Gets Long parameter value from request.
   *
   * @throws IllegalArgumentException if parameter is missing or has incorrect format
   */
  public static Long getLongValue(Request request, String paramName) {
    String rawValue = request.params(paramName);
    try {
      return Long.valueOf(rawValue);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          String.format("%s value for parameter %s.",
              rawValue == null ? "Missing" : "Bad", paramName));
    }
  }
}
