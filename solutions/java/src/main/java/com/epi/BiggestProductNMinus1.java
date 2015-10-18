package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BiggestProductNMinus1 {
  // @include
  public static int findBiggestProductNMinusOneProduct(List<Integer> A) {
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
