package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MaximumSubarrayInCircularArray {
  // @include
  public static int maxSubarraySumInCircular(List<Integer> A) {
    return Math.max(findMaxSubarray(A), findCircularMaxSubarray(A));
  }

  // Calculates the non-circular solution.
  private static int findMaxSubarray(List<Integer> A) {
    int maximumTill = 0, maximum = 0;
    for (Integer a : A) {
      maximumTill = Math.max(a, a + maximumTill);
      maximum = Math.max(maximum, maximumTill);
    }
    return maximum;
  }

  // Calculates the solution which is circular.
  private static int findCircularMaxSubarray(List<Integer> A) {
    // Maximum subarray sum starts at index 0 and ends at or before index i.
    ArrayList<Integer> maximumBegin = new ArrayList<>();
    int sum = A.get(0);
    maximumBegin.add(sum);
    for (int i = 1; i < A.size(); ++i) {
      sum += A.get(i);
      maximumBegin.add(
          Math.max(maximumBegin.get(maximumBegin.size() - 1), sum));
    }

    // Maximum subarray sum starts at index i + 1 and ends at the last element.
    List<Integer> maximumEnd
        = new ArrayList<>(Collections.nCopies(A.size(), 0));
    sum = 0;
    for (int i = A.size() - 2; i >= 0; --i) {
      sum += A.get(i + 1);
      maximumEnd.set(i, Math.max(maximumEnd.get(i + 1), sum));
    }

    // Calculates the maximum subarray which is circular.
    int circularMax = 0;
    for (int i = 0; i < A.size(); ++i) {
      circularMax
          = Math.max(circularMax, maximumBegin.get(i) + maximumEnd.get(i));
    }
    return circularMax;
  }
  // @exclude

  // O(n^2) solution
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
