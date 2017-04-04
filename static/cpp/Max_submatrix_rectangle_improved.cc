// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

#include "./Largest_rectangle_under_skyline.h"
#include "./Max_submatrix_rectangle_brute_force.h"

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::max;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int MaxRectangleSubmatrix(const vector<deque<bool>>& A) {
  vector<int> table(A.front().size(), 0);
  int max_rect_area = 0;
  // Find the maximum among all instances of the largest rectangle.
  for (int i = 0; i < A.size(); ++i) {
    for (int j = 0; j < A[i].size(); ++j) {
      table[j] = A[i][j] ? table[j] + 1 : 0;
    }
    max_rect_area = max(max_rect_area, CalculateLargestRectangle(table));
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
      uniform_int_distribution<int> dis(1, 60);
      n = dis(gen), m = dis(gen);
    }
    vector<deque<bool>> A(n, deque<bool>(m));
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        uniform_int_distribution<int> true_or_false(0, 1);
        A[i][j] = true_or_false(gen);
      }
    }
    /*
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        cout << A[i][j] << ' ';
      }
      cout << endl;
    }
    */
    cout << MaxRectangleSubmatrix(A) << endl;
    cout << MaxRectangleSubmatrixBruteForce(A) << endl;
    assert(MaxRectangleSubmatrixBruteForce(A) == MaxRectangleSubmatrix(A));
  }
  return 0;
}
