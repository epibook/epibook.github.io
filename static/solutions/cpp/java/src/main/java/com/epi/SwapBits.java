package com.epi;

import java.util.Random;

public class SwapBits {
  // @include
  public static long swapBits(long x, int i, int j) {
    // Extract the i-th and j-th bits, and see if they differ.
    if (((x >>> i) & 1) != ((x >>> j) & 1)) {
      // i-th and j-th bits differ. We will swap them by flipping their values.
      // Select the bits to flip with bitMask. Since x^1 = 0 when x = 1 and 1
      // when x = 0, we can perform the flip XOR.
      long bitMask = (1L << i) | (1L << j);
      x ^= bitMask;
    }
    return x;
  }
  // @exclude

  private static void simpleTest() {
    assert(swapBits(47, 1, 4) == 61);
    assert(swapBits(28, 0, 2) == 25);
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    long x;
    int i, j;
    if (args.length == 3) {
      x = Long.parseLong(args[0]);
      i = Integer.parseInt(args[1]);
      j = Integer.parseInt(args[2]);
    } else {
      x = r.nextLong();
      i = r.nextInt(64);
      j = r.nextInt(64);
    }
    System.out.println("x = " + x + ", i = " + i + ", j = " + j);
    System.out.println(swapBits(x, i, j));
  }
}
