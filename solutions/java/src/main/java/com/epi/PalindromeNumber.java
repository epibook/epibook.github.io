package com.epi;

import java.util.Random;

public class PalindromeNumber {
  // @include
  public static boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }

    int numDigits = (int) (Math.floor(Math.log10(x))) + 1;
    int tempX = x, msbShift = (int) Math.pow(10, numDigits - 1);
    for (int i = 0; i < (numDigits >> 1); ++i) {
      if (x / msbShift != tempX % 10) {
        return false;
      }
      x %= msbShift;
      msbShift /= 10;
      tempX /= 10;
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
      assert (checkAns(x) == isPalindrome(x));
    } else {
      for (int times = 0; times < 1000; ++times) {
        x = r.nextInt(99999 * 2 + 1) - 99999;
        assert (checkAns(x) == isPalindrome(x));
      }
    }
  }
}
