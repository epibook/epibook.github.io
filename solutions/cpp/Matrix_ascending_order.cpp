// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <deque>
#include <iostream>
#include <queue>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::max;
using std::pair;
using std::priority_queue;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<vector<int>> A;

struct Compare {
  bool operator()(const pair<int, int> &lhs, const pair<int, int> &rhs) {
    int lhs_val = A[lhs.first][lhs.second];
    int rhs_val = A[rhs.first][rhs.second];
    return lhs_val > rhs_val;
  }
};

void print_matrix_ascending_order(const vector<vector<int>> &A) {
  priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> min_heap;

  vector<deque<bool>> used(A.size(), deque<bool>(A.size(), false));

  min_heap.emplace(0, 0), used[0][0] = true;
  while (!min_heap.empty()) {
    pair<int, int> top = min_heap.top();
    min_heap.pop();
    // Check the (i + 1, j) element is available or not
    if (top.first + 1 < A.size() && !used[top.first + 1][top.second]) {
      min_heap.emplace(top.first + 1, top.second);
      used[top.first + 1][top.second] = true;
    }

    // Check the (i, j + 1) element is available or not
    if (top.second + 1 < A.size() && !used[top.first][top.second + 1]) {
      min_heap.emplace(top.first, top.second + 1);
      used[top.first][top.second + 1] = true;
    }
    cout << A[top.first][top.second] << endl;
  }
}
// @exclude

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    n = dis(gen);
  }
  A.resize(n, vector<int>(n));
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < n; ++j) {
      uniform_int_distribution<int> dis(1, n * n - 1);
      A[i][j] = dis(gen);
      if (i && j) {
        A[i][j] += max(A[i - 1][j], A[i][j - 1]);
      } else if (i) {
        A[i][j] += A[i - 1][j];
      } else if (j) {
        A[i][j] += A[i][j - 1];
      }
    }
  }
  print_matrix_ascending_order(A);
  return 0;
}
