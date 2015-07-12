// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_SUDOKU_CHECK_H_
#define SOLUTIONS_SUDOKU_CHECK_H_

#include <deque>
#include <vector>

using std::deque;
using std::vector;

bool HasDuplicate(const vector<vector<int>>&, int, int, int, int, int);

// @include
// Check if a partially filled matrix has any conflicts.
bool IsValidSudoku(const vector<vector<int>>& partial_assignment) {
  // Check row constraints.
  for (int i = 0; i < partial_assignment.size(); ++i) {
    if (HasDuplicate(partial_assignment, i, i + 1, 0, partial_assignment.size(),
                     partial_assignment.size())) {
      return false;
    }
  }

  // Check column constraints.
  for (int j = 0; j < partial_assignment.size(); ++j) {
    if (HasDuplicate(partial_assignment, 0, partial_assignment.size(), j, j + 1,
                     partial_assignment.size())) {
      return false;
    }
  }

  // Check region constraints.
  int region_size = sqrt(partial_assignment.size());
  for (int I = 0; I < region_size; ++I) {
    for (int J = 0; J < region_size; ++J) {
      if (HasDuplicate(partial_assignment, region_size * I,
                       region_size * (I + 1), region_size * J,
                       region_size * (J + 1), partial_assignment.size())) {
        return false;
      }
    }
  }
  return true;
}

// Return true if subarray partial_assignment[start_row : end_row - 1][start_col
// : end_col - 1]
// contains any duplicates in {1, 2, ..., num_elements}; otherwise return false.
bool HasDuplicate(const vector<vector<int>>& partial_assignment, int start_row,
                  int end_row, int start_col, int end_col, int num_elements) {
  deque<bool> is_present(num_elements + 1, false);
  for (int i = start_row; i < end_row; ++i) {
    for (int j = start_col; j < end_col; ++j) {
      if (partial_assignment[i][j] != 0 &&
          is_present[partial_assignment[i][j]]) {
        return true;
      }
      is_present[partial_assignment[i][j]] = true;
    }
  }
  return false;
}
// @exclude
#endif  // SOLUTIONS_SUDOKU_CHECK_H_
