// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> OfflineSampling(vector<int> A, int k) {
  default_random_engine gen((random_device())());  // Random num generator.
  for (int i = 0; i < k; ++i) {
    // Generate a random int in [i, A.size() - 1].
    uniform_int_distribution<int> dis(i, A.size() - 1);
    swap(A[i], A[dis(gen)]);
  }
  A.resize(k);
  return A;
}
// @exclude

int main(int argc, char *argv[]) {
  int n, k;
  default_random_engine gen((random_device())());
  vector<int> A;
  if (argc == 2) {
    n = atoi(argv[1]);
    uniform_int_distribution<int> dis(1, n);
    k = dis(gen);
  } else if (argc == 3) {
    n = atoi(argv[1]);
    k = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> n_dis(1, 1000000);
    n = n_dis(gen);
    uniform_int_distribution<int> k_dis(1, n);
    k = k_dis(gen);
  }
  for (int i = 0; i < n; ++i) {
    A.emplace_back(i);
  }
  cout << n << ' ' << k << endl;
  vector<int> ans = OfflineSampling(A, k);
  assert(ans.size() == k);
  for (int i = 0; i < k; ++i) {
    cout << ans[i] << ' ';
  }
  cout << endl;
  return 0;
}
