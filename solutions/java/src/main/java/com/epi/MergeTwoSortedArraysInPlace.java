package com.epi;

import java.util.Arrays;
import java.util.Random;

public class MergeTwoSortedArraysInPlace {
  // @include
  public static void mergeTwoSortedArrays(int A[], int m, int B[], int n) {
    int a = m - 1, b = n - 1, writeIdx = m + n - 1;
    while (a >= 0 && b >= 0) {
      A[writeIdx--] = A[a] > B[b] ? A[a--] : B[b--];
    }
    while (b >= 0) {
      A[writeIdx--] = B[b--];
    }
  }
  // @exclude

  private static void checkAns(int[] A) {
    for (int i = 1; i < A.length; ++i) {
      assert(A[i - 1] <= A[i]);
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int m, n;
      if (args.length == 2) {
        m = Integer.parseInt(args[0]);
        n = Integer.parseInt(args[1]);
      } else {
        m = r.nextInt(10001);
        n = r.nextInt(10001);
      }
      int[] A = new int[m + n];
      int[] B = new int[n];
      for (int i = 0; i < m; ++i) {
        A[i] = r.nextInt((m + n) * 2 + 1) - (m + n);
      }
      for (int i = 0; i < n; ++i) {
        B[i] = r.nextInt((m + n) * 2 + 1) - (m + n);
      }
      Arrays.sort(A, 0, m);
      Arrays.sort(B);
      mergeTwoSortedArrays(A, m, B, n);
      checkAns(A);
    }
  }
}
