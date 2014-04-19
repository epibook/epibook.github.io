package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Parity2 {
  // @include
  public static short parity2(long x) {
    short result = 0;
    while (x != 0) {
      result ^= 1;
      x &= (x - 1); // drops the lowest set bit of x.
    }
    return result;
  }
  // @exclude
}
