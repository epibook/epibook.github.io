// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static com.epi.utils.Utils.simplePrint;

public class PaintingIterative {
  static void printMatrix(boolean[][] A) {
    for (boolean[] element : A) {
      simplePrint(element);
      System.out.println();
    }
  }

  // @include
  private static class Coordinate {
    public Integer x;
    public Integer y;

    public Coordinate(Integer x, Integer y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void flipColor(boolean[][] A, int x, int y) {
    int[][] dir = new int[][] {new int[] {0, 1}, new int[] {0, -1},
                               new int[] {1, 0}, new int[] {-1, 0}};
    boolean color = A[x][y];

    Queue<Coordinate> q = new LinkedList<>();
    A[x][y] = !A[x][y]; // Flips.
    q.add(new Coordinate(x, y));
    while (!q.isEmpty()) {
      Coordinate curr = q.element();
      for (int[] d : dir) {
        Coordinate next = new Coordinate(curr.x + d[0], curr.y + d[1]);
        if (next.x >= 0 && next.x < A.length && next.y >= 0 &&
            next.y < A[next.x].length && A[next.x][next.y] == color) {
          // Flips the color.
          A[next.x][next.y] = !A[next.x][next.y];
          q.add(next);
        }
      }
      q.remove();
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
