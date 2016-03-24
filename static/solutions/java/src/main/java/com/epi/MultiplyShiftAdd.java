package com.epi;

import java.util.Random;

public class MultiplyShiftAdd {
  // @include
  public static long multiply(long x, long y) {
    long sum = 0;
    while (x != 0) {
      // Examines each bit of x.
      if ((x & 1) != 0) {
        sum = add(sum, y);
      }
      // clang-format off
      x >>>= 1;
      // clang-format on
      y <<= 1;
    }
    return sum;
  }

  private static long add(long a, long b) {
    long sum = 0, carryin = 0, k = 1, tempA = a, tempB = b;
    while (tempA != 0 || tempB != 0) {
      long ak = a & k, bk = b & k;
      long carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
      sum |= (ak ^ bk ^ carryin);
      carryin = carryout << 1;
      k <<= 1;
      // clang-format off
      tempA >>>= 1;
      tempB >>>= 1;
      // clang-format on
    }
    return sum | carryin;
  }
  // @exclude

  public static void main(String[] args) {
    if (args.length == 2) {
      int x = Integer.parseInt(args[0]), y = Integer.parseInt(args[1]);
      long res = multiply(x, y);
      assert(res == x * y);
      System.out.println("PASS: x = " + x + ", y = " + y + "; prod = " + res);
    } else {
      Random r = new Random();
      // Random test, only works if the product is not greater than 2^32 - 1.
      for (int i = 0; i < 100000; ++i) {
        int x = r.nextInt(65535), y = r.nextInt(65535);
        long prod = multiply(x, y);
        assert(prod == (long)x * y);
        System.out.println("PASS: x = " + x + ", y = " + y + "; prod = "
                           + prod);
      }
    }
  }
}
