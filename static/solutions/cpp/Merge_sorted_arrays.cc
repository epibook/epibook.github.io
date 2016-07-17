// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

#include "./Merge_sorted_arrays.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

void SimpleTest() {
  vector<vector<int>> S = {
      {1, 5, 10}, {2, 3, 100}, {2, 12, numeric_limits<int>::max()}};
  auto ans = MergeSortedArrays(S);
  vector<int> golden = {
      1, 2, 2, 3, 5, 10, 12, 100, numeric_limits<int>::max()};
  assert(equal(ans.begin(), ans.end(), golden.begin(), golden.end()));

  S = {{1}};
  ans = MergeSortedArrays(S);
  assert(ans.size() == 1 && ans.front() == 1);

  S = {{}, {1}, {2}};
  ans = MergeSortedArrays(S);
  assert(ans.size() == 2 && ans[0] == 1 && ans[1] == 2);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 100; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 100);
      n = dis(gen);
    }
    vector<vector<int>> S(n, vector<int>());
    for (size_t i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 500);
      S[i].resize(dis(gen));
      for (size_t j = 0; j < S[i].size(); ++j) {
        uniform_int_distribution<int> dis(-9999, 9999);
        S[i][j] = dis(gen);
      }
      sort(S[i].begin(), S[i].end());
    }
    vector<int> ans = MergeSortedArrays(S);
    for (size_t i = 1; i < ans.size(); ++i) {
      assert(ans[i - 1] <= ans[i]);
    }
  }
  return 0;
}
