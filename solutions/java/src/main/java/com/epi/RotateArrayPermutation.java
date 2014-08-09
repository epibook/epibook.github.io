// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import static com.epi.utils.Utils.swap;

public class RotateArrayPermutation {
  // @include
  public static void rotateArray(int i, int[] A) {
    i %= A.length;
    // Compute the number of cycles in this rotation.
    int cycles = (int) GCD.elementaryGCD(A.length, i);
    int hops = A.length / cycles; // number of elements in a cycle.

    for (int c = 0; c < cycles; ++c) {
      for (int j = 1; j <= hops; ++j) {
        swap(A, (c + j * i) % A.length, c);
      }
    }
  }
  // @exclude
}
