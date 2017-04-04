// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixRotationConstant {
  private static void rotateMatrix(List<List<Integer>> squareMatrix) {
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

  private static void checkAnswer(List<List<Integer>> A,
                                  List<List<Integer>> B) {
    RotatedMatrix rA = new RotatedMatrix(A);
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.size(); ++j) {
        assert(rA.readEntry(i, j) == B.get(i).get(j));
      }
    }
  }

  public static void main(String[] args) {
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      List<List<Integer>> A = new ArrayList<>(1 << n);
      int k = 1;
      for (int i = 0; i < (1 << n); ++i) {
        A.add(new ArrayList(1 << n));
        for (int j = 0; j < (1 << n); ++j) {
          A.get(i).add(k++);
        }
      }
      List<List<Integer>> B = new ArrayList<>(1 << n);
      for (int i = 0; i < (1 << n); ++i) {
        B.add(new ArrayList<>(A.get(i)));
      }
      rotateMatrix(B);
      checkAnswer(A, B);
    } else {
      Random gen = new Random();
      for (int times = 0; times < 100; ++times) {
        n = gen.nextInt(2) + 1;
        List<List<Integer>> A = new ArrayList<>(1 << n);
        int k = 1;
        for (int i = 0; i < (1 << n); ++i) {
          A.add(new ArrayList(1 << n));
          for (int j = 0; j < (1 << n); ++j) {
            A.get(i).add(k++);
          }
        }
        List<List<Integer>> B = new ArrayList<>(1 << n);
        for (int i = 0; i < (1 << n); ++i) {
          B.add(new ArrayList<>(A.get(i)));
        }
        rotateMatrix(B);
        checkAnswer(A, B);
      }
    }
  }
}

// @include
class RotatedMatrix {
  private List<List<Integer>> wrappedSquareMatrix;

  public RotatedMatrix(List<List<Integer>> squareMatrix) {
    this.wrappedSquareMatrix = squareMatrix;
  }

  public int readEntry(int i, int j) {
    return wrappedSquareMatrix.get(wrappedSquareMatrix.size() - 1 - j).get(i);
  }

  public void writeEntry(int i, int j, int v) {
    wrappedSquareMatrix.get(wrappedSquareMatrix.size() - 1 - j).set(i, v);
  }
}
// @exclude
