// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <deque>
#include <iostream>
#include <iterator>
#include <vector>

#include "./Sudoku_check.h"

using std::cout;
using std::deque;
using std::endl;
using std::ostream_iterator;
using std::vector;

bool SolveSudokuHelper(int i, int j, vector<vector<int>>* A);
bool ValidToAdd(const vector<vector<int>>& A, int i, int j, int val);

// @include
bool SolveSudoku(vector<vector<int>>* A) {
  if (!IsValidSudoku(*A)) {
    cout << "Initial configuration violates constraints." << endl;
    return false;
  }

  if (SolveSudokuHelper(0, 0, A)) {
    for (int i = 0; i < A->size(); ++i) {
      copy((*A)[i].begin(), (*A)[i].end(), ostream_iterator<int>(cout, " "));
      cout << endl;
    }
    return true;
  } else {
    cout << "No solution exists." << endl;
    return false;
  }
}

bool SolveSudokuHelper(int i, int j, vector<vector<int>>* A) {
  if (i == A->size()) {
    i = 0;  // Starts a new row.
    if (++j == (*A)[i].size()) {
      return true;  // Entire matrix has been filled without conflict.
    }
  }

  // Skips nonempty entries.
  if ((*A)[i][j] != 0) {
    return SolveSudokuHelper(i + 1, j, A);
  }

  for (int val = 1; val <= A->size(); ++val) {
    // Note: practically, it's substantially quicker to check if entry val
    // conflicts with any of the constraints if we add it at (i,j) before
    // adding it, rather than adding it and then checking all constraints.
    // The reason is that we know we are starting with a valid configuration,
    // and the only entry which can cause a problem is entryval at (i,j).
    if (ValidToAdd(*A, i, j, val)) {
      (*A)[i][j] = val;
      if (SolveSudokuHelper(i + 1, j, A)) {
        return true;
      }
    }
  }

  (*A)[i][j] = 0;  // Undo assignment.
  return false;
}

bool ValidToAdd(const vector<vector<int>>& A, int i, int j, int val) {
  // Check row constraints.
  for (int k = 0; k < A.size(); ++k) {
    if (val == A[k][j]) {
      return false;
    }
  }

  // Check column constraints.
  for (int k = 0; k < A.size(); ++k) {
    if (val == A[i][k]) {
      return false;
    }
  }

  // Check region constraints.
  int region_size = sqrt(A.size());
  int I = i / region_size, J = j / region_size;
  for (int a = 0; a < region_size; ++a) {
    for (int b = 0; b < region_size; ++b) {
      if (val == A[region_size * I + a][region_size * J + b]) {
        return false;
      }
    }
  }
  return true;
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
  SolveSudoku(&A);
  vector<vector<int>> golden_A = {{7, 2, 6, 4, 9, 3, 8, 1, 5},
                                  {3, 1, 5, 7, 2, 8, 9, 4, 6},
                                  {4, 8, 9, 6, 5, 1, 2, 3, 7},
                                  {8, 5, 2, 1, 4, 7, 6, 9, 3},
                                  {6, 7, 3, 9, 8, 5, 1, 2, 4},
                                  {9, 4, 1, 3, 6, 2, 7, 5, 8},
                                  {1, 9, 4, 8, 3, 6, 5, 7, 2},
                                  {5, 6, 7, 2, 1, 4, 3, 8, 9},
                                  {2, 3, 8, 5, 7, 9, 4, 6, 1}};
  for (size_t i = 0; i < 9; ++i) {
    for (size_t j = 0; j < 9; ++j) {
      assert(A[i][j] == golden_A[i][j]);
    }
  }
  return 0;
}
