package com.epi;

import com.epi.utils.BinaryOperators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.utils.Utils.*;

public class BiggestProductNMinus1 {
  // @include
  public static int findBiggestProductNMinusOneProduct(int[] A) {
    // Builds forward product L, and backward product R.
    int[] L = new int[A.length];
    int product = 1;
    for (int i = 0; i < A.length; ++i) {
      product *= A[i];
      L[i] = product;
    }
    product = 1;
    int[] R = new int[A.length];
    for (int i = A.length - 1; i >= 0; --i) {
      product *= A[i];
      R[i] = product;
    }

    // Finds the biggest product of (n - 1) numbers.
    int maxProduct = Integer.MIN_VALUE;
    for (int i = 0; i < A.length; ++i) {
      int forward = i > 0 ? L[i - 1] : 1;
      int backward = i + 1 < A.length ? R[i + 1] : 1;
      maxProduct = Math.max(maxProduct, forward * backward);
    }
    return maxProduct;
  }
  // @exclude

  // n^2 checking.
  private static int checkAns(int[] A) {
    int maxProduct = Integer.MIN_VALUE;
    for (int i = 0; i < A.length; ++i) {
      int product = 1;
      for (int j = 0; j < i; ++j) {
        product *= A[j];
      }
      for (int j = i + 1; j < A.length; ++j) {
        product *= A[j];
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
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
      } else {
        n = gen.nextInt(10) + 2;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = gen.nextInt(19) - 9;
        System.out.print(A[i] + " ");
      }
      System.out.println();
      int res = findBiggestProductNMinusOneProduct(A);
      assert res == checkAns(A);
      System.out.println(res);
    }
  }
}
