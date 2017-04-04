package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MaxSumSubarray {
  // @include
  public static int findMaximumSubarray(List<Integer> A) {
    int minSum = 0, sum = 0, maxSum = 0;
    for (int i = 0; i < A.size(); ++i) {
      sum += A.get(i);
      if (sum < minSum) {
        minSum = sum;
      }
      if (sum - minSum > maxSum) {
        maxSum = sum - minSum;
      }
    }
    return maxSum;
  }
  // @exclude

  private static List<Integer> randVector(int len) {
    Random r = new Random();
    List<Integer> ret = new ArrayList<>();
    while (len-- != 0) {
      ret.add(r.nextInt(2001) - 1000);
    }
    return ret;
  }

  private static void checkMaxSum(List<Integer> A, int maxSum) {
    for (int i = 0; i < A.size(); ++i) {
      int sum = 0;
      for (int j = i; j < A.size(); ++j) {
        sum += A.get(j);
        assert(sum <= maxSum);
      }
    }
  }

  private static void simpleTest() {
    List<Integer> B = Arrays.asList(1);
    int maxSum = findMaximumSubarray(B);
    checkMaxSum(B, maxSum);
    B = Arrays.asList(-5);
    maxSum = findMaximumSubarray(B);
    checkMaxSum(B, maxSum);
    B = Arrays.asList(0);
    maxSum = findMaximumSubarray(B);
    checkMaxSum(B, maxSum);
    B = Arrays.asList(0, 0);
    maxSum = findMaximumSubarray(B);
    checkMaxSum(B, maxSum);
    B = Arrays.asList(0, 0, 0);
    maxSum = findMaximumSubarray(B);
    checkMaxSum(B, maxSum);
    B = Arrays.asList(0, -5, 0);
    maxSum = findMaximumSubarray(B);
    checkMaxSum(B, maxSum);
    B = Arrays.asList(-2, -1);
    maxSum = findMaximumSubarray(B);
    checkMaxSum(B, maxSum);
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      List<Integer> A;
      if (args.length == 0) {
        A = randVector(r.nextInt(10000) + 1);
      } else if (args.length == 1) {
        int n = Integer.parseInt(args[0]);
        A = randVector(n);
      } else {
        A = new ArrayList<>();
        for (String arg : args) {
          A.add(Integer.parseInt(arg));
        }
      }
      int maxSum = findMaximumSubarray(A);
      checkMaxSum(A, maxSum);
    }
  }
}
