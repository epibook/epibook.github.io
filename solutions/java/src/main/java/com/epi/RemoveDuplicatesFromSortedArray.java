package com.epi;

import java.util.*;

public class RemoveDuplicatesFromSortedArray {
  // @include
  public static int removeDuplicates(int[] A) {
    if (A.length == 0) {
      return 0;
    }

    int writeIndex = 0;
    for (int i = 1; i < A.length; ++i) {
      if (A[writeIndex] != A[i]) {
        A[++writeIndex] = A[i];
      }
    }
    return writeIndex + 1;
  }
  // @exclude

  private static void checkAns(int[] A, int n) {
    for (int i = 1; i < n; ++i) {
      assert(A[i - 1] != A[i]);
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10000) + 1;
    }
    for (int times = 0; times < 1000; ++times) {
      int[] A = new int[n];
      for (int i = 0; i < n; i++) {
        A[i] = r.nextInt(2001) - 1000;
      }
      Arrays.sort(A);
      Set<Integer> unique = new HashSet<>();
      for (int a : A) {
        unique.add(a);
      }
      int size = removeDuplicates(A);
      assert(size == unique.size());
      checkAns(A, size);
      System.out.println(size);
    }
  }
}
