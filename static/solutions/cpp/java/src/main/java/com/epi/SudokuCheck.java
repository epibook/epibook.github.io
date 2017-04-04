package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuCheck {
  // @include
  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    // Check row constraints.
    for (int i = 0; i < partialAssignment.size(); ++i) {
      if (hasDuplicate(partialAssignment, i, i + 1, 0,
                       partialAssignment.size())) {
        return false;
      }
    }

    // Check column constraints.
    for (int j = 0; j < partialAssignment.size(); ++j) {
      if (hasDuplicate(partialAssignment, 0, partialAssignment.size(), j,
                       j + 1)) {
        return false;
      }
    }

    // Check region constraints.
    int regionSize = (int)Math.sqrt(partialAssignment.size());
    for (int I = 0; I < regionSize; ++I) {
      for (int J = 0; J < regionSize; ++J) {
        if (hasDuplicate(partialAssignment, regionSize * I,
                         regionSize * (I + 1), regionSize * J,
                         regionSize * (J + 1))) {
          return false;
        }
      }
    }
    return true;
  }

  // Return true if subarray partialAssignment[startRow : endRow - 1][startCol :
  // endCol - 1] contains any duplicates in {1, 2, ...,
  // partialAssignment.size()}; otherwise return false.
  private static boolean hasDuplicate(List<List<Integer>> partialAssignment,
                                      int startRow, int endRow, int startCol,
                                      int endCol) {
    List<Boolean> isPresent = new ArrayList<>(
        Collections.nCopies(partialAssignment.size() + 1, false));
    for (int i = startRow; i < endRow; ++i) {
      for (int j = startCol; j < endCol; ++j) {
        if (partialAssignment.get(i).get(j) != 0
            && isPresent.get(partialAssignment.get(i).get(j))) {
          return true;
        }
        isPresent.set(partialAssignment.get(i).get(j), true);
      }
    }
    return false;
  }
  // @exclude

  public static void main(String[] args) {
    List<List<Integer>> A
        = Arrays.asList(Arrays.asList(0, 2, 6, 0, 0, 0, 8, 1, 0),
                        Arrays.asList(3, 0, 0, 7, 0, 8, 0, 0, 6),
                        Arrays.asList(4, 0, 0, 0, 5, 0, 0, 0, 7),
                        Arrays.asList(0, 5, 0, 1, 0, 7, 0, 9, 0),
                        Arrays.asList(0, 0, 3, 9, 0, 5, 1, 0, 0),
                        Arrays.asList(0, 4, 0, 3, 0, 2, 0, 5, 0),
                        Arrays.asList(1, 0, 0, 0, 3, 0, 0, 0, 2),
                        Arrays.asList(5, 0, 0, 2, 0, 4, 0, 0, 9),
                        Arrays.asList(0, 3, 8, 0, 0, 0, 4, 6, 0));
    assert(isValidSudoku(A));
    A.set(8, Arrays.asList(3, 3, 8, 0, 0, 0, 4, 6, 0));
    assert(isValidSudoku(A) == false);
  }
}
