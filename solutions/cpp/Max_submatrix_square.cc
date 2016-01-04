// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::max;
using std::min;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// O(m^3 n^3) time solution.
int CheckAns(const vector<deque<bool>>& A) {
  int max = 0;
  for (int a = 0; a < A.size(); ++a) {
    for (int b = 0; b < A[a].size(); ++b) {
      for (int r = 1; a + r <= A.size() && b + r <= A[0].size(); ++r) {
        int count = 0;
        bool all_1 = true;
        for (int c = a; c < a + r; ++c) {
          for (int d = b; d < b + r; ++d) {
            if (A[c][d]) {
              ++count;
            } else {
              all_1 = false;
              count = 0;
              break;
            }
          }
          if (all_1 == false) {
            break;
          }
        }
        if (count > max) {
          max = count;
        }
      }
    }
  }
  return max;
}

// @include
struct MaxHW {
  int h, w;
};

int MaxSquareSubmatrix(const vector<deque<bool>>& A) {
  // DP table stores (h, w) for each (i, j).
  vector<vector<MaxHW>> table(A.size(), vector<MaxHW>(A.front().size()));

  for (int i = A.size() - 1; i >= 0; --i) {
    for (int j = A[i].size() - 1; j >= 0; --j) {
      // Finds the largest h such that (i, j) to (i + h - 1, j) are feasible.
      // Finds the largest w such that (i, j) to (i, j + w - 1) are feasible.
      table[i][j] =
          A[i][j] ? MaxHW{i + 1 < A.size() ? table[i + 1][j].h + 1 : 1,
                          j + 1 < A[i].size() ? table[i][j + 1].w + 1 : 1}
                  : MaxHW{0, 0};
    }
  }

  // A table stores the length of the largest square for each (i, j).
  vector<vector<int>> s(A.size(), vector<int>(A.front().size(), 0));
  int max_square_area = 0;
  for (int i = A.size() - 1; i >= 0; --i) {
    for (int j = A[i].size() - 1; j >= 0; --j) {
      int side = min(table[i][j].h, table[i][j].w);
      if (A[i][j]) {
        // Gets the length of largest square with bottom-left corner (i, j).
        if (i + 1 < A.size() && j + 1 < A[i + 1].size()) {
          side = min(s[i + 1][j + 1] + 1, side);
        }
        s[i][j] = side;
        max_square_area = max(max_square_area, side * side);
      }
    }
  }
  return max_square_area;
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
    cout << MaxSquareSubmatrix(A) << endl;
    cout << CheckAns(A) << endl;
    assert(CheckAns(A) == MaxSquareSubmatrix(A));
  }
  return 0;
}
