// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Random;

import static java.lang.Math.ceil;

public class SpiralMatrixClockwise {
  // @include
  public static void printMatrixInSpiralOrder(int[][] A) {
    for (int offset = 0; offset < ceil(0.5 * A.length); ++offset) {
      printMatrixClockwise(A, offset);
    }
  }

  private static void printMatrixClockwise(int[][] A, int offset) {
    if (offset == A.length - offset - 1) {
      // A has odd diemnsion, and we are at the center of the matrix A.
      System.out.print(A[offset][offset]);
    }

    for (int j = offset; j < A.length - offset - 1; ++j) {
      System.out.print(A[offset][j] + " ");
    }
    for (int i = offset; i < A.length - offset - 1; ++i) {
      System.out.print(A[i][A.length - offset - 1] + " ");
    }
    for (int j = A.length - offset - 1; j > offset; --j) {
      System.out.print(A[A.length - offset - 1][j] + " ");
    }
    for (int i = A.length - offset - 1; i > offset; --i) {
      System.out.print(A[i][offset] + " ");
    }
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    int N;
    if (args.length == 1) {
      N = Integer.valueOf(args[0]);
    } else {
      N = gen.nextInt(50) + 1;
    }
    int[][] A = new int[N][N];
    int x = 1;
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < N; ++j) {
        A[i][j] = x++;
      }
    }
    printMatrixInSpiralOrder(A);
  }
}
