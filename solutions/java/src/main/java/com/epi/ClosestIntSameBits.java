package com.epi;

import java.util.Random;

public class ClosestIntSameBits {
  // @include
  static final int NUM_UNSIGN_BITS = 63;

  public static long closestIntSameBitCount(long x) {
    // x is assumed to be non-negative so we know the leading bit is 0. We
    // restrict to our attention to 63 LSBs.
    for (int i = 0; i < NUM_UNSIGN_BITS - 1; ++i) {
      if ((((x >>> i) & 1) != ((x >>> (i + 1)) & 1))) {
        x ^= (1L << i) | (1L << (i + 1)); // Swaps bit-i and bit-(i + 1).
        return x;
      }
    }

    // Throw error if all bits of x are 0 or 1.
    throw new IllegalArgumentException("All bits are 0 or 1");
  }
  // @exclude

  public static int countBitsSetTo1(long x) {
    int count = 0;
    while (x != 0) {
      x &= (x - 1);
      ++count;
    }
    return count;
  }

  private static void smallTest() {
    assert(closestIntSameBitCount(6) == 5);
    assert(closestIntSameBitCount(7) == 11);
    assert(closestIntSameBitCount(2) == 1);
    assert(closestIntSameBitCount(32) == 16);
    assert(closestIntSameBitCount(Long.MAX_VALUE - 1) == Long.MAX_VALUE - 2);

    try {
      closestIntSameBitCount(Long.MAX_VALUE);
      assert(false);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    try {
      closestIntSameBitCount(0);
      assert(false);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    smallTest();

    Random r = new Random();
    long x;
    if (args.length == 1) {
      x = Long.parseLong(args[0]);
    } else {
      x = r.nextLong();
      if (x == Long.MIN_VALUE) {
        x = 0;
      } else if (x < 0) {
        x = -x;
      }
    }
    try {
      long res = closestIntSameBitCount(x);
      System.out.println(x + " " + res);
      assert(countBitsSetTo1(x) == countBitsSetTo1(res));
    } catch (Exception e) {
      System.out.println(x + " " + e.getMessage());
    }
  }
}
