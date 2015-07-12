// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpiralMatrix {
  // @include
  public static List<Integer> matrixInSpiralOrder(int[][] squareMatrix) {
    int[][] shift = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int dir = 0, x = 0, y = 0;
    List<Integer> spiralOrdering = new ArrayList<>();

    for (int i = 0; i < squareMatrix.length * squareMatrix.length; ++i) {
      spiralOrdering.add(squareMatrix[x][y]);
      squareMatrix[x][y] = 0;
      int nextX = x + shift[dir][0], nextY = y + shift[dir][1];
      if (nextX < 0 || nextX >= squareMatrix.length || nextY < 0 ||
          nextY >= squareMatrix.length || squareMatrix[nextX][nextY] == 0) {
        dir = (dir + 1) % 4;
        nextX = x + shift[dir][0];
        nextY = y + shift[dir][1];
      }
      x = nextX;
      y = nextY;
    }
    return spiralOrdering;
  }
  // @exclude

  private static void simpleTest() {
    int[][] A = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    List<Integer> goldenResult = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
    List<Integer> result = matrixInSpiralOrder(A);
    assert(result.equals(goldenResult));
  }

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
    List<Integer> result = matrixInSpiralOrder(A);
    for (Integer a : result) {
      System.out.print(a + " ");
    }
    System.out.println();
  }
}
