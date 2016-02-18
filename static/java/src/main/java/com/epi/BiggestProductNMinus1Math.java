package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiggestProductNMinus1Math {
  // @include
  public static int findBiggestNMinusOneProduct(List<Integer> A) {
    int leastNonnegativeIdx = -1;
    int numberOfNegatives = 0, greatestNegativeIdx = -1, leastNegativeIdx = -1;

    // Identify the least negative, greatest negative, and least nonnegative
    // entries.
    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i) < 0) {
        ++numberOfNegatives;
        if (leastNegativeIdx == -1 || A.get(leastNegativeIdx) < A.get(i)) {
          leastNegativeIdx = i;
        }
        if (greatestNegativeIdx == -1
            || A.get(i) < A.get(greatestNegativeIdx)) {
          greatestNegativeIdx = i;
        }
      } else if (A.get(i) >= 0) {
        if (leastNonnegativeIdx == -1
            || A.get(i) < A.get(leastNonnegativeIdx)) {
          leastNonnegativeIdx = i;
        }
      }
    }

    int product = 1;
    int IdxToSkip = (numberOfNegatives % 2) != 0
                        ? leastNegativeIdx
                        // Check if there are any nonnegative entry.
                        : (leastNonnegativeIdx != -1 ? leastNonnegativeIdx
                                                     : greatestNegativeIdx);
    for (int i = 0; i < A.size(); ++i) {
      if (i != IdxToSkip) {
        product *= A.get(i);
      }
    }
    return product;
  }
  // @exclude

  // n^2 checking
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
    System.out.println(maxProduct);
    return maxProduct;
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 100000; ++times) {
      int n;
      List<Integer> A;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        // Get a random number from [2, 11]
        n = gen.nextInt(10) + 2;
      }
      A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        // Get a random number from [-9, 9]
        A.add(gen.nextInt(19) - 9);
        System.out.print(A.get(i) + " ");
      }
      System.out.println();
      int res = findBiggestNMinusOneProduct(A);
      System.out.println(res);
      assert res == checkAns(A);
    }
  }
}
