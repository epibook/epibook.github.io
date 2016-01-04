package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MergeTwoSortedArraysInPlace {
  // @include
  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    int a = m - 1, b = n - 1, writeIdx = m + n - 1;
    while (a >= 0 && b >= 0) {
      A.set(writeIdx--, A.get(a) > B.get(b) ? A.get(a--) : B.get(b--));
    }
    while (b >= 0) {
      A.set(writeIdx--, B.get(b--));
    }
  }
  // @exclude

  private static void checkAns(List<Integer> A) {
    for (int i = 1; i < A.size(); ++i) {
      assert(A.get(i - 1) <= A.get(i));
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
      List<Integer> A = new ArrayList<>(m + n);
      for (int i = 0; i < m; ++i) {
        A.add(r.nextInt((m + n) * 2 + 1) - (m + n));
      }
      List<Integer> B = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        B.add(r.nextInt((m + n) * 2 + 1) - (m + n));
      }
      Collections.sort(A);
      for (int i = m; i < m + n; ++i) {
        A.add(null);
      }
      Collections.sort(B);
      mergeTwoSortedArrays(A, m, B, n);
      checkAns(A);
    }
  }
}
