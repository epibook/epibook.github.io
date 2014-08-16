// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int SearchFirst(const vector<int>& A, int k) {
  int l = 0, r = A.size() - 1, res = -1;
  while (l <= r) {
    int m = l + ((r - l) / 2);
    if (A[m] > k) {
      r = m - 1;
    } else if (A[m] == k) {
      // Records the solution and keep searching the left part.
      res = m, r = m - 1;
    } else {  // A[m] < k
      l = m + 1;
    }
  }
  return res;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 100000);
      n = dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> k_dis(0, n - 1);
    int k = k_dis(gen);
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(0, n - 1);
      A.emplace_back(dis(gen));
    }
    sort(A.begin(), A.end());
    int ans = SearchFirst(A, k);
    cout << "k = " << k << " locates at " << ans << endl;
    if (ans != -1) {
      cout << "A[k] = " << A[ans] << endl;
    }
    auto it = find(A.cbegin(), A.cend(), k);
    assert((it == A.cend() && ans == -1) || (distance(A.cbegin(), it) == ans));
  }
  return 0;
}
