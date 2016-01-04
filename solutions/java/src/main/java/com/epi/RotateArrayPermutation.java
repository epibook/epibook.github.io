// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.List;

public class RotateArrayPermutation {
  // @include
  public static void rotateArray(int rotateAmount, List<Integer> A) {
    rotateAmount %= A.size();
    int numCycles = (int)GCD1.GCD(A.size(), rotateAmount);
    int cycleLength = A.size() / numCycles;

    for (int c = 0; c < numCycles; ++c) {
      applyCyclicPermutation(rotateAmount, c, cycleLength, A);
    }
  }

  private static void applyCyclicPermutation(int rotateAmount, int offset,
                                             int cycleLength, List<Integer> A) {
    int temp = A.get(offset);
    for (int i = 1; i < cycleLength; ++i) {
      int val = A.get((offset + i * rotateAmount) % A.size());
      A.set((offset + i * rotateAmount) % A.size(), temp);
      temp = val;
    }
    A.set(offset, temp);
  }
  // @exclude
}
