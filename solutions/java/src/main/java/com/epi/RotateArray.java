// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Collections;
import java.util.List;

public class RotateArray {
  // @include
  public static void rotateArray(int i, List<Integer> A) {
    i %= A.size();
    reverse(0, A.size(), A);
    reverse(0, i, A);
    reverse(i, A.size(), A);
  }

  private static void reverse(int begin, int end, List<Integer> A) {
    for (int i = begin, j = end - 1; i < j; ++i, --j) {
      Collections.swap(A, i, j);
    }
  }
  // @exclude
}
