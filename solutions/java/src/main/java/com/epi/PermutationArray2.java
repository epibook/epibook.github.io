// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class PermutationArray2 {
  // @include
  public static void applyPermutation(int[] perm, int[] A) {
    for (int i = 0; i < A.length; ++i) {
      // Traverses the cycle to see if i is the minimum element.
      boolean isMin = true;
      int j = perm[i];
      while (j != i) {
        if (j < i) {
          isMin = false;
          break;
        }
        j = perm[j];
      }

      if (isMin) {
        cyclicPermutation(i, perm, A);
      }
    }
  }

  private static void cyclicPermutation(int start, int[] perm, int[] A) {
    int i = start;
    int temp = A[start];
    do {
      int nextI = perm[i];
      int nextTemp = A[nextI];
      A[nextI] = temp;
      i = nextI;
      temp = nextTemp;
    } while (i != start);
  }
  // @exclude
}
