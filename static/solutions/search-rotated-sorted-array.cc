// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
int search_target(const vector<int>& A, int t) {
  if (A.empty()) {
    return -1;
  }

  size_t left = 0, right = A.size() - 1;
  while (left <= right) {
    size_t mid = left + ((right - left) / 2);
    if (A[mid] == t) {
      return static_cast<int>(mid);
    }

    if (A[left] <= A[mid]) {
      if (A[left] <= t && t < A[mid]) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    } else {  // A[left] > A[mid]
      if (A[mid] < t && t <= A[right]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
  }
  return -1;
}
// @exclude

int check_ans(const vector<int>& A, int t) {
  for (size_t i = 0; i < A.size(); ++i) {
    if (A[i] == t) {
      return static_cast<int>(i);
    }
  }
  return -1;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(0, 10000);
      n = dis(gen);
    }
    vector<int> A;
    unordered_set<int> table;
    uniform_int_distribution<int> x_dis(0, 100000);
    for (int i = 0; i < n; ++i) {
      while (true) {
        int x = x_dis(gen);
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
    int t = x_dis(gen);
    assert(check_ans(A, t) == search_target(A, t));
  }

  // hand-made tests.
  vector<int> A;
  A.emplace_back(2);
  A.emplace_back(3);
  A.emplace_back(4);
  assert(0 == search_target(A, 2));
  A.clear();
  A.emplace_back(100);
  A.emplace_back(101);
  A.emplace_back(102);
  A.emplace_back(2);
  A.emplace_back(5);
  assert(3 == search_target(A, 2));
  A.clear();
  A.emplace_back(10);
  A.emplace_back(20);
  A.emplace_back(30);
  A.emplace_back(40);
  A.emplace_back(5);
  assert(4 == search_target(A, 5));
  return 0;
}
