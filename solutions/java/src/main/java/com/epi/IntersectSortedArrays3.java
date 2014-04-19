// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;

// @include
public class IntersectSortedArrays3 {
  public static ArrayList<Integer> intersect(ArrayList<Integer> A,
      ArrayList<Integer> B) {
    ArrayList<Integer> intersect = new ArrayList<Integer>();
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
}
// @exclude
