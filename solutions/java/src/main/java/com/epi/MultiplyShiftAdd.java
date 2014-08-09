package com.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MultiplyShiftAdd {
  // @include
  public static long multiplyNoOperator(long x, long y) {
    long sum = 0;
    while (x != 0) {
      // Examine the lg(k)-th bit of x.
      if (x % 2 != 0) {
        sum = addNoOperator(sum, y);
      }
      x >>= 1;
      y <<= 1;
    }
    return sum;
  }

  private static long addNoOperator(long a, long b) {
    long sum = 0, carryin = 0, k = 1, tempA = a, tempB = b;
    while (tempA != 0 || tempB != 0) {
      long ak = a & k, bk = b & k;
      long carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
      sum |= (ak ^ bk ^ carryin);
      carryin = carryout << 1;
      k <<= 1;
      tempA >>= 1;
      tempB >>= 1;
    }
    return sum | carryin;
  }
  // @exclude

  public static void main(String[] args) {
    if (args.length == 2) {
      int x = Integer.parseInt(args[0]), y = Integer.parseInt(args[1]);
      long res = multiplyNoOperator(x, y);
      assert (res == x * y);
      System.out.println("PASS: x = " + x + ", y = " + y + "; prod = " + res);
    } else {
      Random r = new Random();
      // Random test, only works if the product is not greater than 2^32 - 1.
      for (int i = 0; i < 100000; ++i) {
        int x = r.nextInt(65535), y = r.nextInt(65535);
        long prod = multiplyNoOperator(x, y);
        assert (prod == (long) x * y);
        System.out
            .println("PASS: x = " + x + ", y = " + y + "; prod = " + prod);
      }
    }
  }

}
