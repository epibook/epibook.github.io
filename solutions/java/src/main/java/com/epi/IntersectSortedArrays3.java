// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays3 {
  // @include
  public static List<Integer> intersectTwoSortedArrays(int[] A, int[] B) {
    List<Integer> intersectionAB = new ArrayList<>();
    int i = 0, j = 0;
    while (i < A.length && j < B.length) {
      if (A[i] == B[j] && (i == 0 || A[i] != A[i - 1])) {
        intersectionAB.add(A[i]);
        ++i;
        ++j;
      } else if (A[i] < B[j]) {
        ++i;
      } else { // A[i] > B[j].
        ++j;
      }
    }
    return intersectionAB;
  }
  // @exclude
}
