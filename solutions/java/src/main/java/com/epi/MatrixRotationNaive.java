// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Random;

public class MatrixRotationNaive {

  private static void printMatrix(int[][] A) {
    for (int i = 0; i < A.length; ++i) {
      // simplePrint(A[i]);
      for (int j = 0; j < A.length; ++j) {
        System.out.print(String.format("A[%d, %d] = %d ", i, j, A[i][j]));
      }
      System.out.println();
    }
  }

  private static void checkAnswer(int[][] A) {
    int k = 1;
    for (int j = A.length - 1; j >= 0; --j) {
      for (int[] element : A) {
        assert (k++ == element[j]);
      }
    }
  }

  // @include
  public static void rotateMatrix(int[][] A) {
    for (int i = 0; i < (A.length / 2); ++i) {
      for (int j = i; j < A.length - i - 1; ++j) {
        int temp = A[i][j];
        A[i][j] = A[A.length - 1 - j][i];
        A[A.length - 1 - j][i] = A[A.length - 1 - i][A.length - 1 - j];
        A[A.length - 1 - i][A.length - 1 - j] = A[j][A.length - 1 - i];
        A[j][A.length - 1 - i] = temp;
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    int n;
    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
      int[][] A = new int[1 << n][1 << n];
      int k = 1;
      for (int i = 0; i < A.length; ++i) {
        for (int j = 0; j < A[i].length; ++j) {
          A[i][j] = k++;
        }
      }
      printMatrix(A);
      rotateMatrix(A);
      checkAnswer(A);
      printMatrix(A);
    } else {
      Random gen = new Random();
      for (int times = 0; times < 100; ++times) {
        n = gen.nextInt(10) + 1;
        int[][] A = new int[1 << n][1 << n];
        int k = 1;
        for (int i = 0; i < A.length; ++i) {
          for (int j = 0; j < A[i].length; ++j) {
            A[i][j] = k++;
          }
        }
        rotateMatrix(A);
        checkAnswer(A);
      }
    }
  }

}
