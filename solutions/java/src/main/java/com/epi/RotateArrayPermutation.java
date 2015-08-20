// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

public class RotateArrayPermutation {
  // @include
  public static void rotateArray(int rotateAmount, int[] A) {
    rotateAmount %= A.length;
    int numCycles = (int)GCD.elementaryGCD(A.length, rotateAmount);
    int cycleLength = A.length / numCycles;

    for (int c = 0; c < numCycles; ++c) {
      applyCyclicPermutation(rotateAmount, c, cycleLength, A);
    }
  }

  private static void applyCyclicPermutation(int rotateAmount, int offset,
                                             int cycleLength, int[] A) {
    int temp = A[offset];
    for (int i = 1; i < cycleLength; ++i) {
      int val = A[(offset + i * rotateAmount) % A.length];
      A[(offset + i * rotateAmount) % A.length] = temp;
      temp = val;
    }
    A[offset] = temp;
  }
  // @exclude
}
