// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

import static com.epi.utils.Utils.swap;

public class DutchNationalFlagTwoPasses {
  // @include
  public static void dutchFlagPartition(int pivotIndex, int[] A) {
    int pivot = A[pivotIndex];
    // First pass: group elements smaller than pivot.
    int smaller = 0;
    for (int i = 0; i < A.length; ++i) {
      if (A[i] < pivot) {
        swap(A, smaller++, i);
      }
    }
    // Second pass: group elements larger than pivot.
    int larger = A.length - 1;
    for (int i = A.length - 1; i >= 0 && A[i] >= pivot; --i) {
      if (A[i] > pivot) {
        swap(A, larger--, i);
      }
    }
  }
  // @exclude

  private static int[] randVector(int len) {
    Random gen = new Random();
    int[] ret = new int[len];

    for (int i = 0; i < len; ++i) {
      ret[i] = gen.nextInt(3);
    }

    return ret;
  }

  public static void main(String[] args) {
    Random gen = new Random();

    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
      } else {
        n = gen.nextInt(100) + 1;
      }

      int[] A = randVector(n);

      int pivotIndex = gen.nextInt(n);
      int pivot = A[pivotIndex];

      dutchFlagPartition(pivotIndex, A);

      int i = 0;
      while (i < n && A[i] < pivot) {
        System.out.print(A[i] + " ");
        ++i;
      }
      while (i < n && A[i] == pivot) {
        System.out.print(A[i] + " ");
        ++i;
      }
      while (i < n && A[i] > pivot) {
        System.out.print(A[i] + " ");
        ++i;
      }
      System.out.println();

      assert(i == n);
    }
  }
}
