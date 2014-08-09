package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Parity4 {
  // @include
  public static short parity(long x) {
    x ^= x >> 32;
    x ^= x >> 16;
    x ^= x >> 8;
    x ^= x >> 4;
    x &= 0xf; // Only wants the last 4 bits of x.
    // Return the LSB, which is the parity.
    return (short) (fourBitParityLookup((int) x) & 1);
  }

  // The LSB of FOUR_BIT_PARITY_LOOKUP_TABLE is the parity of 0,
  // next bit is parity of 1, followed by the parity of 2, etc.

  // 0b0110100110010110 = 0x6996.
  private static final int K_FOUR_BIT_PARITY_LOOKUP_TABLE = 0x6996;

  private static short fourBitParityLookup(int x) {
    return (short) (K_FOUR_BIT_PARITY_LOOKUP_TABLE >> x);
  }
  // @exclude
}
