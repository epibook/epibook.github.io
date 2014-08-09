package com.epi;

import java.util.Arrays;

import static com.epi.SudokuCheck.isValidSudoku;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SudokuSolve {
  // @include
  public static boolean solveSudoku(int[][] A) {
    if (!isValidSudoku(A)) {
      System.out.println("Initial configuration violates constraints.");
      return false;
    }

    if (solveSudokuHelper(A, 0, 0)) {
      for (int[] element : A) {
        System.out.println(Arrays.toString(element));
      }
      return true;
    } else {
      System.out.println("No solution exists.");
      return false;
    }
  }

  private static boolean solveSudokuHelper(int[][] A, int i, int j) {
    if (i == A.length) {
      i = 0; // Starts a new row.
      if (++j == A[i].length) {
        return true; // Entire matrix has been filled without conflict.
      }
    }

    // Skips nonempty entries.
    if (A[i][j] != 0) {
      return solveSudokuHelper(A, i + 1, j);
    }

    for (int val = 1; val <= A.length; ++val) {
      // Note: practically, it's substantially quicker to check if entry val
      // conflicts with any of the constraints if we add it at (i,j) before
      // adding it, rather than adding it and then calling isValidSudoku.
      // The reason is that we are starting with a valid configuration,
      // and the only entry which can cause a problem is entryval at (i,j).
      if (validToAdd(A, i, j, val)) {
        A[i][j] = val;
        if (solveSudokuHelper(A, i + 1, j)) {
          return true;
        }
      }
    }

    A[i][j] = 0; // Undo assignment.
    return false;
  }

  private static boolean validToAdd(int[][] A, int i, int j, int val) {
    // Check row constraints.
    for (int[] element : A) {
      if (val == element[j]) {
        return false;
      }
    }

    // Check column constraints.
    for (int k = 0; k < A.length; ++k) {
      if (val == A[i][k]) {
        return false;
      }
    }

    // Check region constraints.
    int regionSize = (int) Math.sqrt(A.length);
    int I = i / regionSize, J = j / regionSize;
    for (int a = 0; a < regionSize; ++a) {
      for (int b = 0; b < regionSize; ++b) {
        if (val == A[regionSize * I + a][regionSize * J + b]) {
          return false;
        }
      }
    }
    return true;
  }
  // @exclude

  public static void main(String[] args) {
    int[][] A = new int[9][];
    A[0] = new int[]{0, 2, 6, 0, 0, 0, 8, 1, 0};
    A[1] = new int[]{3, 0, 0, 7, 0, 8, 0, 0, 6};
    A[2] = new int[]{4, 0, 0, 0, 5, 0, 0, 0, 7};
    A[3] = new int[]{0, 5, 0, 1, 0, 7, 0, 9, 0};
    A[4] = new int[]{0, 0, 3, 9, 0, 5, 1, 0, 0};
    A[5] = new int[]{0, 4, 0, 3, 0, 2, 0, 5, 0};
    A[6] = new int[]{1, 0, 0, 0, 3, 0, 0, 0, 2};
    A[7] = new int[]{5, 0, 0, 2, 0, 4, 0, 0, 9};
    A[8] = new int[]{0, 3, 8, 0, 0, 0, 4, 6, 0};
    solveSudoku(A);
  }
}
