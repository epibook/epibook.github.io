// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.utils.Utils.swap;

public class DutchNationalFlag {

  // @include
  public static void dutchFlagPartition(List<Integer> A, int pivotIndex) {
    int pivot = A.get(pivotIndex);

    /**
     * Keep the following invariants during partitioning: bottom group:
     * A.subList(0, smaller - 1). middle group: A.subList(smaller, equal - 1).
     * unclassified group: A.subList(equal, larger). top group: A.subList(larger
     * + 1, A.size() - 1).
     */
    int smaller = 0, equal = 0, larger = A.size() - 1;
    // When there is any unclassified element.
    while (equal <= larger) {
      // A.get(equal) is the incoming unclassified element.
      if (A.get(equal) < pivot) {
        swap(A, smaller++, equal++);
      } else if (A.get(equal) == pivot) {
        ++equal;
      } else { // A.get(equal) > pivot.
        swap(A, equal, larger--);
      }
    }
  }
  // @exclude

  private static List<Integer> randVector(int len) {
    Random gen = new Random();
    List<Integer> ret = new ArrayList<>();

    while ((len--) > 0) {
      ret.add(gen.nextInt(3));
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

      List<Integer> A = randVector(n);

      int pivotIndex = gen.nextInt(A.size());
      int pivot = A.get(pivotIndex);

      dutchFlagPartition(A, pivotIndex);

      int i = 0;
      while (i < A.size() && A.get(i) < pivot) {
        System.out.print(A.get(i) + " ");
        ++i;
      }
      while (i < A.size() && A.get(i) == pivot) {
        System.out.print(A.get(i) + " ");
        ++i;
      }
      while (i < A.size() && A.get(i) > pivot) {
        System.out.print(A.get(i) + " ");
        ++i;
      }
      System.out.println();

      assert (i == A.size());
    }

  }
}
