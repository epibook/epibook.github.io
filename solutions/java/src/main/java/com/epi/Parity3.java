package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Parity3 {
  private static short[] precomputedParity;

  static {
    precomputedParity = new short[1 << 16];
    for (int i = 0; i < (1 << 16); ++i) {
      precomputedParity[i] = Parity1.parity1(i);
    }
  }

  // @include
  public static short parity3(long x) {
    return (short) (precomputedParity[(int) ((x >> 48) & 0xFFFF)]
        ^ precomputedParity[(int) ((x >> 32) & 0xFFFF)]
        ^ precomputedParity[(int) ((x >> 16) & 0xFFFF)] ^ precomputedParity[(int) (x & 0xFFFF)]);
  }
  // @exclude
}
