// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

// @include
void RookAttack(vector<vector<int>>* A_ptr) {
  vector<vector<int>>& A = *A_ptr;
  size_t m = A.size(), n = A[0].size();
  bool has_first_row_zero = false;
  for (size_t j = 0; j < n; ++j) {
    if (!A[0][j]) {
      has_first_row_zero = true;
      break;
    }
  }
  bool has_first_column_zero = false;
  for (size_t i = 0; i < m; ++i) {
    if (!A[i][0]) {
      has_first_column_zero = true;
      break;
    }
  }

  for (size_t i = 1; i < m; ++i) {
    for (size_t j = 1; j < n; ++j) {
      if (!A[i][j]) {
        A[i][0] = A[0][j] = 0;
      }
    }
  }

  for (size_t i = 1; i < m; ++i) {
    if (!A[i][0]) {
      for (size_t j = 1; j < n; ++j) {
        A[i][j] = 0;
      }
    }
  }

  for (size_t j = 1; j < n; ++j) {
    if (!A[0][j]) {
      for (size_t i = 1; i < m; ++i) {
        A[i][j] = 0;
      }
    }
  }

  if (has_first_row_zero) {
    for (size_t j = 0; j < n; ++j) {
      A[0][j] = 0;
    }
  }
  if (has_first_column_zero) {
    for (size_t i = 0; i < m; ++i) {
      A[i][0] = 0;
    }
  }
}
// @exclude

void CheckAns(const vector<vector<int>>& A, const vector<vector<int>>& ans) {
  for (size_t i = 0; i < A.size(); ++i) {
    for (size_t j = 0; j < A[i].size(); ++j) {
      if (!A[i][j]) {
        for (size_t k = 0; k < ans.size(); ++k) {
          assert(!ans[k][j]);
        }
        for (size_t k = 0; k < ans[i].size(); ++k) {
          assert(!ans[i][k]);
        }
      }
    }
  }
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    size_t m, n;
    if (argc == 3) {
      m = stoul(argv[1]), n = stoul(argv[2]);
    } else {
      uniform_int_distribution<size_t> dis(1, 50);
      m = dis(gen), n = dis(gen);
    }
    vector<vector<int>> A(m, vector<int>(n));
    uniform_int_distribution<int> zero_or_one(0, 1);
    for (size_t i = 0; i < m; ++i) {
      for (size_t j = 0; j < n; ++j) {
        A[i][j] = zero_or_one(gen);
      }
    }
    auto copy_A(A);
    cout << "m = " << m << ", n = " << n << endl;
    RookAttack(&A);
    CheckAns(copy_A, A);
  }
  return 0;
}
