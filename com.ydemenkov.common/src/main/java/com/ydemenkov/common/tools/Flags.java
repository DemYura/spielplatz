package com.ydemenkov.common.tools;

import java.util.Optional;
import java.util.logging.Logger;

/** Set of methods to operate with system properties. */
public final class Flags {

  public static final String SERVER_PORT = "server_port";

  private static final Logger LOGGER = Logger.getLogger(Flags.class.getName());

  /**
   * Returns integer value of the property of provided default value, if property was not set or
   * it is not a number.
   */
  public static Integer getIntOrDefault(String propertyName, int defaultValue) {
    try {
      String stringValue = System.getProperty(propertyName);
      if (stringValue != null) {
        return Integer.parseInt(stringValue);
      }
    } catch (NumberFormatException ex) {
      LOGGER.warning(
          String.format(
              "Could not read integer flag value for flag %s. The default value %d is used.",
              propertyName, defaultValue));
    }
    return defaultValue;
  }

  /** Returns string value of the property, or provided default value, if property was not set. */
  public static String getStringOrDefault(String propertyName, String defaultValue) {
    return getString(propertyName).orElse(defaultValue);
  }

  /**
   * Returns an optional that holds value of the property, or empty value, if property was not set.
   */
  public static Optional<String> getString(String propertyName) {
    return Optional.ofNullable(System.getProperty(propertyName));
  }

  private Flags() {
  }
}
