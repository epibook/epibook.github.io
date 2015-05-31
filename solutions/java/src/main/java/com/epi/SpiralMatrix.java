// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpiralMatrix {
  // @include
  public static List<Integer> MatrixInSpiralOrder(int[][] A) {
    int[][] shift = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int dir = 0, x = 0, y = 0;
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < A.length * A.length; ++i) {
      result.add(A[x][y]);
      A[x][y] = 0;
      int nextX = x + shift[dir][0], nextY = y + shift[dir][1];
      if (nextX < 0 || nextX >= A.length || nextY < 0 || nextY >= A.length ||
          A[nextX][nextY] == 0) {
        dir = (dir + 1) & 3;
        nextX = x + shift[dir][0];
        nextY = y + shift[dir][1];
      }
      x = nextX;
      y = nextY;
    }
    return result;
  }
  // @exclude

  private static void simpleTest() {
    int[][] A = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    List<Integer> goldenResult = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
    List<Integer> result = MatrixInSpiralOrder(A);
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
    List<Integer> result = MatrixInSpiralOrder(A);
    for (Integer a : result) {
      System.out.print(a + " ");
    }
    System.out.println();
  }
}
