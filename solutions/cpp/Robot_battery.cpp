// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int FindBatteryCapacity(const vector<int>& h) {
  int min_height = numeric_limits<int>::max(), capacity = 0;
  for (const int &height : h) {
    capacity = max(capacity, height - min_height);
    min_height = min(min_height, height);
  }
  return capacity;
}
// @exclude

// O(n^2) checking answer.
int CheckAns(const vector<int>& h) {
  int cap = 0;
  for (int i = 1; i < h.size(); ++i) {
    for (int j = 0; j < i; ++j) {
      cap = max(cap, h[i] - h[j]);
    }
  }
  return cap;
}

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
    uniform_int_distribution<int> dis(0, numeric_limits<int>::max());
    for (int i = 0; i < n; ++i) {
      A.emplace_back(dis(gen));
    }
    cout << FindBatteryCapacity(A) << endl;
    assert(CheckAns(A) == FindBatteryCapacity(A));
  }
  return 0;
}
