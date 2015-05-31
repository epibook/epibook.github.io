// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import com.epi.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LongestIncreasingSubarray {
  // @include
  public static Pair<Integer, Integer> findLongestIncreasingSubarray(
      List<Integer> A) {
    int maxLength = 1;
    Pair<Integer, Integer> ans = new Pair<>(0, 0);
    int i = 0;
    while (i < A.size() - maxLength) {
      // Backward check and skip if A[j - 1] >= A[j].
      boolean isSkippable = false;
      for (int j = i + maxLength; j > i; --j) {
        if (A.get(j - 1) >= A.get(j)) {
          i = j;
          isSkippable = true;
          break;
        }
      }

      // Forward check if it is not skippable.
      if (!isSkippable) {
        i += maxLength;
        while (i < A.size() && A.get(i - 1) < A.get(i)) {
          ++i;
          ++maxLength;
        }
        ans = new Pair<>(i - maxLength, i - 1);
      }
    }
    return ans;
  }
  // @exclude

  private static void simpleTest() {
    Pair<Integer, Integer> ans =
        findLongestIncreasingSubarray(Arrays.asList(-1, -1));
    assert(ans.getFirst() == 0 && ans.getSecond() == 0);
    ans = findLongestIncreasingSubarray(Arrays.asList(1, 2));
    assert(ans.getFirst() == 0 && ans.getSecond() == 1);
  }

  public static void main(String[] args) {
    simpleTest();
    Random gen = new Random();

    for (int times = 0; times < 1000; ++times) {
      List<Integer> A = new ArrayList<>();
      if (args.length > 2) {
        for (int i = 1; i < args.length; ++i) {
          A.add(Integer.valueOf(args[i]));
        }
      } else {
        int n;
        if (args.length == 1) {
          n = Integer.valueOf(args[0]);
        } else {
          n = gen.nextInt(1000000) + 1;
        }
        for (int i = 0; i < n; ++i) {
          A.add((gen.nextBoolean() ? -1 : 1) * gen.nextInt(n));
        }
      }
      Pair<Integer, Integer> result = findLongestIncreasingSubarray(A);
      System.out.println(result.getFirst() + " " + result.getSecond());
      int len = 1;
      for (int i = 1; i < A.size(); ++i) {
        if (A.get(i) > A.get(i - 1)) {
          ++len;
        } else {
          len = 1;
        }
        assert(len <= result.getSecond() - result.getFirst() + 1);
      }
    }
  }
}
