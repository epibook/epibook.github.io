// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

public class CanStringBePalindrome {
  private static String randString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    Random rnd = new Random();
    for (int times = 0; times < 1000; ++times) {
      String s = null;
      if (args.length == 1) {
        s = args[0];
      } else {
        s = randString(rnd.nextInt(10) + 1);
      }
      System.out.println(s);
      assert(CanStringBePalindromeHash.canFormPalindrome(s)
             == CanStringBePalindromeSorting.canFormPalindrome(s));
    }
  }
}
