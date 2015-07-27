// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.List;

public class PermutationArray1 {
  // @include
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    for (int i = 0; i < A.size(); ++i) {
      // Check if the element at index i has not been moved by seeing if
      // perm.get(i) is nonnegative.
      if (perm.get(i) >= 0) {
        cyclicPermutation(i, perm, A);
      }
    }

    // Restore perm.
    for (int i = 0; i < perm.size(); i++) {
      perm.set(i, perm.get(i) + perm.size());
    }
  }

  private static void cyclicPermutation(int start, List<Integer> perm,
                                        List<Integer> A) {
    int i = start;
    int temp = A.get(start);
    do {
      int nextI = perm.get(i);
      int nextTemp = A.get(nextI);
      A.set(nextI, temp);
      // Subtracts perm.size() from an entry in perm to make it negative, which
      // indicates the corresponding move has been performed.
      perm.set(i, perm.get(i) - perm.size());
      i = nextI;
      temp = nextTemp;
    } while (i != start);
  }
  // @exclude
}
