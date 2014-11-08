// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import com.epi.utils.Pair;

import java.util.Random;

public class LongestIncreasingSubarray {
  // @include
  public static Pair<Integer, Integer> findLongestIncreasingSubarray(int[] A) {
    int maxLength = 1;
    Pair<Integer, Integer> ans = new Pair<>(0, 0);
    int i = 0;
    while (i < A.length) {
      // Backward check and skip if A[j] >= A[j + 1].
      boolean isSkippable = false;
      for (int j = i + maxLength - 1; j >= i; --j) {
        if (j + 1 >= A.length || A[j] >= A[j + 1]) {
          i = j + 1;
          isSkippable = true;
          break;
        }
      }

      // Forward check if it is not skippable.
      if (!isSkippable) {
        i += maxLength - 1;
        while (i + 1 < A.length && A[i] < A[i + 1]) {
          ++i;
          ++maxLength;
        }
        ans = new Pair<>(i - maxLength + 1, i);
      }
    }
    return ans;
  }
  // @exclude

  private static void simpleTest() {
    int[] A = {-1, -1};
    Pair<Integer, Integer> ans = findLongestIncreasingSubarray(A);
    assert (ans.getFirst() == 0 && ans.getSecond() == 0);

    A[0] = 1;
    A[1] = 2;
    ans = findLongestIncreasingSubarray(A);
    assert (ans.getFirst() == 0 && ans.getSecond() == 1);
  }

  public static void main(String[] args) {
    simpleTest();
    Random gen = new Random();

    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
      } else {
        n = gen.nextInt(1000000) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = gen.nextBoolean() ? -1 : 1 * gen.nextInt(n);
      }
      Pair<Integer, Integer> result = findLongestIncreasingSubarray(A);
      System.out.println(result.getFirst() + " " + result.getSecond());
      int len = 1;
      for (int i = 1; i < A.length; ++i) {
        if (A[i] > A[i - 1]) {
          ++len;
        } else {
          len = 1;
        }
        assert (len <= result.getSecond() - result.getFirst() + 1);
      }
    }
  }
}
