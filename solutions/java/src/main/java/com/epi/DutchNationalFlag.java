// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DutchNationalFlag {
  // @include
  public static void dutchFlagPartition(int pivotIndex, List<Integer> A) {
    int pivot = A.get(pivotIndex);

    /**
     * Keep the following invariants during partitioning:
     * bottom group: A.subList(0 : smaller).
     * middle group: A.subList(smaller : equal).
     * unclassified group: A.subList(equal : larger + 1).
     * top group: A.subList(larger + 1, A.size()).
     */
    int smaller = 0, equal = 0, larger = A.size() - 1;
    // Keep iterating as long as there is an unclassified element.
    while (equal <= larger) {
      // A.get(equal) is the incoming unclassified element.
      if (A.get(equal) < pivot) {
        Collections.swap(A, smaller++, equal++);
      } else if (A.get(equal) == pivot) {
        ++equal;
      } else { // A.get(equal) > pivot.
        Collections.swap(A, equal, larger--);
      }
    }
  }
  // @exclude

  private static List<Integer> randArray(int len) {
    Random r = new Random();
    List<Integer> ret = new ArrayList<>(len);
    for (int i = 0; i < len; ++i) {
      ret.add(r.nextInt(3));
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

      List<Integer> A = randArray(n);

      int pivotIndex = gen.nextInt(n);
      int pivot = A.get(pivotIndex);

      dutchFlagPartition(pivotIndex, A);

      int i = 0;
      while (i < n && A.get(i) < pivot) {
        System.out.print(A.get(i) + " ");
        ++i;
      }
      while (i < n && A.get(i) == pivot) {
        System.out.print(A.get(i) + " ");
        ++i;
      }
      while (i < n && A.get(i) > pivot) {
        System.out.print(A.get(i) + " ");
        ++i;
      }
      System.out.println();

      assert(i == n);
    }
  }
}
