// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

import static com.epi.utils.Utils.swap;

public class DutchNationalFlag {
  // @include
  public static void dutchFlagPartition(int pivotIndex, int[] A) {
    int pivot = A[pivotIndex];

    /**
     * Keep the following invariants during partitioning:
     * bottom group: A[0 : smaller - 1].
     * middle group: A[smaller : equal - 1].
     * unclassified group: A[equal : larger].
     * top group: A[larger + 1, A.length - 1].
     */
    int smaller = 0, equal = 0, larger = A.length - 1;
    // Keep iterating as long as there is an unclassified element.
    while (equal <= larger) {
      // A[equal] is the incoming unclassified element.
      if (A[equal] < pivot) {
        swap(A, smaller++, equal++);
      } else if (A[equal] == pivot) {
        ++equal;
      } else { // A[equal] > pivot.
        swap(A, equal, larger--);
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
