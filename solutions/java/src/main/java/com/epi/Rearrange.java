package com.epi;

import java.util.Random;

public class Rearrange {
  // @include
  public static void rearrange(int[] A) {
    for (int i = 1; i < A.length; ++i) {
      if (((i % 2) == 0 && A[i - 1] < A[i]) ||
          ((i % 2) == 1 && A[i - 1] > A[i])) {
        Integer temp = A[i - 1];
        A[i - 1] = A[i];
        A[i] = temp;
      }
    }
  }
  // @exclude

  private static void checkAnswer(int[] A) {
    for (int i = 0; i < A.length; ++i) {
      if ((i % 2) != 0) {
        assert(A[i] >= A[i - 1]);
        if (i < A.length - 1) {
          assert(A[i] >= A[i + 1]);
        }
      } else {
        if (i > 0) {
          assert(A[i - 1] >= A[i]);
        }
        if (i < A.length - 1) {
          assert(A[i + 1] >= A[i]);
        }
      }
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = r.nextInt(2 * n + 1) - n;
      }
      rearrange(A);
      /*
       * System.out.println(A);
       */
      checkAnswer(A);
    }
  }
}
