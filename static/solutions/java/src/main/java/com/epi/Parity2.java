package com.epi;

public class Parity2 {
  // @include
  public static short parity(long x) {
    short result = 0;
    while (x != 0) {
      result ^= 1;
      x &= (x - 1); // Drops the lowest set bit of x.
    }
    return result;
  }
  // @exclude
}
