// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::exception;
using std::max;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int BinarySearchUnknownLength(const vector<int>& A, int k) {
  // Find the possible range where k exists.
  int p = 0;
  while (true) {
    try {
      int idx = (1 << p) - 1;  // 2^p - 1.
      if (A.at(idx) == k) {
        return idx;
      } else if (A.at(idx) > k) {
        break;
      }
    } catch (const exception& e) {
      break;
    }
    ++p;
  }

  // Binary search between indices 2^(p - 1) and 2^p - 2, inclusive.
  int left = max(0, 1 << (p - 1)), right = (1 << p) - 2;
  while (left <= right) {
    int mid = left + ((right - left) / 2);
    try {
      if (A.at(mid) == k) {
        return mid;
      } else if (A.at(mid) > k) {
        right = mid - 1;
      } else {  // A.at(mid) < k
        left = mid + 1;
      }
    } catch (const exception& e) {
      right = mid - 1;  // Search the left part if out-of-bound.
    }
  }
  return -1;  // Nothing matched k.
}
// @exclude

void SmallTest() {
  vector<int> A = {1, 2, 3};
  assert(BinarySearchUnknownLength(A, 3) == 2);
  assert(BinarySearchUnknownLength(A, 1) == 0);
  assert(BinarySearchUnknownLength(A, 2) == 1);
  assert(BinarySearchUnknownLength(A, 4) == -1);
  assert(BinarySearchUnknownLength(A, -1) == -1);
}

int main(int argc, char* argv[]) {
  SmallTest();
  int n, k;
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> dis(0, 99999);
      k = dis(gen);
    } else if (argc == 3) {
      n = atoi(argv[1]);
      k = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> n_dis(1, 1000000);
      n = n_dis(gen);
      uniform_int_distribution<int> k_dis(0, (n * 2) - 1);
      k = k_dis(gen);
    }
    vector<int> A;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> k_dis(0, (n * 2) - 1);
      A.emplace_back(k_dis(gen));
    }
    sort(A.begin(), A.end());
    cout << n << ' ' << k << endl;
    int idx = BinarySearchUnknownLength(A, k);
    cout << idx << endl;
    assert((idx == -1 && !binary_search(A.cbegin(), A.cend(), k)) ||
           A[idx] == k);
  }
  return 0;
}
