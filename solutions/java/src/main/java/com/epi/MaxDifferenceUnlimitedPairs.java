// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Random;

public class MaxDifferenceUnlimitedPairs {
  // @include
  public static int maxProfitUnlimitedPairs(int[] A) {
    int profit = 0;
    for (int i = 1; i < A.length; ++i) {
      int delta = A[i] - A[i - 1];
      if (delta > 0) {
        profit += delta;
      }
    }
    return profit;
  }
  // @exclude

  private static int checkAns(int[] A) {
    int profit = 0;

    for (int i = 1; i < A.length; ++i) {
      if (A[i] > A[i - 1]) {
        profit += A[i] - A[i - 1];
      }
    }
    return profit;
  }

  public static void main(String[] args) {
    Random gen = new Random();
    int n = 5;
    // random tests for n = 40, k = 4 for 100 times
    for (int times = 0; times < 100; ++times) {
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = gen.nextInt(100);
      }
      System.out.println("n = " + n);
      System.out.println(checkAns(A));
      System.out.println(maxProfitUnlimitedPairs(A));
      assert(checkAns(A) == maxProfitUnlimitedPairs(A));
    }

    // For input
    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
    } else {
      n = gen.nextInt(10000) + 1;
    }
    int[] A = new int[n];
    for (int i = 0; i < n; ++i) {
      A[i] = gen.nextInt(100);
    }
    System.out.println("n = " + n);
    System.out.println(maxProfitUnlimitedPairs(A));
  }
}
