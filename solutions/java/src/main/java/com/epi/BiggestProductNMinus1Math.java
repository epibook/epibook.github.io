package com.epi;

import java.util.Random;

public class BiggestProductNMinus1Math {
  // @include
  public static int findBiggestNMinusOneProduct(int[] A) {
    int zeroCount = 0, zeroIdx = - 1;
    int positiveCount = 0, smallestPositiveIdx = -1;
    int negativeCount = 0, smallNegativeIdx = -1, biggestNegativeIdx = -1;

    for (int i = 0; i < A.length; ++i) {
      if (A[i] < 0) {
        ++negativeCount;
        if (smallNegativeIdx == -1 || A[i] < A[smallNegativeIdx]) {
          smallNegativeIdx = i;
        }
        if (biggestNegativeIdx == -1 || A[biggestNegativeIdx] < A[i]) {
          biggestNegativeIdx = i;
        }
      } else if (A[i] == 0) {
        zeroIdx = i;
        ++zeroCount;
      } else { // A[i] > 0.
        ++positiveCount;
        if (smallestPositiveIdx == -1 || A[i] < A[smallestPositiveIdx]) {
          smallestPositiveIdx = i;
        }
      }
    }

    // Try to find a number whose elimination could maximize the product of
    // the remaining (n - 1) numbers.
    int x; // Stores the idx of eliminated one.
    if (zeroCount >= 2) {
      return 0;
    } else if (zeroCount == 1) {
      if ((negativeCount & 1) > 0) { // Odd number of negatives.
        return 0;
      } else {
        x = zeroIdx;
      }
    } else { // No zero in A.
      if ((negativeCount & 1) > 0) { // Odd number of negatives.
        x = biggestNegativeIdx;
      } else { // Even number of negatives.
        if (positiveCount > 0) {
          x = smallestPositiveIdx;
        } else {
          x = smallNegativeIdx;
        }
      }
    }

    int product = 1;
    for (int i = 0; i < A.length; ++i) {
      if (i != x) {
        product *= A[i];
      }
    }
    return product;
  }
  // @exclude

  // n^2 checking
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
    System.out.println(maxProduct);
    return maxProduct;
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 100000; ++times) {
      int n;
      int[] A;
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
      } else {
        // Get a random number from [2, 11]
        n = gen.nextInt(10) + 2;
      }
      A = new int[n];
      for (int i = 0; i < n; ++i) {
        // Get a random number from [-9, 9]
        A[i] = gen.nextInt(19) - 9;
        System.out.print(A[i] + " ");

      }
      System.out.println();
      int res = findBiggestNMinusOneProduct(A);
      System.out.println(res);
      assert res == checkAns(A);
    }
  }
}
