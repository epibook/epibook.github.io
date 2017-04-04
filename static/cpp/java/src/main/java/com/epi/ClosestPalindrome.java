package com.epi;

import java.util.Random;

public class ClosestPalindrome {
  // @include
  static long findClosestPalindrome(long x) {
    // Make str a palindrome by mirroring the left half to the right half.
    String mirrored = mirrorLeftHalf(String.valueOf(x));
    StringBuilder str = new StringBuilder(mirrored);

    long mirrorLeft = Long.parseLong(mirrored);
    int idx = (str.length() - 1) / 2;
    if (mirrorLeft >= x) {
      // Subtract one from the left half.
      while (idx >= 0) {
        if (str.charAt(idx) == '0') {
          str.setCharAt(idx--, '9');
        } else {
          char c = str.charAt(idx);
          str.setCharAt(idx, (char)(c - 1));
          break;
        }
      }
      // Special case, make the entire string "99...9".
      if (str.charAt(0) == '0') {
        str.deleteCharAt(0); // Removes the leading 0.
        for (int i = 0; i < str.length(); i++) {
          str.setCharAt(i, '9');
        }
      }
    } else { // mirrorLeft < x.
      // Add one to the left half.
      while (idx >= 0) {
        if (str.charAt(idx) == '9') {
          str.setCharAt(idx--, '0');
        } else {
          char c = str.charAt(idx);
          str.setCharAt(idx, (char)(c + 1));
          break;
        }
      }
    }

    // Make str a palindrome again by mirroring the left half to the right half.
    mirrored = mirrorLeftHalf(str.toString());
    return Math.abs(x - mirrorLeft) < Math.abs(x - Long.parseLong(mirrored))
        ? mirrorLeft
        : Long.parseLong(mirrored);
  }
  // @exclude

  static String mirrorLeftHalf(String s) {
    int half = s.length() / 2;
    StringBuilder sb = new StringBuilder(s);
    for (int i = 0; i < half; i++) {
      sb.setCharAt(sb.length() - 1 - i, s.charAt(i));
    }
    return sb.toString();
  }

  static boolean isPalindrome(long x) {
    String str = String.valueOf(x);
    for (int i = 0, j = str.length() - 1; i < j; ++i, --j) {
      if (str.charAt(i) != str.charAt(j)) {
        return false;
      }
    }
    return true;
  }

  static void checkAnswer(long x, long ans) {
    if (isPalindrome(x)) {
      assert ans == x;
      return;
    }
    long small = x - 1;
    while (!isPalindrome(small)) {
      --small;
    }
    long big = x + 1;
    while (!isPalindrome(big)) {
      ++big;
    }
    if (x - small > big - x) {
      assert big - x == (ans > x ? ans - x : x - ans);
    } else {
      assert x - small == (ans > x ? ans - x : x - ans);
    }
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 100000; ++times) {
      long x;
      if (args.length == 1) {
        x = Integer.parseInt(args[0]);
      } else {
        x = gen.nextInt(10000000) + 1;
      }
      long ret = findClosestPalindrome(x);
      System.out.println(x + " " + ret);
      assert isPalindrome(ret);
      checkAnswer(x, ret);
    }
  }
}
