// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpiralMatrixClockwise {
  // @include
  public static List<Integer> matrixInSpiralOrder(
      List<List<Integer>> squareMatrix) {
    List<Integer> spiralOrdering = new ArrayList<>();
    for (int offset = 0; offset < Math.ceil(0.5 * squareMatrix.size());
         ++offset) {
      matrixLayerInClockwise(squareMatrix, offset, spiralOrdering);
    }
    return spiralOrdering;
  }

  private static void matrixLayerInClockwise(List<List<Integer>> squareMatrix,
                                             int offset,
                                             List<Integer> spiralOrdering) {
    if (offset == squareMatrix.size() - offset - 1) {
      // squareMatrix has odd dimension, and we are at its center.
      spiralOrdering.add(squareMatrix.get(offset).get(offset));
      return;
    }

    for (int j = offset; j < squareMatrix.size() - offset - 1; ++j) {
      spiralOrdering.add(squareMatrix.get(offset).get(j));
    }
    for (int i = offset; i < squareMatrix.size() - offset - 1; ++i) {
      spiralOrdering.add(
          squareMatrix.get(i).get(squareMatrix.size() - offset - 1));
    }
    for (int j = squareMatrix.size() - offset - 1; j > offset; --j) {
      spiralOrdering.add(
          squareMatrix.get(squareMatrix.size() - offset - 1).get(j));
    }
    for (int i = squareMatrix.size() - offset - 1; i > offset; --i) {
      spiralOrdering.add(squareMatrix.get(i).get(offset));
    }
  }
  // @exclude

  private static void simpleTest() {
    List<List<Integer>> A = Arrays.asList(
        Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9));
    List<Integer> goldenResult = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
    List<Integer> result = matrixInSpiralOrder(A);
    assert(result.equals(goldenResult));
  }

  public static void main(String[] args) {
    simpleTest();
    Random gen = new Random();
    int N;
    if (args.length == 1) {
      N = Integer.parseInt(args[0]);
    } else {
      N = gen.nextInt(50) + 1;
    }
    List<List<Integer>> A = new ArrayList<>(N);
    int x = 1;
    for (int i = 0; i < N; ++i) {
      A.add(new ArrayList(N));
      for (int j = 0; j < N; ++j) {
        A.get(i).add(x++);
      }
    }
    List<Integer> result = matrixInSpiralOrder(A);
    for (Integer a : result) {
      System.out.print(a + " ");
    }
    System.out.println();
  }
}
