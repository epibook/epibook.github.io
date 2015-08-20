// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

void print_matrix(const vector<vector<int>> &A) {
  for (int i = 0; i < A.size(); ++i) {
    // copy(A[i].begin(), A[i].end(), ostream_iterator<int>(cout, " "));
    for (int j = 0; j < A.size(); ++j) {
      cout << "A[" << i << "," << j << "] = " << A[i][j] << "  ";
    }
    cout << endl;
  }
}

void check_answer(const vector<vector<int>> &A) {
  int k = 1;
  for (int j = A.size() - 1; j >= 0; --j) {
    for (int i = 0; i < A.size(); ++i) {
      assert(k++ == A[i][j]);
    }
  }
}

// @include
void rotate_matrix(vector<vector<int>> *A_ptr) {
  auto &A = *A_ptr;
  reverse(A.begin(), A.end());
  for (size_t i = 0; i < A.size(); ++i) {
    for (size_t j = i + 1; j < A.size(); ++j) {
      swap(A[i][j], A[j][i]);
    }
  }
}
// @exclude

int main(int argc, char *argv[]) {
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
    vector<vector<int>> A(1 << n, vector<int>(1 << n));
    int k = 1;
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A[i].size(); ++j) {
        A[i][j] = k++;
      }
    }
    print_matrix(A);
    rotate_matrix(&A);
    check_answer(A);
    print_matrix(A);
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(1, 10);
    for (int times = 0; times < 100; ++times) {
      n = dis(gen);
      vector<vector<int>> A(1 << n, vector<int>(1 << n));
      int k = 1;
      for (int i = 0; i < A.size(); ++i) {
        for (int j = 0; j < A[i].size(); ++j) {
          A[i][j] = k++;
        }
      }
      rotate_matrix(&A);
      check_answer(A);
    }
  }
  return 0;
}
