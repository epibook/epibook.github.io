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
    final int matrixSize = squareMatrix.size() - 1;
    for (int i = 0; i < (squareMatrix.size() / 2); ++i) {
      for (int j = i; j < matrixSize - i; ++j) {
        // Perform a 4-way exchange.
        int temp1 = squareMatrix.get(matrixSize - j).get(i);
        int temp2 = squareMatrix.get(matrixSize - i).get(matrixSize - j);
        int temp3 = squareMatrix.get(j).get(matrixSize - i);
        int temp4 = squareMatrix.get(i).get(j);
        squareMatrix.get(i).set(j, temp1);
        squareMatrix.get(matrixSize - j).set(i, temp2);
        squareMatrix.get(matrixSize - i).set(matrixSize - j, temp3);
        squareMatrix.get(j).set(matrixSize - i, temp4);
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
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
