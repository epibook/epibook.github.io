// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LongestIncreasingSubarray {
  // @include
  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findLongestIncreasingSubarray(List<Integer> A) {
    int maxLength = 1;
    Subarray ans = new Subarray(0, 0);
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
        ans = new Subarray(i - maxLength, i - 1);
      }
    }
    return ans;
  }
  // @exclude

  private static void simpleTest() {
    Subarray ans = findLongestIncreasingSubarray(Arrays.asList(-1, -1));
    assert(ans.start == 0 && ans.end == 0);
    ans = findLongestIncreasingSubarray(Arrays.asList(1, 2));
    assert(ans.start == 0 && ans.end == 1);
  }

  public static void main(String[] args) {
    simpleTest();
    Random gen = new Random();

    for (int times = 0; times < 1000; ++times) {
      List<Integer> A = new ArrayList<>();
      if (args.length > 2) {
        for (int i = 1; i < args.length; ++i) {
          A.add(Integer.parseInt(args[i]));
        }
      } else {
        int n;
        if (args.length == 1) {
          n = Integer.parseInt(args[0]);
        } else {
          n = gen.nextInt(10000) + 1;
        }
        for (int i = 0; i < n; ++i) {
          A.add((gen.nextBoolean() ? -1 : 1) * gen.nextInt(n));
        }
      }
      Subarray result = findLongestIncreasingSubarray(A);
      System.out.println(result.start + " " + result.end);
      int len = 1;
      for (int i = 1; i < A.size(); ++i) {
        if (A.get(i) > A.get(i - 1)) {
          ++len;
        } else {
          len = 1;
        }
        assert(len <= result.end - result.start + 1);
      }
    }
  }
}
