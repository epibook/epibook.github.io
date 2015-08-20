// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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

bool MatchHelper(const vector<vector<int>>& A, const vector<int>& S, int i,
                 int j, int len,
                 unordered_set<tuple<int, int, int>, HashTuple>* cache);

// @include
struct HashTuple {
  size_t operator()(const tuple<int, int, int>& t) {
    return hash<int>()(get<0>(t)) ^ hash<int>()(get<1>(t)) ^
           hash<int>()(get<2>(t));
  }
};

bool Match(const vector<vector<int>>& A, const vector<int>& S) {
  unordered_set<tuple<int, int, int>, HashTuple> cache;
  for (int i = 0; i < A.size(); ++i) {
    for (int j = 0; j < A[i].size(); ++j) {
      if (MatchHelper(A, S, i, j, 0, &cache)) {
        return true;
      }
    }
  }
  return false;
}

bool MatchHelper(const vector<vector<int>>& A, const vector<int>& S, int i,
                 int j, int len,
                 unordered_set<tuple<int, int, int>, HashTuple>* cache) {
  if (S.size() == len) {
    return true;
  }

  if (i < 0 || i >= A.size() || j < 0 || j >= A[i].size() ||
      cache->find({i, j, len}) != cache->cend()) {
    return false;
  }

  if (A[i][j] == S[len] && (MatchHelper(A, S, i - 1, j, len + 1, cache) ||
                            MatchHelper(A, S, i + 1, j, len + 1, cache) ||
                            MatchHelper(A, S, i, j - 1, len + 1, cache) ||
                            MatchHelper(A, S, i, j + 1, len + 1, cache))) {
    return true;
  }
  cache->emplace(i, j, len);
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
  cout << endl << boolalpha << Match(A, S) << endl;
  return 0;
}
