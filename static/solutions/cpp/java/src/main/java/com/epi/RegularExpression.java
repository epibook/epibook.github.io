package com.epi;

public class RegularExpression {
  // @include
  public static boolean isMatch(String regex, String s) {
    // Case (2.): regex starts with '^'.
    if (regex.charAt(0) == '^') {
      return isMatchHere(regex.substring(1), s);
    }

    for (int i = 0; i <= s.length(); ++i) {
      if (isMatchHere(regex, s.substring(i))) {
        return true;
      }
    }
    return false;
  }

  private static boolean isMatchHere(String regex, String s) {
    if (regex.isEmpty()) {
      // Case (1.): Empty regex matches all strings.
      return true;
    }

    if ("$".equals(regex)) {
      // Case (2): Reach the end of regex, and last char is '$'.
      return s.isEmpty();
    }

    if (regex.length() >= 2 && regex.charAt(1) == '*') {
      // Case (3.): A '*' match.
      // Iterate through s, checking '*' condition, if '*' condition holds,
      // performs the remaining checks.
      for (int i = 0; i < s.length() && (regex.charAt(0) == '.'
                                         || regex.charAt(0) == s.charAt(i));
           ++i) {
        if (isMatchHere(regex.substring(2), s.substring(i + 1))) {
          return true;
        }
      }
      // See '*' matches zero character in s.
      return isMatchHere(regex.substring(2), s);
    }

    // Case (4.): regex begins with single character match.
    return !s.isEmpty()
        && (regex.charAt(0) == '.' || regex.charAt(0) == s.charAt(0))
        && isMatchHere(regex.substring(1), s.substring(1));
  }
  // @exclude

  public static void main(String[] args) {
    assert(isMatch(".", "a"));
    assert(isMatch("a", "a"));
    assert(!isMatch("a", "b"));
    assert(isMatch("a.9", "aW9"));
    assert(!isMatch("a.9", "aW19"));
    assert(isMatch("^a.9", "aW9"));
    assert(!isMatch("^a.9", "baW19"));
    assert(isMatch(".*", "a"));
    assert(isMatch(".*", ""));
    assert(isMatch("c*", "c"));
    assert(!isMatch("aa*", "c"));
    assert(isMatch("ca*", "c"));
    assert(isMatch(".*", "asdsdsa"));
    assert(isMatch("9$", "xxxxW19"));

    assert(isMatch(".*a", "ba"));

    assert(isMatch(".*a", "ba"));
    assert(isMatch("^a.*9$", "axxxxW19"));

    assert(isMatch("^a.*W19$", "axxxxW19"));
    assert(isMatch(".*a.*W19", "axxxxW19123"));
    assert(!isMatch(".*b.*W19", "axxxxW19123"));
    assert(isMatch("n.*", "n"));
    assert(isMatch("a*n.*", "an"));
    assert(isMatch("a*n.*", "ana"));
    assert(isMatch("a*n.*W19", "anaxxxxW19123"));
    assert(isMatch(".*a*n.*W19", "asdaaadnanaxxxxW19123"));
    assert(isMatch(".*.*.a*n.*W19", "asdaaadnanaxxxxW19123"));
  }
}
