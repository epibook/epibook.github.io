package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiggestProductNMinus1Math {
  // @include
  public static int findBiggestNMinusOneProduct(List<Integer> A) {
    int smallestPositiveIdx = -1;
    int negativeCount = 0, smallestNegativeIdx = -1, biggestNegativeIdx = -1;

    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i) < 0) {
        ++negativeCount;
        if (biggestNegativeIdx == -1 || A.get(biggestNegativeIdx) < A.get(i)) {
          biggestNegativeIdx = i;
        }
        if (smallestNegativeIdx == -1 || A.get(i) < A.get(smallestNegativeIdx)) {
          smallestNegativeIdx = i;
        }
      } else if (A.get(i) >= 0) {
        if (smallestPositiveIdx == -1 || A.get(i) < A.get(smallestPositiveIdx)) {
          smallestPositiveIdx = i;
        }
      }
    }

    int product = 1;
    int targetIdx = (negativeCount & 1) == 1
                        ? biggestNegativeIdx
                        : (smallestPositiveIdx != -1 ? smallestPositiveIdx
                                                     : smallestNegativeIdx);
    for (int i = 0; i < A.size(); ++i) {
      if (i != targetIdx) {
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
        n = Integer.valueOf(args[0]);
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
