package com.epi;

import com.epi.utils.BinaryOperators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.utils.Utils.*;
import static java.lang.Math.max;

public class BiggestProductNMinus1 {
  // @include
  public static int findBiggestProductNMinusOneProduct(List<Integer> A) {
    // Build forward product L, and backward product R.
    List<Integer> L = new ArrayList<>(A.size());
    List<Integer> R = new ArrayList<>(A.size());
    fill(L, A.size(), 0);
    fill(R, A.size(), 0);
    partialSum(A.iterator(), L.listIterator(), BinaryOperators.MULTIPLIES);
    partialSum(revListIterator(A), revListIterator(R),
        BinaryOperators.MULTIPLIES);

    // Find the biggest product of (n - 1) numbers.
    int maxProduct = Integer.MIN_VALUE;
    for (int i = 0; i < A.size(); ++i) {
      int forward = i > 0 ? L.get(i - 1) : 1;
      int backward = i + 1 < A.size() ? R.get(i + 1) : 1;
      maxProduct = max(maxProduct, forward * backward);
    }
    return maxProduct;
  }
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

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      List<Integer> A;
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
      } else {
        n = gen.nextInt(10) + 2;
      }
      A = new ArrayList<>(n);
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
