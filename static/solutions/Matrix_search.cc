// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
bool MatrixSearch(const vector<vector<int>>& A, int x) {
  int row = 0, col = A[0].size() - 1;  // Start from the top-right corner.
  // Keeps searching while there are unclassified rows and columns.
  while (row < A.size() && col >= 0) {
    if (A[row][col] == x) {
      return true;
    } else if (A[row][col] < x) {
      ++row;  // Eliminate this row.
    } else {  // A[row][col] > x.
      --col;  // Eliminate this column.
    }
  }
  return false;
}
// @exclude

static void SimpleTest() {
  vector<vector<int>> A = {{1}};
  assert(!MatrixSearch(A, 0));
  assert(MatrixSearch(A, 1));

  A = {{1, 5}, {2, 6}};
  assert(!MatrixSearch(A, 0));
  assert(MatrixSearch(A, 1));
  assert(MatrixSearch(A, 2));
  assert(MatrixSearch(A, 5));
  assert(MatrixSearch(A, 6));
  assert(!MatrixSearch(A, 3));
  assert(!MatrixSearch(A, numeric_limits<int>::max()));

  A = {{2, 5}, {2, 6}};
  assert(!MatrixSearch(A, 1));
  assert(MatrixSearch(A, 2));

  A = {{1, 5, 7}, {3, 10, 100}, {3, 12, numeric_limits<int>::max()}};
  assert(MatrixSearch(A, 1));
  assert(!MatrixSearch(A, 2));
  assert(!MatrixSearch(A, 4));
  assert(MatrixSearch(A, 3));
  assert(MatrixSearch(A, 10));
  assert(MatrixSearch(A, numeric_limits<int>::max()));
  assert(!MatrixSearch(A, numeric_limits<int>::max() - 1));
  assert(MatrixSearch(A, 12));
}

// O(n^2) solution for verifying answer.
bool BruteForceSearch(const vector<vector<int>>& A, int x) {
  for (int i = 0; i < A.size(); ++i) {
    for (int j = 0; j < A[i].size(); ++j) {
      if (A[i][j] == x) {
        return true;
      }
    }
  }
  return false;
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    int n, m;
    if (argc == 3) {
      n = atoi(argv[1]), m = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> dis(1, 100);
      n = dis(gen), m = dis(gen);
    }
    vector<vector<int>> A(n, vector<int>(m));
    uniform_int_distribution<int> dis(0, 99);
    A[0][0] = dis(gen);
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        int up = (i == 0) ? 0 : A[i - 1][j];
        int left = (j == 0) ? 0 : A[i][j - 1];
        uniform_int_distribution<int> shift_dis(1, 20);
        A[i][j] = max(up, left) + shift_dis(gen);
      }
    }
    uniform_int_distribution<int> x_dis(0, 999);
    int x = x_dis(gen);
    assert(BruteForceSearch(A, x) == MatrixSearch(A, x));
  }
  return 0;
}
