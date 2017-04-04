package com.epi;
/*
    @slug
    3sum

    @title
    The three sum problem.

    @problem
    Design an algorithm that takes as input an array and a number, and
   determines if
    there are three entries in A (not necessarily distinct) which add up to the
   specified
    number. <p>

    For example, if the array is <2, 3, 5, 7, 11> then there are three entries
   in the
    56 array which add up to 21 (3, 7, 11 and 5, 5, 11). (Note that we can use 5
   twice, since
    the problem statement said we can use the same entry more than once.)
   However, no
    three entries add up to 9.

    @hint
    How would you check if A[i] is part of a triple that 3-creates T?

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ThreeSum {
  // @include
  // @judge-include-display
  public static boolean hasThreeSum(List<Integer> A, int t) {
    // @judge-exclude-display
    Collections.sort(A);
    for (Integer a : A) {
      // Finds if the sum of two numbers in A equals to t - a.
      if (TwoSum.hasTwoSum(A, t - a)) {
        return true;
      }
    }
    return false;
    // @judge-include-display
  }
  // @judge-exclude-display
  // @exclude

  // n^3 solution.
  public static boolean checkAns(List<Integer> A, int t) {
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.size(); ++j) {
        for (Integer aA : A) {
          if (A.get(i) + A.get(j) + aA == t) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private static void check(List<Integer> A, int t) {
    boolean expected = checkAns(A, t);
    boolean got = hasThreeSum(A, t);
    if (expected != got) {
      System.err.println("Incorrect answer for target " + t);
      System.err.println("A = " + A);
      System.err.println("Expected " + expected + ", you computed " + got);
      System.exit(-1);
    }
  }

  private static void directedTests() {
    check(Arrays.asList(1, 2, 3), 6);
    check(Arrays.asList(1, 1, 1), 1);
    check(Arrays.asList(1, 1, 1), 3);
    check(Arrays.asList(1, 1), 3);
    check(Arrays.asList(1, 1), 3);
    check(Arrays.asList(1, 1, 2, 2, 3, 3), 6);
  }

  private static void stressTest() {
    int N = 1000000;
    List<Integer> A = new ArrayList<>(Collections.nCopies(N, new Integer(3)));
    check(A, 9);
  }

  public static void main(String[] args) {
    directedTests();
    stressTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, T;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        T = gen.nextInt(n - 1);
      } else {
        n = gen.nextInt(10000) + 1;
        T = gen.nextInt(n - 1);
      }

      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(gen.nextInt(200000) - 100000);
      }
      System.out.println(hasThreeSum(A, T) ? "true" : "false");
      check(A, T);
    }
  }
}
