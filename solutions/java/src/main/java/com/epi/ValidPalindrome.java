package com.epi;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.toLowerCase;

public class ValidPalindrome {
  // @include
  public static boolean isPalindrome(String s) {
    // i moves forward, and j moves backward.
    int i = 0, j = s.length() - 1;
    while (i < j) {
      // i and j both skip non-alphanumeric characters.
      while (!isLetterOrDigit(s.charAt(i)) && i < j) {
        ++i;
      }
      while (!isLetterOrDigit(s.charAt(j)) && i < j) {
        --j;
      }
      if (toLowerCase(s.charAt(i)) != toLowerCase(s.charAt(j))) {
        return false;
      }
      ++i;
      --j;
    }
    return true;
  }
  // @exclude

  public static void main(String[] args) {
    assert(isPalindrome("A man, a plan, a canal: Panama"));
    assert(!isPalindrome("race a car"));
    assert(isPalindrome("Able was I, ere I saw Elba!"));
    assert(!isPalindrome("Ray a Ray"));
    assert(isPalindrome(""));
  }
}
