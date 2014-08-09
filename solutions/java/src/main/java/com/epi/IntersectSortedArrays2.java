// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntersectSortedArrays2 {
  // @include
  public static List<Integer> intersect(List<Integer> A, List<Integer> B) {
    List<Integer> intersect = new ArrayList<>();
    for (int i = 0; i < A.size(); ++i) {
      if ((i == 0 || !A.get(i).equals(A.get(i - 1)))
          && Collections.binarySearch(B, A.get(i)) >= 0) {
        intersect.add(A.get(i));
      }
    }
    return intersect;
  }
// @exclude
}
