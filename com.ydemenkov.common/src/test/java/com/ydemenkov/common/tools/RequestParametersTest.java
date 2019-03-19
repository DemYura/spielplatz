package com.ydemenkov.common.tools;

import static org.junit.jupiter.api.Assertions.*;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spark.Request;

@ExtendWith(MockitoExtension.class)
class RequestParametersTest {

  @Test
  void getLongValue_shouldReturnParameterValue(@Mock Request request) {
    Mockito.when(request.params("test")).thenReturn("123");

    Truth.assertThat(RequestParameters.getLongValue(request, "test")).isEqualTo(123L);
  }

  @Test
  void getLongValue_incorrectValue(@Mock Request request) {
    Mockito.when(request.params("test")).thenReturn("abc");

    assertThrows(
        IllegalArgumentException.class,
        () -> RequestParameters.getLongValue(request, "test"));
  }

  @Test
  void getLongValue_missingValue(@Mock Request request) {
    Mockito.when(request.params("test")).thenReturn(null);

    assertThrows(
        IllegalArgumentException.class,
        () -> RequestParameters.getLongValue(request, "test"));
  }
}