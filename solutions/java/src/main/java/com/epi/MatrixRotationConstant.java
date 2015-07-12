// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

import static com.epi.utils.Utils.copy;

public class MatrixRotationConstant {
  private static void rotateMatrix(int[][] A) {
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

  private static void checkAnswer(int[][] A, int[][] B) {
    RotatedMatrix rA = new RotatedMatrix(A);
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < A.length; ++j) {
        assert(rA.readEntry(i, j) == B[i][j]);
      }
    }
  }

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
      int[][] B = copy(A);
      rotateMatrix(B);
      checkAnswer(A, B);
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
        int[][] B = copy(A);
        rotateMatrix(B);
        checkAnswer(A, B);
      }
    }
  }
}

// @include
class RotatedMatrix {
  private int[][] wrappedSquareMatrix;

  public RotatedMatrix(int[][] squareMatrix) {
    this.wrappedSquareMatrix = squareMatrix;
  }

  public int readEntry(int i, int j) {
    return wrappedSquareMatrix[wrappedSquareMatrix.length - 1 - j][i];
  }

  public void writeEntry(int i, int j, int v) {
    wrappedSquareMatrix[wrappedSquareMatrix.length - 1 - j][i] = v;
  }
}
// @exclude
