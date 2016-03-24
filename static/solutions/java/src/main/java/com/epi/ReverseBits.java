package com.epi;

import java.util.Random;

public class ReverseBits {
  private static long[] precomputedReverse = new long[(1 << 16)];

  private static long reverseBits(long x, int n) {
    for (int i = 0, j = n; i < j; ++i, --j) {
      x = SwapBits.swapBits(x, i, j);
    }
    return x;
  }

  private static void createPrecomputedTable() {
    for (int i = 0; i < (1 << 16); ++i) {
      precomputedReverse[i] = reverseBits(i, 15);
    }
  }

  // @include
  public static long reverseBits(long x) {
    final int WORD_SIZE = 16;
    final int BIT_MASK = 0xFFFF;
    // clang-format off
    return precomputedReverse[(int)(x & BIT_MASK)] << (3 * WORD_SIZE)
           | precomputedReverse[(int)((x >>> WORD_SIZE) & BIT_MASK)]
                 << (2 * WORD_SIZE)
           | precomputedReverse[(int)((x >>> (2 * WORD_SIZE)) & BIT_MASK)]
                 << WORD_SIZE
           | precomputedReverse[(int)((x >>> (3 * WORD_SIZE)) & BIT_MASK)];
    // clang-format on
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
