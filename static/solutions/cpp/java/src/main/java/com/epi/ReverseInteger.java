package com.epi;

import java.util.Random;

public class ReverseInteger {
  // @include
  public static long reverse(int x) {
    long result = 0;
    long xRemaining = Math.abs(x);
    while (xRemaining != 0) {
      result = result * 10 + xRemaining % 10;
      xRemaining /= 10;
    }
    return x < 0 ? -result : result;
  }
  // @exclude

  private static long checkAns(int x) {
    String s = String.valueOf(x);
    char chars[] = s.toCharArray();
    StringBuilder result = new StringBuilder();
    if (chars[0] == '-') {
      result.append('-');
    }
    for (int i = chars.length - 1; i >= 0; i--) {
      if (chars[i] != '-') {
        result.append(chars[i]);
      }
    }
    return Long.parseLong(result.toString());
  }

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      System.out.println("n = " + n + ", reversed n = " + reverse(n));
      assert(checkAns(n) == reverse(n));
    } else {
      for (int times = 0; times < 1000; ++times) {
        n = r.nextInt();
        System.out.println("n = " + n + ", reversed n = " + reverse(n));
        assert(checkAns(n) == reverse(n));
      }
    }
  }
}
