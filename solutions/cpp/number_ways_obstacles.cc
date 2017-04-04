// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <deque>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
// Given the dimensions of A, n and m, and B, return the number of ways
// from A[0][0] to A[n - 1][m - 1] considering obstacles.
int NumberOfWaysWithObstacles(int n, int m, const vector<deque<bool>>& B) {
  if (B[0][0]) {  // No way to start from (0, 0) if B[0][0] == true.
    return 0;
  }

  vector<vector<int>> A(n, vector<int>(m, 0));
  A[0][0] = 1;
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < m; ++j) {
      if (B[i][j] == 0) {
        A[i][j] += (i < 1 ? 0 : A[i - 1][j]) + (j < 1 ? 0 : A[i][j - 1]);
      }
    }
  }
  return A.back().back();
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n, m;
  if (argc == 3) {
    n = atoi(argv[1]), m = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> dis(1, 10);
    n = dis(gen);
    m = dis(gen);
  }
  vector<deque<bool>> B(n, deque<bool>(m));
  for (size_t i = 0; i < n; ++i) {
    for (size_t j = 0; j < m; ++j) {
      uniform_int_distribution<int> dis(0, 9);
      int x = dis(gen);
      B[i][j] = ((x < 2) ? 1 : 0);
    }
  }
  for (size_t i = 0; i < n; ++i) {
    for (size_t j = 0; j < m; ++j) {
      cout << B[i][j] << ' ';
    }
    cout << endl;
  }
  cout << NumberOfWaysWithObstacles(n, m, B) << endl;
  return 0;
}
