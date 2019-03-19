package com.ydemenkov.common.spark;

import static spark.Spark.exception;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.ydemenkov.common.spark.exceptions.EntityNotFoundException;
import com.ydemenkov.common.spark.exceptions.GenericServerException;
import java.net.HttpURLConnection;
import spark.ExceptionHandler;

/**
 * Contains required exception handlers and initializes them for Spark.
 */
final class ExceptionHandlers {

  private static final ExceptionHandler<EntityNotFoundException>
      ENTITY_NOT_FOUND_EXCEPTION_EXCEPTION_HANDLER =
      (e, request, response) -> {
        response.status(HttpURLConnection.HTTP_NOT_FOUND);
        response.body(
            printErrorMessage(
                String.format("Entity with id=%d not found.", e.getEntityId())));
      };

  private static final ExceptionHandler<GenericServerException>
      INTERNAL_ERROR_HANDLER =
      (e, request, response) -> {
        response.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
        response.body(printErrorMessage(e.getMessage()));
      };

  private static final ExceptionHandler<IllegalArgumentException>
      BAD_REQUEST_HANDLER =
      (e, request, response) -> {
        response.status(HttpURLConnection.HTTP_BAD_REQUEST);
        response.body(printErrorMessage(e.getMessage()));
      };

  private ExceptionHandlers() {
  }

  static void initializeHandlers() {
    exception(EntityNotFoundException.class, ENTITY_NOT_FOUND_EXCEPTION_EXCEPTION_HANDLER);
    exception(GenericServerException.class, INTERNAL_ERROR_HANDLER);
    exception(IllegalArgumentException.class, BAD_REQUEST_HANDLER);
  }

  private static String printErrorMessage(String message) {
    JsonObject object = new JsonObject();
    object.add("message", new JsonPrimitive(message));
    return object.toString();
  }
}
