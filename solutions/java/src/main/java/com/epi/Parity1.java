package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Parity1 {
  // @include
  public static short parity(long x) {
    short result = 0;
    while (x != 0) {
      result ^= (x & 1);
      x >>= 1;
    }
    return result;
  }
  // @exclude
}
