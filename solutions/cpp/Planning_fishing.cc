// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int MaximizeFishing(vector<vector<int>> A) {
  for (int i = 0; i < A.size(); ++i) {
    for (int j = 0; j < A[i].size(); ++j) {
      A[i][j] += max(i < 1 ? 0 : A[i - 1][j], j < 1 ? 0 : A[i][j - 1]);
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
    uniform_int_distribution<int> dis(1, 100);
    n = dis(gen), m = dis(gen);
  }
  vector<vector<int>> A(n, vector<int>(m));
  for (size_t i = 0; i < n; ++i) {
    for (size_t j = 0; j < m; ++j) {
      uniform_int_distribution<int> dis(0, 999);
      A[i][j] = dis(gen);
    }
  }
  for (size_t i = 0; i < n; ++i) {
    for (size_t j = 0; j < m; ++j) {
      cout << A[i][j] << ' ';
    }
    cout << endl;
  }
  cout << MaximizeFishing(A) << endl;
  return 0;
}
