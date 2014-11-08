// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.max;

public class MaxDifferenceKPairs {
  // @include
  public static int maxKPairsProfits(int[] A, int k) {
    int[] kSum = new int[k * 2];
    Arrays.fill(kSum, Integer.MIN_VALUE);

    for (int i = 0; i < A.length; ++i) {
      int[] preKSum = Arrays.copyOf(kSum, kSum.length);

      for (int j = 0, sign = -1; j < kSum.length && j <= i; ++j, sign *= -1) {
        int diff = sign * A[i] + (j == 0 ? 0 : preKSum[j - 1]);
        kSum[j] = max(diff, preKSum[j]);
      }
    }

    // Returns the last selling profits as the answer.
    return kSum[kSum.length - 1];
  }
  // @exclude

  // O(n^k) checking answer.
  private static int checkAnsHelper(int[] A, int l, int k, int pre,
                                    int ans, int maxAns) {

    int finalMaxAns;
    if (l == k) {
      finalMaxAns = max(maxAns, ans);
    } else {
      finalMaxAns = maxAns;
      for (int i = pre; i < A.length; ++i) {
        finalMaxAns = checkAnsHelper(A, l + 1, k, i + 1, ans
            + (((l & 1) == 1) ? A[i] : -A[i]), finalMaxAns);
      }
    }

    return finalMaxAns;
  }

  private static int checkAns(int[] A, int k) {
    int ans = 0, maxAns = Integer.MIN_VALUE;

    maxAns = checkAnsHelper(A, 0, 2 * k, 0, ans, maxAns);
    System.out.println("maxAns = " + maxAns);
    return maxAns;
  }

  public static void main(String[] args) {
    Random gen = new Random();

    int n = 40, k = 3;
    // random tests for n = 40, k = 3 for 300 times/
    for (int times = 0; times < 300; ++times) {
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = gen.nextInt(100);
      }

      System.out.println("n = " + n + ", k = " + k);
      System.out.println(maxKPairsProfits(A, k));
      assert (checkAns(A, k) == maxKPairsProfits(A, k));
    }

    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
      k = gen.nextInt(n / 2) + 1;
    } else if (args.length == 2) {
      n = Integer.valueOf(args[0]);
      n = Integer.valueOf(args[1]);
    } else {
      n = gen.nextInt(10000) + 1;
      k = gen.nextInt(n / 2) + 1;
    }

    int[] A = new int[n];

    for (int i = 0; i < n; ++i) {
      A[i] = gen.nextInt(100);
    }

    System.out.println("n = " + n + ", k = " + k);
    System.out.println(maxKPairsProfits(A, k));
  }
}
