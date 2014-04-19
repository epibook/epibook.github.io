// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import static com.epi.utils.Utils.reverse;

public class RotateArray {
  // @include
  static void rotateArray(int[] A, int i) {
    i %= A.length;
    reverse(A, 0, A.length);
    reverse(A, 0, i);
    reverse(A, i, A.length);
  }
  // @exclude
}
