// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int close_search(const vector<int>& A, int k) {
  int idx = 0;
  while (idx < A.size() && A[idx] != k) {
    idx += abs(A[idx] - k);
  }
  return idx < A.size() ? idx : -1;  // -1 means no result.
}
// @exclude

int main(int argc, char* argv[]) {
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
    uniform_int_distribution<int> dis(0, 9);
    A.emplace_back(dis(gen));
    for (size_t i = 1; i < n; ++i) {
      uniform_int_distribution<int> shift_dis(-1, 1);
      int shift = shift_dis(gen);
      A.emplace_back(A[i - 1] + shift);
    }
    uniform_int_distribution<int> k_dis(0, 99);
    int k = k_dis(gen);
    int ans = close_search(A, k);
    cout << ans << endl;
    if (ans != -1) {
      assert(A[ans] == k);
    } else {
      bool found = false;
      for (size_t i = 0; i < A.size(); ++i) {
        if (A[i] == k) {
          found = true;
          break;
        }
      }
      assert(found == false);
    }
  }
  return 0;
}
