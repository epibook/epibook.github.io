// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DutchNationalFlagTwoPasses {
  // @include
  public static void dutchFlagPartition(int pivotIndex, List<Integer> A) {
    int pivot = A.get(pivotIndex);
    // First pass: group elements smaller than pivot.
    int smaller = 0;
    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i) < pivot) {
        Collections.swap(A, smaller++, i);
      }
    }
    // Second pass: group elements larger than pivot.
    int larger = A.size() - 1;
    for (int i = A.size() - 1; i >= 0 && A.get(i) >= pivot; --i) {
      if (A.get(i) > pivot) {
        Collections.swap(A, larger--, i);
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
