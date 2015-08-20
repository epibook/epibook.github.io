// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class ThreeSum {
  // @include
  public static boolean hasThreeSum(List<Integer> A, int t) {
    Collections.sort(A);
    for (Integer a : A) {
      // Finds if the sum of two numbers in A equals to t - a.
      if (hasTwoSum(A, t - a)) {
        return true;
      }
    }
    return false;
  }

  private static boolean hasTwoSum(List<Integer> A, int t) {
    int j = 0, k = A.size() - 1;

    while (j <= k) {
      if (A.get(j) + A.get(k) == t) {
        return true;
      } else if (A.get(j) + A.get(k) < t) {
        ++j;
      } else { // A[j] + A[k] > t.
        --k;
      }
    }
    return false;
  }
  // @exclude

  // n^3 solution.
  public static boolean checkAns(List<Integer> A, int t) {
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.size(); ++j) {
        for (Integer aA : A) {
          if (A.get(i) + A.get(j) + aA == t) {
            return true;
          }
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
      System.out.println(hasThreeSum(A, T) ? "true" : "false");
      assert(checkAns(A, T) == hasThreeSum(A, T));
    }
  }
}
