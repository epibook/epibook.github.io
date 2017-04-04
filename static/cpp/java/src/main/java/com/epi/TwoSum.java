// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TwoSum {
  // @include
  public static boolean hasTwoSum(List<Integer> A, int t) {
    int i = 0, j = A.size() - 1;
    while (i <= j) {
      if (A.get(i) + A.get(j) == t) {
        return true;
      } else if (A.get(i) + A.get(j) < t) {
        ++i;
      } else { // A[i] + A[j] > t.
        --j;
      }
    }
    return false;
  }
  // @exclude

  // n^2 solution.
  public static boolean checkAns(List<Integer> A, int t) {
    for (int i = 0; i < A.size(); ++i) {
      for (int j = i; j < A.size(); ++j) {
        if (A.get(i) + A.get(j) == t) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, T;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        T = gen.nextInt(n - 1);
      } else {
        n = gen.nextInt(10000) + 1;
        T = gen.nextInt(n - 1);
      }

      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(gen.nextInt(200000) - 100000);
      }
      Collections.sort(A);
      System.out.println(hasTwoSum(A, T) ? "true" : "false");
      assert(checkAns(A, T) == hasTwoSum(A, T));
    }
  }
}
