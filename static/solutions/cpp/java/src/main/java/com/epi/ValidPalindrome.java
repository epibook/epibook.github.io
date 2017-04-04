package com.epi;

public class ValidPalindrome {
  // @include
  public static boolean isPalindrome(String s) {
    // i moves forward, and j moves backward.
    int i = 0, j = s.length() - 1;
    while (i < j) {
      // i and j both skip non-alphanumeric characters.
      while (!Character.isLetterOrDigit(s.charAt(i)) && i < j) {
        ++i;
      }
      while (!Character.isLetterOrDigit(s.charAt(j)) && i < j) {
        --j;
      }
      if (Character.toLowerCase(s.charAt(i++))
          != Character.toLowerCase(s.charAt(j--))) {
        return false;
      }
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
