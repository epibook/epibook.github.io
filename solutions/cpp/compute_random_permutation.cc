// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./Offline_sampling.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> ComputeRandomPermutation(int n) {
  vector<int> permutation(n);
  // Initializes permutation to 0, 1, 2, ..., n - 1.
  iota(permutation.begin(), permutation.end(), 0);
  RandomSampling(permutation.size(), &permutation);
  return permutation;
}
// @exclude

int main(int argc, char* argv[]) {
  int n;
  default_random_engine gen((random_device())());
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> n_dis(1, 1000000);
    n = n_dis(gen);
  }
  cout << n << endl;
  auto result = ComputeRandomPermutation(n);
  sort(result.begin(), result.end());
  for (int i = 0; i < n; ++i) {
    assert(result[i] == i);
  }
  return 0;
}
