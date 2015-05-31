// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectSortedArrays2 {
  // @include
  public static List<Integer> intersectTwoSortedArrays(int[] A, int[] B) {
    List<Integer> intersectionAB = new ArrayList<>();
    for (int i = 0; i < A.length; ++i) {
      if ((i == 0 || A[i] != A[i - 1]) && Arrays.binarySearch(B, A[i]) >= 0) {
        intersectionAB.add(A[i]);
      }
    }
    return intersectionAB;
  }
  // @exclude
}
