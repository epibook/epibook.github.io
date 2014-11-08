// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class PermutationArray1 {
  // @include
  public static void applyPermutation1(int[] perm, int[] A) {
    for (int i = 0; i < A.length; ++i) {
      // Check if the element at index i has already been moved
      // by seeing if perm[i] is negative.
      if (perm[i] >= 0) {
        int a = i;
        int temp = A[i];
        do {
          int nextA = perm[a];
          int nextTemp = A[nextA];
          A[nextA] = temp;
          // Mark a as visited by using the sign bit. Specifically,
          // we subtract perm.length from each entry in perm.
          perm[a] -= perm.length;
          a = nextA;
          temp = nextTemp;
        } while (a != i);
      }
    }

    // Restore perm back.
    for (int i = 0; i < perm.length; i++) {
      perm[i] += perm.length;
    }
  }
  // @exclude
}
