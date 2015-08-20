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
    final int MATRIX_SIZE = squareMatrix.size() - 1;
    for (int i = 0; i < (squareMatrix.size() / 2); ++i) {
      for (int j = i; j < MATRIX_SIZE - i; ++j) {
        // Perform a 4-way exchange.
        int temp = squareMatrix.get(i).get(j);
        squareMatrix.get(i).set(j, squareMatrix.get(MATRIX_SIZE - j).get(i));
        squareMatrix.get(MATRIX_SIZE - j)
            .set(i, squareMatrix.get(MATRIX_SIZE - i).get(MATRIX_SIZE - j));
        squareMatrix.get(MATRIX_SIZE - i)
            .set(MATRIX_SIZE - j, squareMatrix.get(j).get(MATRIX_SIZE - i));
        squareMatrix.get(j).set(MATRIX_SIZE - i, temp);
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
