// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <iostream>
#include <iterator>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

void MatrixClockwise(const vector<vector<int>>&, int, vector<int>*);

// @include
vector<int> MatrixInSpiralOrder(const vector<vector<int>> &A) {
  vector<int> result;
  for (int offset = 0; offset < ceil(0.5 * A.size()); ++offset) {
    MatrixClockwise(A, offset, &result);
  }
  return result;
}

void MatrixClockwise(const vector<vector<int>> &A, int offset,
                     vector<int>* result) {
  if (offset == A.size() - offset - 1) {
    // A has odd dimension, and we are at the center of the matrix A.
    result->emplace_back(A[offset][offset]);
    return;
  }

  for (int j = offset; j < A.size() - offset - 1; ++j) {
    result->emplace_back(A[offset][j]);
  }
  for (int i = offset; i < A.size() - offset - 1; ++i) {
    result->emplace_back(A[i][A.size() - offset - 1]);
  }
  for (int j = A.size() - offset - 1; j > offset; --j) {
    result->emplace_back(A[A.size() - offset - 1][j]);
  }
  for (int i = A.size() - offset - 1; i > offset; --i) {
    result->emplace_back(A[i][offset]);
  }
}
// @exclude

void SimpleTest() {
  vector<vector<int>> A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
  auto result = MatrixInSpiralOrder(A);
  vector<int> golden_result = {1, 2, 3, 6, 9, 8, 7, 4, 5};
  assert(result.size() == golden_result.size() &&
         equal(result.begin(), result.end(), golden_result.begin()));
}

int main(int argc, char *argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  int N;
  if (argc == 2) {
    N = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 50);
    N = dis(gen);
  }
  vector<vector<int>> A(N, vector<int>(N));
  int x = 1;
  for (size_t i = 0; i < N; ++i) {
    for (size_t j = 0; j < N; ++j) {
      A[i][j] = x++;
    }
  }
  auto result = MatrixInSpiralOrder(A);
  copy(result.begin(), result.end(), ostream_iterator<int>(cout, " "));
  cout << endl;
  return 0;
}
