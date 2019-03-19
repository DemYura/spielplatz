package com.ydemenkov.common.tools;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

class FlagsTest {

  @Test
  void getIntOrDefault_shouldReturnIntValueFromSystem() {
    System.setProperty("test", "123");

    assertThat(Flags.getIntOrDefault("test", 100)).isEqualTo(123);
  }

  @Test
  void getIntOrDefault_propertyMissingShouldReturnDefaultValue() {
    System.clearProperty("test");

    assertThat(Flags.getIntOrDefault("test", 100)).isEqualTo(100);
  }

  @Test
  void getIntOrDefault_propertyIncorrectShouldReturnDefaultValue() {
    System.setProperty("test", "abc");

    assertThat(Flags.getIntOrDefault("test", 100)).isEqualTo(100);
  }

  @Test
  void getStringOrDefault_shouldReturnPropertyValue() {
    System.setProperty("test", "abc");

    assertThat(Flags.getStringOrDefault("test", "def")).isEqualTo("abc");
  }

  @Test
  void getStringOrDefault_propertyMissingShouldReturnDefaultValue() {
    System.clearProperty("test");

    assertThat(Flags.getStringOrDefault("test", "def")).isEqualTo("def");
  }

  @Test
  void getString_PropertyIsPresent() {
    System.setProperty("test", "123");

    assertThat(Flags.getString("test").isPresent()).isTrue();
    assertThat(Flags.getString("test").get()).isEqualTo("123");
  }

  @Test
  void getString_PropertyIsMissing() {
    System.clearProperty("test");

    assertThat(Flags.getString("test").isPresent()).isFalse();
  }
}