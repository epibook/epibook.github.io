package com.epi;

public class RegularExpression {
  // @include
  public static boolean isMatch(String r, String s) {
    // Case (2.) : starts with '^'.
    if (r.charAt(0) == '^') {
      return isMatchHere(r.substring(1), s);
    }

    for (int i = 0; i <= s.length(); ++i) {
      if (isMatchHere(r, s.substring(i))) {
        return true;
      }
    }
    return false;
  }

  private static boolean isMatchHere(String r, String s) {
    // Case (1.)
    if (r.isEmpty()) {
      return true;
    }

    // Case (2) : ends with '$'.
    if ("$".equals(r)) {
      return s.isEmpty();
    }

    // Case (4.)
    if (r.length() >= 2 && r.charAt(1) == '*') {
      for (int i = 0; i < s.length()
          && (r.charAt(0) == '.' || r.charAt(0) == s.charAt(i)); ++i) {
        if (isMatchHere(r.substring(2), s.substring(i + 1))) {
          return true;
        }
      }
      return isMatchHere(r.substring(2), s);
    }

    // Case (3.)
    return !s.isEmpty() && (r.charAt(0) == '.' || r.charAt(0) == s.charAt(0))
        && isMatchHere(r.substring(1), s.substring(1));
  }
  // @exclude

  public static void main(String[] args) {
    assert (isMatch(".", "a"));
    assert (isMatch("a", "a"));
    assert (!isMatch("a", "b"));
    assert (isMatch("a.9", "aW9"));
    assert (!isMatch("a.9", "aW19"));
    assert (isMatch("^a.9", "aW9"));
    assert (!isMatch("^a.9", "baW19"));
    assert (isMatch(".*", "a"));
    assert (isMatch(".*", ""));
    assert (isMatch("c*", "c"));
    assert (!isMatch("aa*", "c"));
    assert (isMatch("ca*", "c"));
    assert (isMatch(".*", "asdsdsa"));
    assert (isMatch("9$", "xxxxW19"));

    assert (isMatch(".*a", "ba"));

    assert (isMatch(".*a", "ba"));
    assert (isMatch("^a.*9$", "axxxxW19"));

    assert (isMatch("^a.*W19$", "axxxxW19"));
    assert (isMatch(".*a.*W19", "axxxxW19123"));
    assert (!isMatch(".*b.*W19", "axxxxW19123"));
    assert (isMatch("n.*", "n"));
    assert (isMatch("a*n.*", "an"));
    assert (isMatch("a*n.*", "ana"));
    assert (isMatch("a*n.*W19", "anaxxxxW19123"));
    assert (isMatch(".*a*n.*W19", "asdaaadnanaxxxxW19123"));
    assert (isMatch(".*.*.a*n.*W19", "asdaaadnanaxxxxW19123"));
  }
}
