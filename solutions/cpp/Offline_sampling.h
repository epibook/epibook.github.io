// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_OFFLINE_SAMPLING_H_
#define SOLUTIONS_OFFLINE_SAMPLING_H_

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::default_random_engine;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

// @include
void RandomSampling(int k, vector<int>* A_ptr) {
  vector<int>& A = *A_ptr;
  default_random_engine seed((random_device())());  // Random num generator.
  for (int i = 0; i < k; ++i) {
    // Generate a random index in [i, A.size() - 1].
    uniform_int_distribution<int> rand_idx_gen(i, A.size() - 1);
    swap(A[i], A[rand_idx_gen(seed)]);
  }
}
// @exclude
#endif  // SOLUTIONS_OFFLINE_SAMPLING_H_
