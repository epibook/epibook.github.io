// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

public class PermutationArray1 {
  // @include
  public static void applyPermutation1(int[] perm, int[] A) {
    for (int i = 0; i < A.length; ++i) {
      if (perm[i] >= 0) {
        int a = i;
        int temp = A[i];
        do {
          int nextA = perm[a];
          int nextTemp = A[nextA];
          A[nextA] = temp;
          // Mark a as visited by using the sign bit.
          perm[a] -= perm.length;
          a = nextA;
          temp = nextTemp;
        } while (a != i);
      }
    }

    // Restore perm back.
    int size = perm.length;
    for (int i = 0; i < perm.length; i++) {
      perm[i] += size;
    }
  }
  // @exclude
}
