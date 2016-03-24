// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <deque>
#include <vector>

using std::deque;
using std::vector;

bool HasDuplicate(const vector<vector<int>>&, int, int, int, int);

// @include
// Check if a partially filled matrix has any conflicts.
bool IsValidSudoku(const vector<vector<int>>& partial_assignment) {
  // Check row constraints.
  for (int i = 0; i < partial_assignment.size(); ++i) {
    if (HasDuplicate(partial_assignment, i, i + 1, 0,
                     partial_assignment.size())) {
      return false;
    }
  }

  // Check column constraints.
  for (int j = 0; j < partial_assignment.size(); ++j) {
    if (HasDuplicate(partial_assignment, 0, partial_assignment.size(), j,
                     j + 1)) {
      return false;
    }
  }

  // Check region constraints.
  int region_size = sqrt(partial_assignment.size());
  for (int I = 0; I < region_size; ++I) {
    for (int J = 0; J < region_size; ++J) {
      if (HasDuplicate(partial_assignment, region_size * I,
                       region_size * (I + 1), region_size * J,
                       region_size * (J + 1))) {
        return false;
      }
    }
  }
  return true;
}

// Return true if subarray partial_assignment[start_row : end_row -
// 1][start_col : end_col - 1] contains any duplicates in {1, 2, ...,
// partial_assignment.size()}; otherwise return false.
bool HasDuplicate(const vector<vector<int>>& partial_assignment,
                  int start_row, int end_row, int start_col, int end_col) {
  deque<bool> is_present(partial_assignment.size() + 1, false);
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

int main(int argc, char* argv[]) {
  vector<vector<int>> A(9, vector<int>(9, 0));
  A[0] = {0, 2, 6, 0, 0, 0, 8, 1, 0};
  A[1] = {3, 0, 0, 7, 0, 8, 0, 0, 6};
  A[2] = {4, 0, 0, 0, 5, 0, 0, 0, 7};
  A[3] = {0, 5, 0, 1, 0, 7, 0, 9, 0};
  A[4] = {0, 0, 3, 9, 0, 5, 1, 0, 0};
  A[5] = {0, 4, 0, 3, 0, 2, 0, 5, 0};
  A[6] = {1, 0, 0, 0, 3, 0, 0, 0, 2};
  A[7] = {5, 0, 0, 2, 0, 4, 0, 0, 9};
  A[8] = {0, 3, 8, 0, 0, 0, 4, 6, 0};
  assert(IsValidSudoku(A));
  // There are two 3s.
  A[8] = {3, 3, 8, 0, 0, 0, 4, 6, 0};
  assert(!IsValidSudoku(A));
  return 0;
}
