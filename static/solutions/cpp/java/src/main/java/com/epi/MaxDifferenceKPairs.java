// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaxDifferenceKPairs {
  // @include
  public static double maxKPairsProfits(List<Double> A, int k) {
    List<Double> kSum = new ArrayList<>();
    for (int i = 0; i < k * 2; ++i) {
      kSum.add(Double.NEGATIVE_INFINITY);
    }

    for (int i = 0; i < A.size(); ++i) {
      List<Double> preKSum = new ArrayList<>(kSum);
      for (int j = 0, sign = -1; j < kSum.size() && j <= i; ++j, sign *= -1) {
        double diff = sign * A.get(i) + (j == 0 ? 0 : preKSum.get(j - 1));
        kSum.set(j, Math.max(diff, preKSum.get(j)));
      }
    }

    // Returns the last selling profits as the answer.
    return kSum.get(kSum.size() - 1);
  }
  // @exclude

  // O(n^k) checking answer.
  private static double checkAnsHelper(List<Double> A, int l, int k, int pre,
                                       double ans, double maxAns) {
    double finalMaxAns;
    if (l == k) {
      finalMaxAns = Math.max(maxAns, ans);
    } else {
      finalMaxAns = maxAns;
      for (int i = pre; i < A.size(); ++i) {
        finalMaxAns = checkAnsHelper(
            A, l + 1, k, i + 1, ans + (((l & 1) == 1) ? A.get(i) : -A.get(i)),
            finalMaxAns);
      }
    }

    return finalMaxAns;
  }

  private static double checkAns(List<Double> A, int k) {
    double ans = 0, maxAns = Double.NEGATIVE_INFINITY;

    maxAns = checkAnsHelper(A, 0, 2 * k, 0, ans, maxAns);
    System.out.println("maxAns = " + maxAns);
    return maxAns;
  }

  public static void main(String[] args) {
    Random gen = new Random();

    int n = 30, k = 4;
    // random tests for n = 30, k = 4 for 100 times/
    for (int times = 0; times < 100; ++times) {
      List<Double> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(gen.nextDouble() * 100.0);
      }

      System.out.println("n = " + n + ", k = " + k);
      System.out.println(maxKPairsProfits(A, k));
      assert(checkAns(A, k) == maxKPairsProfits(A, k));
    }

    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      k = gen.nextInt(n / 2) + 1;
    } else if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      n = gen.nextInt(60) + 1;
      k = gen.nextInt(1 + (n / 10)) + 1;
    }

    List<Double> A = new ArrayList<>();

    for (int i = 0; i < n; ++i) {
      A.add(gen.nextDouble() * 100.0);
    }

    System.out.println("n = " + n + ", k = " + k);
    System.out.println(checkAns(A, k) + ", " + maxKPairsProfits(A, k));
    assert(checkAns(A, k) == maxKPairsProfits(A, k));
  }
}
