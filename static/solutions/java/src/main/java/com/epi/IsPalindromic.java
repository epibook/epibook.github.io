// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

public class IsPalindromic {
  // @include
  public static boolean isPalindromic(String s) {
    for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }
    return true;
  }
  // @exclude

  private static boolean checkAnswer(String s) {
    String copy = new StringBuilder(s).reverse().toString();
    return s.equals(copy);
  }

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  public static void main(String args[]) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      String s = randString(r.nextInt(1000) + 1);
      assert(checkAnswer(s) == isPalindromic(s));
    }
  }
}
