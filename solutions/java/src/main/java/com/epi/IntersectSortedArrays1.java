// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;

public class IntersectSortedArrays1 {
// @include
  public static ArrayList<Integer> intersect(ArrayList<Integer> A,
      ArrayList<Integer> B) {
    ArrayList<Integer> intersect = new ArrayList<Integer>();
    for (int i = 0; i < A.size(); ++i) {
      if (i == 0 || !A.get(i).equals(A.get(i - 1))) {
        for (int j = 0; j < B.size(); ++j) {
          if (A.get(i).equals(B.get(j))) {
            intersect.add(A.get(i));
            break;
          }
        }
      }
    }
    return intersect;
  }
// @exclude
}
