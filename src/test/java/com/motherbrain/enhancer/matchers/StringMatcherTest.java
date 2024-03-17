package com.motherbrain.enhancer.matchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StringMatcherTest {
  StringMatcher stringMatcher = new StringMatcher();

  @Test
  void caseDifference_shouldMatch() {
    String s1 = "Bluestep Bank";
    String s2 = "BlueStep Bank";
    assertTrue(stringMatcher.matches(s1, s2));
  }

  @Test
  void typo_shouldMatch() {
    String s1 = "itslearning";
    String s2 = "gtslearning";
    assertTrue(stringMatcher.matches(s1, s2));
  }

  @Test
  void twoTypos_shouldNotMatch() {
    String s1 = "Illuminoss";
    String s2 = "Illuminoo";
    assertFalse(stringMatcher.matches(s1, s2));
  }

  @Test
  void containingAB_shouldMatch() {
    String s1 = "Anticimex";
    String s2 = "Anticimex AB";
    assertTrue(stringMatcher.matches(s1, s2));
  }

  @Test
  void ab_shouldNotMatch() {
    String s1 = "Babelab";
    String s2 = "Babel";
    assertFalse(stringMatcher.matches(s1, s2));
  }
}
