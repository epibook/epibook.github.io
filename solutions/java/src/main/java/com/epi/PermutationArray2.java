// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

public class PermutationArray2 {
  // @include
  public static void applyPermutation2(int[] perm, int[] A) {
    for (int i = 0; i < A.length; ++i) {
      // Traverse the cycle to see if i is the min element.
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
        int a = i;
        int temp = A[i];
        do {
          int nextA = perm[a];
          int nextTemp = A[nextA];
          A[nextA] = temp;
          a = nextA;
          temp = nextTemp;
        } while (a != i);
      }
    }
  }
  // @exclude
}
