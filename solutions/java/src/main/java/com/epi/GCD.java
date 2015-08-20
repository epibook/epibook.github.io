package com.epi;

import java.util.Random;

public class GCD {
  // @include
  public static long elementaryGCD(long x, long y) {
    if (x == 0) {
      return y;
    } else if (y == 0) {
      return x;
    } else if ((x & 1) == 0 && (y & 1) == 0) { // x and y are even.
      return elementaryGCD(x >> 1, y >> 1) << 1;
    } else if ((x & 1) == 0 && (y & 1) != 0) { // x is even, y is odd.
      return elementaryGCD(x >> 1, y);
    } else if ((x & 1) != 0 && (y & 1) == 0) { // x is odd, y is even.
      return elementaryGCD(x, y >> 1);
    } else if (x > y) { // Both x and y are odd, and x > y.
      return elementaryGCD(x - y, y);
    }
    return elementaryGCD(x, y - x); // Both x and y are odd, and x <= y.
  }
  // @exclude

  private static long anotherGCD(long a, long b) {
    if (b != 0) {
      while ((a = a % b) != 0 && (b = b % a) != 0) {
      }
    }
    return a + b;
  }

  public static void main(String[] args) {
    long x = 18, y = 12;
    assert(elementaryGCD(x, y) == 6);
    if (args.length == 2) {
      x = Integer.parseInt(args[0]);
      y = Integer.parseInt(args[1]);
      System.out.println(elementaryGCD(x, y));
      assert(elementaryGCD(x, y) == anotherGCD(x, y));
    } else {
      Random r = new Random();
      for (int times = 0; times < 1000; ++times) {
        x = r.nextInt(Integer.MAX_VALUE);
        y = r.nextInt(Integer.MAX_VALUE);
        assert(elementaryGCD(x, y) == anotherGCD(x, y));
      }
    }
  }
}
