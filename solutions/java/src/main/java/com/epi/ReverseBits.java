package com.epi;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ReverseBits {
  private static ArrayList<Long> precomputedReverse = new ArrayList<Long>();

  private static long reverseBits(long x, int n) {
    for (int i = 0, j = n; i < j; ++i, --j) {
      x = SwapBits.swapBits(x, i, j);
    }
    return x;
  }

  private static void createPrecomputedTable() {
    for (int i = 0; i < (1 << 16); ++i) {
      precomputedReverse.add(reverseBits(i, 15));
    }
  }

  // @include
  public static long reverseBits(long x) {
    return precomputedReverse.get((int) ((x >> 48) & 0xFFFF))
        | precomputedReverse.get((int) ((x >> 32) & 0xFFFF)) << 16
        | precomputedReverse.get((int) ((x >> 16) & 0xFFFF)) << 32
        | precomputedReverse.get((int) (x & 0xFFFF)) << 48;
  }

  // @exclude

  public static void main(String[] args) {
    createPrecomputedTable();
    if (args.length == 2) {
      long x = Long.parseLong(args[1]);
      System.out.println("sizeof(x) = 8"); // Java long is always 8 bytes
      System.out.println("x = " + x + ", reverseBits(x) = " + reverseBits(x));
      System.out.println(reverseBits(x, 63));
      assert reverseBits(x) == reverseBits(x, 63);
    } else {
      Random r = new Random();
      for (int times = 0; times < 1000; ++times) {
        long x = r.nextLong();
        System.out.println("x = " + x + ", reverseBits(x) = " + reverseBits(x));
        assert reverseBits(x) == reverseBits(x, 63);
      }
    }
  }
}
