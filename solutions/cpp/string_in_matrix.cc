// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <iterator>
#include <random>
#include <tuple>
#include <unordered_set>
#include <vector>

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::get;
using std::hash;
using std::ostream_iterator;
using std::random_device;
using std::tuple;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

vector<vector<int>> RandMatrix(int n) {
  vector<vector<int>> matrix(n, vector<int>(n));
  default_random_engine gen((random_device())());
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < n; ++j) {
      uniform_int_distribution<int> dis(0, n - 1);
      matrix[i][j] = dis(gen);
    }
  }
  return matrix;
}

struct HashTuple;

bool IsPatternSuffixContainedStartingAtXY(
    const vector<vector<int>>&, int, int, const vector<int>&, int,
    unordered_set<tuple<int, int, int>, HashTuple>*);

// @include
struct HashTuple {
  size_t operator()(const tuple<int, int, int>& t) const {
    return hash<int>()(get<0>(t) ^ get<1>(t) * 1021 ^ get<2>(t) * 1048573);
  }
};

bool IsPatternContainedInGrid(const vector<vector<int>>& grid,
                              const vector<int>& pattern) {
  // Each entry in previous_attempts is a point in the grid and suffix of
  // pattern (identified by its offset). Presence in previous_attempts
  // indicates the suffix is not contained in the grid starting from that
  // point.
  unordered_set<tuple<int, int, int>, HashTuple> previous_attempts;
  for (int i = 0; i < grid.size(); ++i) {
    for (int j = 0; j < grid[i].size(); ++j) {
      if (IsPatternSuffixContainedStartingAtXY(grid, i, j, pattern, 0,
                                               &previous_attempts)) {
        return true;
      }
    }
  }
  return false;
}

bool IsPatternSuffixContainedStartingAtXY(
    const vector<vector<int>>& grid, int x, int y, const vector<int>& pattern,
    int offset,
    unordered_set<tuple<int, int, int>, HashTuple>* previous_attempts) {
  if (pattern.size() == offset) {
    // Nothing left to complete.
    return true;
  }
  // Check if (x, y) lies outside the grid.
  if (x < 0 || x >= grid.size() || y < 0 || y >= grid[x].size() ||
      previous_attempts->find({x, y, offset}) != previous_attempts->cend() ||
      grid[x][y] != pattern[offset]) {
    return false;
  }

  if (IsPatternSuffixContainedStartingAtXY(grid, x - 1, y, pattern,
                                           offset + 1, previous_attempts) ||
      IsPatternSuffixContainedStartingAtXY(grid, x + 1, y, pattern,
                                           offset + 1, previous_attempts) ||
      IsPatternSuffixContainedStartingAtXY(grid, x, y - 1, pattern,
                                           offset + 1, previous_attempts) ||
      IsPatternSuffixContainedStartingAtXY(grid, x, y + 1, pattern,
                                           offset + 1, previous_attempts)) {
    return true;
  }
  previous_attempts->emplace(x, y, offset);
  return false;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(2, 10);
    n = dis(gen);
  }
  vector<vector<int>> A = RandMatrix(n);
  for (size_t i = 0; i < A.size(); ++i) {
    for (size_t j = 0; j < A[i].size(); ++j) {
      cout << A[i][j] << ' ';
    }
    cout << endl;
  }
  cout << "S = ";
  uniform_int_distribution<int> dis(1, n * n / 2);
  vector<int> S(1 + dis(gen));
  for (size_t i = 0; i < S.size(); ++i) {
    uniform_int_distribution<int> dis(0, n - 1);
    S[i] = dis(gen);
  }
  copy(S.begin(), S.end(), ostream_iterator<int>(cout, " "));
  cout << endl << boolalpha << IsPatternContainedInGrid(A, S) << endl;
  return 0;
}
