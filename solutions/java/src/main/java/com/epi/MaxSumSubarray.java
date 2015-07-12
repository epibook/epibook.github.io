package com.epi;

import com.epi.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MaxSumSubarray {
  // @include
  public static Pair<Integer, Integer> findMaximumSubarray(List<Integer> A) {
    // A[range.first : range.second - 1] will be the maximum subarray.
    Pair<Integer, Integer> range = new Pair<>(0, 0);
    int minIdx = -1, minSum = 0, sum = 0, maxSum = 0;
    for (int i = 0; i < A.size(); ++i) {
      sum += A.get(i);
      if (sum < minSum) {
        minSum = sum;
        minIdx = i;
      }
      if (sum - minSum > maxSum) {
        maxSum = sum - minSum;
        range = new Pair<>(minIdx + 1, i + 1);
      }
    }
    return range;
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

  private static void checkMaxSum(List<Integer> A, Pair<Integer, Integer> range) {
    int maxSum = 0;
    for (int i = range.getFirst(); i < range.getSecond(); ++i) {
      maxSum += A.get(i);
    }
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
    Pair<Integer, Integer> range = findMaximumSubarray(B);
    System.out.println(range);
    checkMaxSum(B, range);
    B = Arrays.asList(-5);
    range = findMaximumSubarray(B);
    System.out.println(range);
    B = Arrays.asList(0);
    range = findMaximumSubarray(B);
    System.out.println(range);
    B = Arrays.asList(0, 0);
    range = findMaximumSubarray(B);
    System.out.println(range);
    B = Arrays.asList(0, 0, 0);
    range = findMaximumSubarray(B);
    System.out.println(range);
    B = Arrays.asList(0, -5, 0);
    range = findMaximumSubarray(B);
    System.out.println(range);
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
      Pair<Integer, Integer> range = findMaximumSubarray(A);
      System.out.println(range);
      checkMaxSum(A, range);
    }
  }
}
