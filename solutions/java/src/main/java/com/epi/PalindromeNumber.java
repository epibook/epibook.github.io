package com.epi;

import java.util.Random;

public class PalindromeNumber {
  // @include
  public static boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    } else if (x == 0) {
      return true;
    }

    final int NUM_DIGITS = (int)(Math.floor(Math.log10(x))) + 1;
    int msdMask = (int)Math.pow(10, NUM_DIGITS - 1);
    for (int i = 0; i < (NUM_DIGITS / 2); ++i) {
      if (x / msdMask != x % 10) {
        return false;
      }
      x %= msdMask; // Remove the most significant digit of x.
      x /= 10; // Remove the least significant digit of x.
      msdMask /= 100;
    }
    return true;
  }
  // @exclude

  private static boolean checkAns(int x) {
    String s = String.valueOf(x);
    for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Random r = new Random();
    int x;
    if (args.length == 1) {
      x = Integer.parseInt(args[0]);
      System.out.println(x + " " + isPalindrome(x));
      assert(checkAns(x) == isPalindrome(x));
    } else {
      for (int times = 0; times < 1000; ++times) {
        x = r.nextInt(99999 * 2 + 1) - 99999;
        assert(checkAns(x) == isPalindrome(x));
      }
    }
  }
}
