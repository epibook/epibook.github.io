// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays1 {
  // @include
  public static List<Integer> intersectTwoSortedArrays(int[] A, int[] B) {
    List<Integer> intersectionAB = new ArrayList<>();
    for (int i = 0; i < A.length; ++i) {
      if (i == 0 || A[i] != A[i - 1]) {
        for (Integer b : B) {
          if (A[i] == b) {
            intersectionAB.add(A[i]);
            break;
          }
        }
      }
    }
    return intersectionAB;
  }
// @exclude
}
