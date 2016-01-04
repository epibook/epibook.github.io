// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

#include "./Max_submatrix_rectangle_brute_force.h"

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::max;
using std::min;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
struct MaxHW {
  int h, w;
};

int MaxRectangleSubmatrix(const vector<deque<bool>>& A) {
  // DP table stores (h, w) for each (i, j).
  vector<vector<MaxHW>> table(A.size(), vector<MaxHW>(A.front().size()));

  for (int i = A.size() - 1; i >= 0; --i) {
    for (int j = A[i].size() - 1; j >= 0; --j) {
      // Find the largest h such that (i, j) to (i + h - 1, j) are feasible.
      // Find the largest w such that (i, j) to (i, j + w - 1) are feasible.
      table[i][j] =
          A[i][j] ? MaxHW{i + 1 < A.size() ? table[i + 1][j].h + 1 : 1,
                          j + 1 < A[i].size() ? table[i][j + 1].w + 1 : 1}
                  : MaxHW{0, 0};
    }
  }

  int max_rect_area = 0;
  for (int i = 0; i < A.size(); ++i) {
    for (int j = 0; j < A[i].size(); ++j) {
      // Process (i, j) if it is feasible and is possible to update
      // max_rect_area.
      if (A[i][j] && table[i][j].w * table[i][j].h > max_rect_area) {
        int min_width = numeric_limits<int>::max();
        for (int a = 0; a < table[i][j].h; ++a) {
          min_width = min(min_width, table[i + a][j].w);
          max_rect_area = max(max_rect_area, min_width * (a + 1));
        }
      }
    }
  }
  return max_rect_area;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n, m;
    if (argc == 3) {
      n = atoi(argv[1]), m = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> dis(1, 50);
      n = dis(gen), m = dis(gen);
    }
    vector<deque<bool>> A(n, deque<bool>(m));
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        uniform_int_distribution<int> true_or_false(0, 1);
        A[i][j] = true_or_false(gen) ? true : false;
      }
    }
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        cout << A[i][j] << ' ';
      }
      cout << endl;
    }
    cout << MaxRectangleSubmatrix(A) << endl;
    int test_area = MaxRectangleSubmatrixBruteForce(A);
    cout << test_area << endl;
    assert(test_area == MaxRectangleSubmatrix(A));
  }
  return 0;
}
