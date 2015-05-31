// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Math.ceil;

public class SpiralMatrixClockwise {
  // @include
  public static List<Integer> MatrixInSpiralOrder(int[][] A) {
    List<Integer> result = new ArrayList<>();
    for (int offset = 0; offset < ceil(0.5 * A.length); ++offset) {
      MatrixClockwise(A, offset, result);
    }
    return result;
  }

  private static void MatrixClockwise(int[][] A, int offset,
                                      List<Integer> result) {
    if (offset == A.length - offset - 1) {
      // A has odd diemnsion, and we are at the center of the matrix A.
      result.add(A[offset][offset]);
    }

    for (int j = offset; j < A.length - offset - 1; ++j) {
      result.add(A[offset][j]);
    }
    for (int i = offset; i < A.length - offset - 1; ++i) {
      result.add(A[i][A.length - offset - 1]);
    }
    for (int j = A.length - offset - 1; j > offset; --j) {
      result.add(A[A.length - offset - 1][j]);
    }
    for (int i = A.length - offset - 1; i > offset; --i) {
      result.add(A[i][offset]);
    }
  }
  // @exclude

  private static void simpleTest() {
    int[][] A = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    List<Integer> goldenResult = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
    List<Integer> result = MatrixInSpiralOrder(A);
    assert(result.equals(goldenResult));
  }

  public static void main(String[] args) {
    simpleTest();
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
    List<Integer> result = MatrixInSpiralOrder(A);
    for (Integer a : result) {
      System.out.print(a + " ");
    }
    System.out.println();
  }
}
