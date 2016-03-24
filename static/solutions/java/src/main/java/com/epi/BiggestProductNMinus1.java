package com.epi;
/*
    @slug
    biggest-product-n-minus-1-entries

    @title
    Biggest product of n-1 entries in an array.

    @problem
    Suppose you are given an array A of integers, and are asked to find the
   largest product
    that can be made by multiplying all but one of the entries in A. (You cannot
   use an
    entry more than once.) For example, if A = <3, 2, 5, 4>, the result is 3 x 5
   x 4 = 60, if
    A = <3, 2,-1, 4>, the result is 3 x 2 x 4 = 24, and if A = <3, 2, -1, 4,-1,
   6>,
    the result is 3 x -1 x 4 x -1 x 6 = 72.
    <p>

    One approach is to form the product P of all the elements, and then find the
    maximum of P/A[i] over all i. This takes n - 1 multiplications (to form P)
   and n
    divisions (to compute each P/A[i]). Suppose because of finite precision
   considerations
    we cannot use a division-based approach; we can only use multiplications.
   The brute-force
    solution entails computing all n products of n - 1 elements; each such
   product
    takes n - 2 multiplications, i.e., O(n^2) time complexity.
    <p>

    Given an array A of length n whose entries are integers, compute the largest
   product that can be made using n - 1 entries in A. You cannot use an entry
   more than
   once.  Array entries may be positive, negative, or 0. Your algorithm cannot
   use the
   division operator, explicitly or implicitly.

    @hint
    Consider the products of the first i - 1 and the last n - i elements.
    Alternatively, count the number of negative entries and zero entries.

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BiggestProductNMinus1 {
  // @include
  // @judge-include-display
  public static int findBiggestProductNMinusOneProduct(List<Integer> A) {
    // @judge-exclude-display
    // Builds forward product L, and backward product R.
    List<Integer> L = new ArrayList<>(A.size());
    int product = 1;
    for (int i = 0; i < A.size(); ++i) {
      product *= A.get(i);
      L.add(product);
    }
    product = 1;
    List<Integer> R = new ArrayList<>(Collections.nCopies(A.size(), 0));
    for (int i = A.size() - 1; i >= 0; --i) {
      product *= A.get(i);
      R.set(i, product);
    }

    // Finds the biggest product of (n - 1) numbers.
    int maxProduct = Integer.MIN_VALUE;
    for (int i = 0; i < A.size(); ++i) {
      int forward = i > 0 ? L.get(i - 1) : 1;
      int backward = i + 1 < A.size() ? R.get(i + 1) : 1;
      maxProduct = Math.max(maxProduct, forward * backward);
    }
    return maxProduct;
    // @judge-include-display
  }
  // @judge-exclude-display
  // @exclude

  // n^2 checking.
  private static int checkAns(List<Integer> A) {
    int maxProduct = Integer.MIN_VALUE;
    for (int i = 0; i < A.size(); ++i) {
      int product = 1;
      for (int j = 0; j < i; ++j) {
        product *= A.get(j);
      }
      for (int j = i + 1; j < A.size(); ++j) {
        product *= A.get(j);
      }
      if (product > maxProduct) {
        maxProduct = product;
      }
    }
    return maxProduct;
  }

  private static void check(List<Integer> A) {
    int got = findBiggestProductNMinusOneProduct(A);
    int expected = checkAns(A);
    if (got != expected) {
      System.err.println("Your program computed the wrong result for input "
                         + A);
      System.err.println("Expected " + expected);
      System.err.println("Got " + got);
      System.exit(-1);
    }
  }

  private static void directedTest() {
    check(Arrays.asList(1, 2, 3));
    check(Arrays.asList(3, 2, 1));
    check(Arrays.asList(1, 1, 1));
    check(Arrays.asList(-1, 1, 1));
    check(Arrays.asList(-1, 1, -1));
    check(Arrays.asList(-10, 12, -1001));
    check(Arrays.asList(12, -10, 12, -1001));
    check(Arrays.asList(1));
  }

  private static void stressTest() {
    int N = 100000;
    List<Integer> A = new ArrayList<>(Collections.nCopies(N, new Integer(1)));
    if (findBiggestProductNMinusOneProduct(A) != 1) {
      System.err.println(
          "Your program computed the wrong result for an array consisting of "
          + N + " 1s.");
      System.exit(-1);
    }
  }

  public static void main(String[] args) {
    directedTest();
    stressTest();
    Random gen = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(10) + 2;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(gen.nextInt(19) - 9);
        System.out.print(A.get(i) + " ");
      }
      System.out.println();
      int res = findBiggestProductNMinusOneProduct(A);
      assert res == checkAns(A);
      System.out.println(res);
    }
  }
}
