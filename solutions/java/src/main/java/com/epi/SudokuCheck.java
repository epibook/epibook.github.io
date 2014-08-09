package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SudokuCheck {
  // @include
  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(int[][] A) {
    // Check row constraints.
    for (int[] aA1 : A) {
      boolean[] isPresent = new boolean[A.length + 1];
      for (int j = 0; j < A.length; ++j) {
        if (aA1[j] != 0 && isPresent[aA1[j]]) {
          return false;
        } else {
          isPresent[aA1[j]] = true;
        }
      }
    }

    // Check column constraints.
    for (int j = 0; j < A.length; ++j) {
      boolean[] isPresent = new boolean[A.length + 1];
      for (int[] aA : A) {
        if (aA[j] != 0 && isPresent[aA[j]]) {
          return false;
        } else {
          isPresent[aA[j]] = true;
        }
      }
    }

    // Check region constraints.
    int regionSize = (int) Math.sqrt(A.length);
    for (int I = 0; I < regionSize; ++I) {
      for (int J = 0; J < regionSize; ++J) {
        boolean[] isPresent = new boolean[A.length + 1];
        for (int i = 0; i < regionSize; ++i) {
          for (int j = 0; j < regionSize; ++j) {
            if (A[regionSize * I + i][regionSize * J + j] != 0
                && isPresent[A[regionSize * I + i][regionSize * J + j]]) {
              return false;
            } else {
              isPresent[A[regionSize * I + i][regionSize * J + j]] = true;
            }
          }
        }
      }
    }
    return true;
  }
  // @exclude
}
