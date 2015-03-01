// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

public class SpiralMatrix {
  // @include
  public static void printMatrixInSpiralOrder(int[][] A) {
    int[][] shift = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int dir = 0, x = 0, y = 0;

    for (int i = 0; i < A.length * A.length; ++i) {
      System.out.print(A[x][y] + " ");
      A[x][y] = 0;
      int nextX = x + shift[dir][0], nextY = y + shift[dir][1];
      if (nextX < 0 || nextX >= A.length || nextY < 0 || nextY >= A.length
          || A[nextX][nextY] == 0) {
        dir = (dir + 1) & 3;
        nextX = x + shift[dir][0];
        nextY = y + shift[dir][1];
      }
      x = nextX;
      y = nextY;
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
