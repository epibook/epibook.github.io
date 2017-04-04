// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

int ComputeNumberOfWaysToXY(int, int, vector<vector<int>>*);

// @include
int NumberOfWays(int n, int m) {
  vector<vector<int>> number_of_ways(n, vector<int>(m, 0));
  return ComputeNumberOfWaysToXY(n - 1, m - 1, &number_of_ways);
}

int ComputeNumberOfWaysToXY(int x, int y,
                            vector<vector<int>>* number_of_ways_ptr) {
  if (x == 0 && y == 0) {
    return 1;
  }

  vector<vector<int>>& number_of_ways = *number_of_ways_ptr;
  if (number_of_ways[x][y] == 0) {
    int ways_top =
        x == 0 ? 0 : ComputeNumberOfWaysToXY(x - 1, y, number_of_ways_ptr);
    int ways_left =
        y == 0 ? 0 : ComputeNumberOfWaysToXY(x, y - 1, number_of_ways_ptr);
    number_of_ways[x][y] = ways_top + ways_left;
  }
  return number_of_ways[x][y];
}
// @exclude

int ComputeNumberOfWaysSpaceEfficient(int n, int m) {
  if (n < m) {
    swap(n, m);
  }
  vector<int> A(m, 1);
  for (int i = 1; i < n; ++i) {
    int prev_res = 0;
    if (n < m) {
      swap(n, m);
    }
    for (int j = 0; j < m; ++j) {
      A[j] = A[j] + prev_res;
      prev_res = A[j];
    }
  }
  return A[m - 1];
}

int CheckAns(int n, int k) {
  vector<vector<int>> table(n + 1, vector<int>(k + 1));
  // Basic case: C(i, 0) = 1.
  for (int i = 0; i <= n; ++i) {
    table[i][0] = 1;
  }
  // Basic case: C(i, i) = 1.
  for (int i = 1; i <= k; ++i) {
    table[i][i] = 1;
  }
  // C(i, j) = C(i - 1, j) + C(i - 1, j - 1).
  for (int i = 2; i <= n; ++i) {
    for (int j = 1; j < i && j <= k; ++j) {
      table[i][j] = table[i - 1][j] + table[i - 1][j - 1];
    }
  }
  return table[n][k];
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n, m;
    if (argc == 3) {
      n = atoi(argv[1]), m = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> dis(1, 10);
      n = dis(gen);
      m = dis(gen);
    }
    cout << "n = " << n << ", m = " << m
         << ", number of ways = " << NumberOfWays(n, m) << endl;
    assert(CheckAns(n + m - 2, m - 1) == NumberOfWays(n, m));
    assert(ComputeNumberOfWaysSpaceEfficient(n, m) == NumberOfWays(n, m));
    if (argc == 3) {
      break;
    }
  }
  return 0;
}
