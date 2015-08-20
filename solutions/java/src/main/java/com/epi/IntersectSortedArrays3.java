// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays3 {
  // @include
  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    List<Integer> intersectionAB = new ArrayList<>();
    int i = 0, j = 0;
    while (i < A.size() && j < B.size()) {
      if (A.get(i) == B.get(j) && (i == 0 || A.get(i) != A.get(i - 1))) {
        intersectionAB.add(A.get(i));
        ++i;
        ++j;
      } else if (A.get(i) < B.get(j)) {
        ++i;
      } else { // A.get(i) > B.get(j).
        ++j;
      }
    }
    return intersectionAB;
  }
  // @exclude
}
