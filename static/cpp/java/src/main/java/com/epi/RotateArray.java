// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import static com.epi.utils.Utils.reverse;

public class RotateArray {
  // @include
  public static void rotateArray(int i, int[] A) {
    i %= A.length;
    reverse(A, 0, A.length);
    reverse(A, 0, i);
    reverse(A, i, A.length);
  }
  // @exclude
}
