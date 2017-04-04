package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaximumSubarrayInCircularArrayConstantSpace {
  // @include
  private interface IntegerComparator {
    Integer compare(Integer o1, Integer o2);
  }

  private static class MaxComparator implements IntegerComparator {
    @Override
    public Integer compare(Integer o1, Integer o2) {
      return o1 > o2 ? o1 : o2;
    }
  }

  private static class MinComparator implements IntegerComparator {
    @Override
    public Integer compare(Integer o1, Integer o2) {
      return o1 > o2 ? o2 : o1;
    }
  }

  public static int maxSubarraySumInCircular(List<Integer> A) {
    // Finds the max in non-circular case and circular case.
    int accumulate = 0;
    for (int a : A) {
      accumulate += a;
    }
    return Math.max(
        findOptimumSubarrayUsingComp(A, new MaxComparator()),
        accumulate - findOptimumSubarrayUsingComp(A, new MinComparator()));
  }

  private static int findOptimumSubarrayUsingComp(List<Integer> A,
                                                  IntegerComparator comp) {
    int till = 0, overall = 0;
    for (int a : A) {
      till = comp.compare(a, a + till);
      overall = comp.compare(overall, till);
    }
    return overall;
  }
  // @exclude

  // O(n^2) solution.
  private static int checkAns(List<Integer> A) {
    int ans = 0;
    for (int i = 0; i < A.size(); ++i) {
      int sum = 0;
      for (int j = 0; j < A.size(); ++j) {
        sum += A.get((i + j) % A.size());
        ans = Math.max(ans, sum);
      }
    }
    System.out.println("correct answer = " + ans);
    return ans;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      ArrayList<Integer> A = new ArrayList<>();
      if (args.length > 1) {
        for (int i = 1; i < args.length; ++i) {
          A.add(Integer.parseInt(args[i]));
        }
      } else {
        if (args.length == 1) {
          n = Integer.parseInt(args[0]);
        } else {
          n = r.nextInt(10000) + 1;
        }
        while (n-- != 0) {
          A.add(r.nextInt(20001) - 10000);
        }
      }
      int ans = maxSubarraySumInCircular(A);
      // System.out.println(A);
      System.out.println("maximum sum = " + ans);
      assert(ans == checkAns(A));
    }
  }
}
