package com.epi;

public class SudokuCheck {
  // @include
  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(int[][] partialAssignment) {
    // Check row constraints.
    for (int i = 0; i < partialAssignment.length; ++i) {
      if (hasDuplicate(partialAssignment, i, i + 1, 0, partialAssignment.length,
                       partialAssignment.length)) {
        return false;
      }
    }

    // Check column constraints.
    for (int j = 0; j < partialAssignment.length; ++j) {
      if (hasDuplicate(partialAssignment, 0, partialAssignment.length, j, j + 1,
                       partialAssignment.length)) {
        return false;
      }
    }

    // Check region constraints.
    int regionSize = (int)Math.sqrt(partialAssignment.length);
    for (int I = 0; I < regionSize; ++I) {
      for (int J = 0; J < regionSize; ++J) {
        if (hasDuplicate(partialAssignment, regionSize * I, regionSize * (I + 1),
                         regionSize * J, regionSize * (J + 1),
                         partialAssignment.length)) {
          return false;
        }
      }
    }
    return true;
  }

  // Return true if subarray partialAssignment[startRow : endRow - 1][startCol :
  // endCol - 1]
  // contains any duplicates in {1, 2, ..., numElements}; otherwise return false.
  private static boolean hasDuplicate(int[][] partialAssignment, int startRow,
                                      int endRow, int startCol, int endCol,
                                      int numElements) {
    boolean[] isPresent = new boolean[numElements + 1];
    for (int i = startRow; i < endRow; ++i) {
      for (int j = startCol; j < endCol; ++j) {
        if (partialAssignment[i][j] != 0 && isPresent[partialAssignment[i][j]]) {
          return true;
        }
        isPresent[partialAssignment[i][j]] = true;
      }
    }
    return false;
  }
  // @exclude
}
