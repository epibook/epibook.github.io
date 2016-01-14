// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./Longest_nondecreasing_subsequence_n2.h"
#include "./Longest_nondecreasing_subsequence_nlogn.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<int> A;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(0, 99999999);
      A.emplace_back(dis(gen));
    }
    cout << "n = " << n << endl;
    int ret_length = LNS_nlogn::LongestNondecreasingSubsequenceLength(A);
    int another_length = LNS_n2::LongestNondecreasingSubsequenceLength(A);
    assert(ret_length == another_length);
  }
  return 0;
}
