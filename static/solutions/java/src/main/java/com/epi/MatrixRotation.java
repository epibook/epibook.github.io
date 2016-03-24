// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

import static com.epi.utils.Utils.copy;

public class MatrixRotation {
  private static void checkAnswer(int[][] A) {
    int k = 1;
    for (int j = A.length - 1; j >= 0; --j) {
      for (int[] element : A) {
        assert(k++ == element[j]);
      }
    }
  }

  // @include
  public static void rotateMatrix(int[][] A) {
    rotateMatrixHelper(A, 0, A.length, 0, A.length);
  }

  private static void rotateMatrixHelper(int[][] a, int xs, int xe, int ys,
                                         int ye) {
    if (xe > xs + 1) {
      int midX = xs + ((xe - xs) / 2), midY = ys + ((ye - ys) / 2);
      // Move submatrices.
      int[][] C = new int[midX - xs][midY - ys];
      copyMatrix(C, 0, C.length, 0, C.length, a, xs, ys);
      copyMatrix(a, xs, midX, ys, midY, a, midX, ys);
      copyMatrix(a, midX, xe, ys, midY, a, midX, midY);
      copyMatrix(a, midX, xe, midY, ye, a, xs, midY);
      copyMatrix(a, xs, midX, midY, ye, C, 0, 0);

      // Recursively rotate submatrices.
      rotateMatrixHelper(a, xs, midX, ys, midY);
      rotateMatrixHelper(a, xs, midX, midY, ye);
      rotateMatrixHelper(a, midX, xe, midY, ye);
      rotateMatrixHelper(a, midX, xe, ys, midY);
    }
  }

  private static void copyMatrix(int[][] a, int axs, int axe, int ays, int aye,
                                 int[][] s, int sx, int sy) {
    for (int i = 0; i < axe - axs; ++i) {
      copy(s[sx + i], sy, sy + aye - ays, a[axs + i], ays);
    }
  }
  // @exclude

  public static void main(String[] args) {
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      int[][] A = new int[1 << n][1 << n];
      int k = 1;
      for (int i = 0; i < A.length; ++i) {
        for (int j = 0; j < A[i].length; ++j) {
          A[i][j] = k++;
        }
      }
      rotateMatrix(A);
      checkAnswer(A);
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
        // printMatrix(A);
        rotateMatrix(A);
        checkAnswer(A);
        // printMatrix(A);
      }
    }
  }
}
