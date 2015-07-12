// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class PermutationArray1 {
  // @include
  public static void applyPermutation(int[] perm, int[] A) {
    for (int i = 0; i < A.length; ++i) {
      // Check if the element at index i has not been permutated
      // by seeing if perm[i] is negative.
      if (perm[i] >= 0) {
        cyclicPermutation(i, perm, A);
      }
    }

    // Restore perm.
    for (int i = 0; i < perm.length; i++) {
      perm[i] += perm.length;
    }
  }

  private static void cyclicPermutation(int start, int[] perm, int[] A) {
    int i = start;
    int temp = A[start];
    do {
      int nextI = perm[i];
      int nextTemp = A[nextI];
      A[nextI] = temp;
      // Subtracts perm.length from an entry in perm to make it negative, which
      // indicates the corresponding assignment has been performed.
      perm[i] -= perm.length;
      i = nextI;
      temp = nextTemp;
    } while (i != start);
  }
  // @exclude
}
