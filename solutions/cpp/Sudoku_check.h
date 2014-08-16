// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_SUDOKU_CHECK_H_
#define SOLUTIONS_SUDOKU_CHECK_H_

#include <deque>
#include <vector>

using std::deque;
using std::vector;

bool HasDuplicate(const vector<vector<int>>& A, int start_row, int end_row,
                   int start_col, int end_col, int num_elements);

// @include
// Check if a partially filled matrix has any conflicts.
bool IsValidSudoku(const vector<vector<int>>& A) {
  // Check row constraints.
  for (int i = 0; i < A.size(); ++i) {
    if (HasDuplicate(A, i, i + 1, 0, A.size(), A.size())) {
      return false;
    }
  }

  // Check column constraints.
  for (int j = 0; j < A.size(); ++j) {
    if (HasDuplicate(A, 0, A.size(), j, j + 1, A.size())) {
      return false;
    }
  }

  // Check region constraints.
  int region_size = sqrt(A.size());
  for (int I = 0; I < region_size; ++I) {
    for (int J = 0; J < region_size; ++J) {
      if (HasDuplicate(A, region_size * I, region_size * (I + 1),
                       region_size * J, region_size * (J + 1), A.size())) {
        return false;
      }
    }
  }
  return true;
}

// Return true if subarray A[start_row : end_row - 1][start_col : end_col - 1]
// contains any duplicates in [1 : num_elements]; otherwise return false.
bool HasDuplicate(const vector<vector<int>>& A, int start_row, int end_row,
                  int start_col, int end_col, int num_elements) {
  deque<bool> is_present(num_elements + 1, false);
  for (int i = start_row; i < end_row; ++i) {
    for (int j = start_col; j < end_col; ++j) {
      if (A[i][j] != 0 && is_present[A[i][j]]) {
        return true;
      }
      is_present[A[i][j]] = true;
    }
  }
  return false;
}
// @exclude
#endif  // SOLUTIONS_SUDOKU_CHECK_H_
