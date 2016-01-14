// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::minmax;
using std::pair;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
struct MinMax {
  int min, max;
};

MinMax FindMinMax(const vector<int>& A) {
  if (A.size() <= 1) {
    return {A.front(), A.front()};
  }

  pair<int, int> global_min_max = minmax(A[0], A[1]);
  // Process two elements at a time.
  for (int i = 2; i + 1 < A.size(); i += 2) {
    pair<int, int> local_min_max = minmax(A[i], A[i + 1]);
    global_min_max = {min(global_min_max.first, local_min_max.first),
                      max(global_min_max.second, local_min_max.second)};
  }
  // If there is odd number of elements in the array, we still
  // need to compare the last element with the existing answer.
  if (A.size() % 2) {
    global_min_max = {min(global_min_max.first, A.back()),
                      max(global_min_max.second, A.back())};
  }
  return {global_min_max.first, global_min_max.second};
}
// @exclude

static void SimpleTest() {
  vector<int> A = {-1, 3, -4, 6, 4, 10, 4, 4, 9};
  auto res = FindMinMax(A);
  assert(-4 == res.min && 10 == res.max);
  A[5] = -12;
  res = FindMinMax(A);
  assert(-12 == res.min && 9 == res.max);

  A = {-1, 3, -4};
  res = FindMinMax(A);
  assert(-4 == res.min && 3 == res.max);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<int> A;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(0, 999999);
      A.emplace_back(dis(gen));
    }
    MinMax res = FindMinMax(A);
    assert(res.min == *min_element(A.cbegin(), A.cend()) &&
           res.max == *max_element(A.cbegin(), A.cend()));
  }
  return 0;
}
