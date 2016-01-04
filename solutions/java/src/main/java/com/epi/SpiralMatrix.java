// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpiralMatrix {
  // @include
  public static List<Integer> matrixInSpiralOrder(
      List<List<Integer>> squareMatrix) {
    final int[][] SHIFT = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int dir = 0, x = 0, y = 0;
    List<Integer> spiralOrdering = new ArrayList<>();

    for (int i = 0; i < squareMatrix.size() * squareMatrix.size(); ++i) {
      spiralOrdering.add(squareMatrix.get(x).get(y));
      squareMatrix.get(x).set(y, 0);
      int nextX = x + SHIFT[dir][0], nextY = y + SHIFT[dir][1];
      if (nextX < 0 || nextX >= squareMatrix.size() || nextY < 0
          || nextY >= squareMatrix.size()
          || squareMatrix.get(nextX).get(nextY) == 0) {
        dir = (dir + 1) % 4;
        nextX = x + SHIFT[dir][0];
        nextY = y + SHIFT[dir][1];
      }
      x = nextX;
      y = nextY;
    }
    return spiralOrdering;
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
