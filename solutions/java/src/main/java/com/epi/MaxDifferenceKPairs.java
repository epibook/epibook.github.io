// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaxDifferenceKPairs {
  // @include
  static int maxKPairsProfits(List<Integer> A, int k) {
    List<Integer> kSum = new ArrayList<Integer>(k << 1);
    fill(kSum, k << 1, Integer.MIN_VALUE);

    for (int i = 0; i < A.size(); ++i) {
      List<Integer> preKSum = new ArrayList<Integer>(kSum);

      for (int j = 0, sign = -1; j < kSum.size() && j <= i; ++j, sign *= -1) {
        int diff = sign * A.get(i) + (j == 0 ? 0 : preKSum.get(j - 1));
        kSum.set(j, max(diff, preKSum.get(j)));
      }
    }

    return kSum.get(kSum.size() - 1); // returns the last selling profits as
                                      // the answer.
  }

  // @exclude

  static void fill(List<Integer> list, int numOfElements, int value) {
    for (int i = 1; i <= numOfElements; ++i) {
      list.add(value);
    }
  }

  // O(n^k) checking answer.
  static int checkAnsHelper(List<Integer> A, int l, int k, int pre, int ans,
      int maxAns) {

    int finalMaxAns;
    if (l == k) {
      finalMaxAns = max(maxAns, ans);
    } else {
      finalMaxAns = maxAns;
      for (int i = pre; i < A.size(); ++i) {
        finalMaxAns = checkAnsHelper(A, l + 1, k, i + 1, ans
            + (((l & 1) == 1) ? A.get(i) : -A.get(i)), finalMaxAns);
      }
    }

    return finalMaxAns;
  }

  static int checkAns(List<Integer> A, int k) {
    int ans = 0, maxAns = Integer.MIN_VALUE;

    maxAns = checkAnsHelper(A, 0, k << 1, 0, ans, maxAns);
    System.out.println("maxAns = " + maxAns);
    return maxAns;
  }

  public static void main(String[] args) {
    Random gen = new Random();

    int n = 40, k = 3;
    // random tests for n = 40, k = 3 for 300 times/
    for (int times = 0; times < 300; ++times) {
      List<Integer> A = new ArrayList<Integer>();
      for (int i = 0; i < n; ++i) {
        A.add(gen.nextInt(100));
      }

      System.out.println("n = " + n + ", k = " + k);
      System.out.println(maxKPairsProfits(A, k));
      assert (checkAns(A, k) == maxKPairsProfits(A, k));
    }

    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
      k = gen.nextInt(n >> 1) + 1;
    } else if (args.length == 2) {
      n = Integer.valueOf(args[0]);
      n = Integer.valueOf(args[1]);
    } else {
      n = gen.nextInt(10000) + 1;
      k = gen.nextInt(n >> 1) + 1;
    }

    List<Integer> A = new ArrayList<Integer>();

    for (int i = 0; i < n; ++i) {
      A.add(gen.nextInt(100));
    }

    System.out.println("n = " + n + ", k = " + k);
    System.out.println(maxKPairsProfits(A, k));
  }

}
