// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
int SearchSmallest(const vector<int>& A) {
  int left = 0, right = A.size() - 1;
  while (left < right) {
    int mid = left + ((right - left) / 2);
    if (A[mid] > A[right]) {
      // Minimum must be in [mid + 1 : right].
      left = mid + 1;
    } else {  // A[mid] < A[right].
      // Minimum cannot be in [mid + 1 : right] so it must be in [left : mid].
      right = mid;
    }
  }
  // Loop ends when left == right.
  return left;
}
// @exclude

void SimpleTest() {
  vector<int> A = {3, 1, 2};
  assert(1 == SearchSmallest(A));
  A = {0, 2, 4, 8};
  assert(0 == SearchSmallest(A));
  A[0] = 16;
  assert(1 == SearchSmallest(A));
  A = {2, 3, 4};
  assert(0 == SearchSmallest(A));
  A = {100, 101, 102, 2, 5};
  assert(3 == SearchSmallest(A));
  A = {10, 20, 30, 40, 5};
  assert(4 == SearchSmallest(A));
}

int main(int argc, char* argv[]) {
  SimpleTest();
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
    unordered_set<int> table;
    for (size_t i = 0; i < n; ++i) {
      while (true) {
        uniform_int_distribution<int> dis(0, 100000);
        int x = dis(gen);
        if (table.emplace(x).second) {
          A.emplace_back(x);
          break;
        }
      }
    }
    sort(A.begin(), A.end());
    uniform_int_distribution<int> n_dis(0, n - 1);
    int shift = n_dis(gen);
    reverse(A.begin(), A.end());
    reverse(A.begin(), A.begin() + shift + 1);
    reverse(A.begin() + shift + 1, A.end());
    /*
       for (size_t i = 0; i < n; ++i) {
       cout << A[i] << ' ';
       }
       cout << endl;
     */
    assert((shift + 1) % n == SearchSmallest(A));
  }
  return 0;
}
