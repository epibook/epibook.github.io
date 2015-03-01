// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./Merge_sorted_arrays.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 100; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<vector<int>> S(n, vector<int>());
    cout << "n = " << n << endl;
    for (size_t i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 500);
      S[i].resize(dis(gen));
      for (size_t j = 0; j < S[i].size(); ++j) {
        uniform_int_distribution<int> dis(-9999, 9999);
        S[i][j] = dis(gen);
      }
      sort(S[i].begin(), S[i].end());
    }
    /*
       for (size_t i = 0; i < n; ++i) {
       for (size_t j = 0; j < S[i].size(); ++j) {
       cout << S[i][j] << ' ';
       }
       cout << endl;
       }
     */
    vector<int> ans = MergeSortedArrays(S);
    for (size_t i = 1; i < ans.size(); ++i) {
      assert(ans[i - 1] <= ans[i]);
    }
  }
  return 0;
}
