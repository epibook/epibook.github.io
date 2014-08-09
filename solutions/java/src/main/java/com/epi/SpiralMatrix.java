// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Random;

public class SpiralMatrix {
  // @include
  public static void printMatrixInSpiralOrder(int[][] A) {
    int[][] shift = new int[4][2];
    shift[0] = new int[]{0, 1};
    shift[1] = new int[]{1, 0};
    shift[2] = new int[]{0, -1};
    shift[3] = new int[]{-1, 0};
    int dir = 0, x = 0, y = 0;

    for (int i = 0; i < A.length * A.length; ++i) {
      System.out.print(A[x][y] + " ");
      A[x][y] = 0;
      int nx = x + shift[dir][0], ny = y + shift[dir][1];
      if (nx < 0 || nx >= A.length || ny < 0 || ny >= A.length
          || A[nx][ny] == 0) {
        dir = (dir + 1) & 3;
        nx = x + shift[dir][0];
        ny = y + shift[dir][1];
      }
      x = nx;
      y = ny;
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
