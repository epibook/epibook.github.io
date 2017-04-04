package com.epi;

public class GCD2 {
  // @include
  public static long GCD(long x, long y) { return y == 0 ? x : GCD(y, x % y); }
  // @exclude
}
