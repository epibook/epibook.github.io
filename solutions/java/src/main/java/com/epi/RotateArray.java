// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

public class RotateArray {
  // @include
  public static void rotateArray(int i, int[] A) {
    i %= A.length;
    reverse(A, 0, A.length);
    reverse(A, 0, i);
    reverse(A, i, A.length);
  }
  // @exclude

  public static void reverse(int[] array, int start, int stopIndex) {
    if (start >= stopIndex) {
      return;
    }

    int last = stopIndex - 1;
    for (int i = start; i <= start + (last - start) / 2; i++) {
      int tmp = array[i];
      array[i] = array[last - i + start];
      array[last - i + start] = tmp;
    }
  }
}
