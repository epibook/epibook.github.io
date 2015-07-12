// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Math.ceil;

public class SpiralMatrixClockwise {
  // @include
  public static List<Integer> matrixInSpiralOrder(int[][] squareMatrix) {
    List<Integer> spiralOrdering = new ArrayList<>();
    for (int offset = 0; offset < ceil(0.5 * squareMatrix.length); ++offset) {
      matrixLayerInClockwise(squareMatrix, offset, spiralOrdering);
    }
    return spiralOrdering;
  }

  private static void matrixLayerInClockwise(int[][] squareMatrix, int offset,
                                             List<Integer> spiralOrdering) {
    if (offset == squareMatrix.length - offset - 1) {
      // squareMatrix has odd diemnsion, and we are at the center of squareMatrix.
      spiralOrdering.add(squareMatrix[offset][offset]);
    }

    for (int j = offset; j < squareMatrix.length - offset - 1; ++j) {
      spiralOrdering.add(squareMatrix[offset][j]);
    }
    for (int i = offset; i < squareMatrix.length - offset - 1; ++i) {
      spiralOrdering.add(squareMatrix[i][squareMatrix.length - offset - 1]);
    }
    for (int j = squareMatrix.length - offset - 1; j > offset; --j) {
      spiralOrdering.add(squareMatrix[squareMatrix.length - offset - 1][j]);
    }
    for (int i = squareMatrix.length - offset - 1; i > offset; --i) {
      spiralOrdering.add(squareMatrix[i][offset]);
    }
  }
  // @exclude

  private static void simpleTest() {
    int[][] A = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    List<Integer> goldenResult = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
    List<Integer> result = matrixInSpiralOrder(A);
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
    List<Integer> result = matrixInSpiralOrder(A);
    for (Integer a : result) {
      System.out.print(a + " ");
    }
    System.out.println();
  }
}
