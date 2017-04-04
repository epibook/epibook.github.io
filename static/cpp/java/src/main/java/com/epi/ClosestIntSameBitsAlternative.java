package com.epi;

import java.util.Random;

public class ClosestIntSameBitsAlternative {
  // @include
  public static long closestIntSameBitCount(long x) {
    if (x == 0 || x == Long.MAX_VALUE) {
      throw new IllegalArgumentException("All bits are 0 or 1");
    }
    long mask = (x & 1) == 0 ? lowestSetBit(x) : lowestUnsetBit(x);
    mask = mask | (mask >>> 1);
    return x ^ mask;
  }

  private static long lowestSetBit(long x) { return x & ~(x - 1); }

  private static long lowestUnsetBit(long x) { return lowestSetBit(x + 1); }
  // @exclude

  public static int countBitsSetTo1(int x) {
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
  }

  public static void main(String[] args) {
    smallTest();
    long r1 = closestIntSameBitCount(1L);
    assert(r1 == 2L);

    long r2 = 0;
    try {
      r2 = closestIntSameBitCount(Long.MAX_VALUE);
    } catch (Exception e) {
      System.out.println(r2 + " " + e.getMessage());
    }

    long r3 = closestIntSameBitCount(Long.MAX_VALUE - 1);
    assert(r3 == Long.MAX_VALUE - 2);

    Random r = new Random();
    long x;
    if (args.length == 1) {
      x = Long.parseLong(args[0]);
    } else {
      x = r.nextInt(Integer.MAX_VALUE);
    }
    try {
      long res = closestIntSameBitCount(x);
      System.out.println(x + " " + res);
      assert(countBitsSetTo1((int)x) == countBitsSetTo1((int)res));
    } catch (Exception e) {
      System.out.println(x + " " + e.getMessage());
    }
  }
}
