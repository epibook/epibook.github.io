// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays1 {
  // @include
  public static List<Integer> intersect(int[] A, int[] B) {
    List<Integer> intersect = new ArrayList<>();
    for (int i = 0; i < A.length; ++i) {
      if (i == 0 || A[i] != A[i - 1]) {
        for (Integer b : B) {
          if (A[i] == b) {
            intersect.add(A[i]);
            break;
          }
        }
      }
    }
    return intersect;
  }
// @exclude
}
