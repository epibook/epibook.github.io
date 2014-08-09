// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays3 {
  // @include
  public static List<Integer> intersect(List<Integer> A, List<Integer> B) {
    List<Integer> intersect = new ArrayList<>();
    int i = 0;
    int j = 0;
    while (i < A.size() && j < B.size()) {
      if (A.get(i).equals(B.get(j))
          && (i == 0 || !A.get(i).equals(A.get(i - 1)))) {
        intersect.add(A.get(i));
        ++i;
        ++j;
      } else if (A.get(i) < B.get(j)) {
        ++i;
      } else { // A.get(i) > B.get(j).
        ++j;
      }
    }
    return intersect;
  }
  // @exclude
}
