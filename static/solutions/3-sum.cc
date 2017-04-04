// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./2_sum.h"

using TwoSum::HasTwoSum;
using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
bool HasThreeSum(vector<int> A, int t) {
  sort(A.begin(), A.end());

  for (int a : A) {
    // Finds if the sum of two numbers in A equals to t - a.
    if (HasTwoSum(A, t - a)) {
      return true;
    }
  }
  return false;
}
// @exclude

// n^3 solution.
bool CheckAns(const vector<int>& A, int t) {
  for (int i = 0; i < A.size(); ++i) {
    for (int j = 0; j < A.size(); ++j) {
      for (int k = 0; k < A.size(); ++k) {
        if (A[i] + A[j] + A[k] == t) {
          return true;
        }
      }
    }
  }
  return false;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n, T;
    if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> dis(0, n - 1);
      T = dis(gen);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
      uniform_int_distribution<int> T_dis(0, n - 1);
      T = T_dis(gen);
    }
    vector<int> A;
    for (size_t i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(-100000, 100000);
      A.emplace_back(dis(gen));
    }
    cout << boolalpha << HasThreeSum(A, T) << endl;
    assert(CheckAns(A, T) == HasThreeSum(A, T));
  }
  return 0;
}
