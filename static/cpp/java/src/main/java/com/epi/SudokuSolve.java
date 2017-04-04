package com.epi;

/*

@slug
sudoku-solver

@summary
Sudoku is a popular logic-based combinatorial number placement puzzle.
The objective is to fill a $9\times 9$ grid with digits subject to the
constraint that each column,
each row, and each of the nine $3 \times 3$ sub-grids that compose the grid
contains unique integers in $[1,9]$. The grid is initialized with a partial
assignment as shown in Figure~\vref{sudoku-checker-fig-a};
a complete solution is shown in Figure~\vref{sudoku-checker-fig-b}.


@problem
Implement a Sudoku solver.
*/

import java.util.Arrays;
import java.util.List;

public class SudokuSolve {
  // @include
  private static final int EMPTY_ENTRY = 0;

  public static boolean solveSudoku(List<List<Integer>> partialAssignment) {
    return solvePartialSudoku(0, 0, partialAssignment);
  }

  private static boolean solvePartialSudoku(
      int i, int j, List<List<Integer>> partialAssignment) {
    if (i == partialAssignment.size()) {
      i = 0; // Starts a new row.
      if (++j == partialAssignment.get(i).size()) {
        return true; // Entire matrix has been filled without conflict.
      }
    }

    // Skips nonempty entries.
    if (partialAssignment.get(i).get(j) != EMPTY_ENTRY) {
      return solvePartialSudoku(i + 1, j, partialAssignment);
    }

    for (int val = 1; val <= partialAssignment.size(); ++val) {
      // It's substantially quicker to check if entry val conflicts
      // with any of the constraints if we add it at (i,j) before
      // adding it, rather than adding it and then checking all constraints.
      // The reason is that we are starting with a valid configuration,
      // and the only entry which can cause a problem is entryval at (i,j).
      if (validToAddVal(partialAssignment, i, j, val)) {
        partialAssignment.get(i).set(j, val);
        if (solvePartialSudoku(i + 1, j, partialAssignment)) {
          return true;
        }
      }
    }

    partialAssignment.get(i).set(j, EMPTY_ENTRY); // Undo assignment.
    return false;
  }

  private static boolean validToAddVal(List<List<Integer>> partialAssignment,
                                       int i, int j, int val) {
    // Check row constraints.
    for (List<Integer> element : partialAssignment) {
      if (val == element.get(j)) {
        return false;
      }
    }

    // Check column constraints.
    for (int k = 0; k < partialAssignment.size(); ++k) {
      if (val == partialAssignment.get(i).get(k)) {
        return false;
      }
    }

    // Check region constraints.
    int regionSize = (int)Math.sqrt(partialAssignment.size());
    int I = i / regionSize, J = j / regionSize;
    for (int a = 0; a < regionSize; ++a) {
      for (int b = 0; b < regionSize; ++b) {
        // clang-format off
        if (val == partialAssignment
                       .get(regionSize * I + a).get(regionSize * J + b)) {
          // clang-format on
          return false;
        }
      }
    }
    return true;
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
    assert(solveSudoku(A));
    List<List<Integer>> goldenA
        = Arrays.asList(Arrays.asList(7, 2, 6, 4, 9, 3, 8, 1, 5),
                        Arrays.asList(3, 1, 5, 7, 2, 8, 9, 4, 6),
                        Arrays.asList(4, 8, 9, 6, 5, 1, 2, 3, 7),
                        Arrays.asList(8, 5, 2, 1, 4, 7, 6, 9, 3),
                        Arrays.asList(6, 7, 3, 9, 8, 5, 1, 2, 4),
                        Arrays.asList(9, 4, 1, 3, 6, 2, 7, 5, 8),
                        Arrays.asList(1, 9, 4, 8, 3, 6, 5, 7, 2),
                        Arrays.asList(5, 6, 7, 2, 1, 4, 3, 8, 9),
                        Arrays.asList(2, 3, 8, 5, 7, 9, 4, 6, 1));
    assert(A.equals(goldenA));
  }
}
