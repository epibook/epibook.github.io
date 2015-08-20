package com.epi;

import java.util.Arrays;
import java.util.Random;

public class FirstMissingPositive {
  private static int checkAns(int[] A) {
    Arrays.sort(A);
    int target = 1;
    for (int a : A) {
      if (a > 0) {
        if (a == target) {
          ++target;
        } else if (a > target) {
          return target;
        }
      }
    }
    return target;
  }

  // @include
  public static int findFirstMissingPositive(int[] A) {
    // Record which values are present by writing A[i] to index A[i] - 1 if A[i]
    // is between 1 and A.length, inclusive. We save the value at index
    // A[i] - 1 by swapping it with the entry at i. If A[i] is negative or
    // greater than n, we just advance i.
    int i = 0;
    while (i < A.length) {
      if (A[i] > 0 && A[i] <= A.length && A[A[i] - 1] != A[i]) {
        int temp = A[A[i] - 1];
        A[A[i] - 1] = A[i];
        A[i] = temp;
      } else {
        ++i;
      }
    }

    // Second pass through A to search for the first index i such that
    // A[i] != i+1, indicating that i + 1 is absent. If all numbers between 1
    // and A.length are present, the smallest missing positive is A.length + 1.
    for (i = 0; i < A.length; ++i) {
      if (A[i] != i + 1) {
        return i + 1;
      }
    }
    return A.length + 1;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(500001);
      }
      int[] A = new int[n];
      for (int i = 0; i < n; i++) {
        A[i] = r.nextInt(n + 1);
      }
      System.out.println("n = " + n);
      findFirstMissingPositive(A);
      assert(findFirstMissingPositive(A) == checkAns(A));
    }
  }
}
