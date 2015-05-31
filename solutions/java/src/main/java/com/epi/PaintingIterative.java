// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import com.epi.utils.Pair;

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
  public static void flipColor(boolean[][] A, int x, int y) {
    int[][] dir = new int[][] {new int[] {0, 1}, new int[] {0, -1},
                               new int[] {1, 0}, new int[] {-1, 0}};
    boolean color = A[x][y];

    Queue<Pair<Integer, Integer>> q = new LinkedList<>();
    A[x][y] = !A[x][y]; // Flips.
    q.add(new Pair<>(x, y));
    while (!q.isEmpty()) {
      Pair<Integer, Integer> curr = q.element();
      for (int[] d : dir) {
        Pair<Integer, Integer> next =
            new Pair<>(curr.getFirst() + d[0], curr.getSecond() + d[1]);
        if (next.getFirst() >= 0 && next.getFirst() < A.length &&
            next.getSecond() >= 0 &&
            next.getSecond() < A[next.getFirst()].length &&
            A[next.getFirst()][next.getSecond()] == color) {
          // Flips the color.
          A[next.getFirst()][next.getSecond()] =
              !A[next.getFirst()][next.getSecond()];
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
