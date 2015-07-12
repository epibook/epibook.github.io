// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixRotationNaive {
  private static void checkAnswer(List<List<Integer>> A) {
    int k = 1;
    for (int j = A.size() - 1; j >= 0; --j) {
      for (List<Integer> element : A) {
        assert(k++ == element.get(j));
      }
    }
  }

  // @include
  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    for (int i = 0; i < (squareMatrix.size() / 2); ++i) {
      for (int j = i; j < squareMatrix.size() - i - 1; ++j) {
        // Perform a 4-way exchange.
        int temp = squareMatrix.get(i).get(j);
        squareMatrix.get(i)
            .set(j, squareMatrix.get(squareMatrix.size() - 1 - j).get(i));
        squareMatrix.get(squareMatrix.size() - 1 - j)
            .set(i, squareMatrix.get(squareMatrix.size() - 1 - i)
                        .get(squareMatrix.size() - 1 - j));
        squareMatrix.get(squareMatrix.size() - 1 - i)
            .set(squareMatrix.size() - 1 - j,
                 squareMatrix.get(j).get(squareMatrix.size() - 1 - i));
        squareMatrix.get(j).set(squareMatrix.size() - 1 - i, temp);
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    int n;
    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
      List<List<Integer>> A = new ArrayList<>();
      int k = 1;
      for (int i = 0; i < (1 << n); ++i) {
        A.add(new ArrayList());
        for (int j = 0; j < (1 << n); ++j) {
          A.get(i).add(k++);
        }
      }
      System.out.println(A);
      rotateMatrix(A);
      checkAnswer(A);
      System.out.println(A);
    } else {
      Random gen = new Random();
      for (int times = 0; times < 100; ++times) {
        n = gen.nextInt(10) + 1;
        List<List<Integer>> A = new ArrayList<>();
        int k = 1;
        for (int i = 0; i < (1 << n); ++i) {
          A.add(new ArrayList());
          for (int j = 0; j < (1 << n); ++j) {
            A.get(i).add(k++);
          }
        }
        rotateMatrix(A);
        checkAnswer(A);
      }
    }
  }
}
