// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Random;

public class PaintingRecursive {
  private static void printMatrix(boolean[][] A) {
    for (boolean[] element : A) {
      for (int j = 0; j < A.length; ++j) {
        System.out.print(element[j] + " ");
      }
      System.out.println();
    }
  }

  // @include
  public static void flipColor(boolean[][] A, int x, int y) {
    int[][] dir = new int[][] {new int[] {0, 1}, new int[] {0, -1},
                               new int[] {1, 0}, new int[] {-1, 0}};

    boolean color = A[x][y];

    A[x][y] = !A[x][y]; // Flips.
    for (int[] d : dir) {
      int nx = x + d[0], ny = y + d[1];
      if (nx >= 0 && nx < A.length && ny >= 0 && ny < A[nx].length &&
          A[nx][ny] == color) {
        flipColor(A, nx, ny);
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    int n;
    Random gen = new Random();
    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
    } else {
      n = gen.nextInt(100) + 1;
    }
    boolean[][] A = new boolean[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        A[i][j] = gen.nextBoolean();
      }
    }
    int i = gen.nextInt(n), j = gen.nextInt(n);
    System.out.println("color = " + i + " " + j + " " + A[i][j]);
    printMatrix(A);
    flipColor(A, i, j);
    System.out.println();
    printMatrix(A);
  }
}
