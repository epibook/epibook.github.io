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

bool search_target_helper(const vector<int>& A, int left, int right, int t);

// @include
bool search_target(const vector<int>& A, int t) {
  return search_target_helper(A, 0, A.size() - 1, t);
}

bool search_target_helper(const vector<int>& A, int left, int right, int t) {
  if (left > right) {
    return false;
  }
  int mid = left + ((right - left) / 2);
  if (A[mid] == t) {
    return true;
  }

  if (A[left] < A[mid]) {
    if (A[left] <= t && t < A[mid]) {
      return search_target_helper(A, left, mid - 1, t);
    }
    return search_target_helper(A, mid + 1, right, t);
  } else if (A[left] > A[mid]) {
    if (A[mid] < t & t <= A[right]) {
      return search_target_helper(A, mid + 1, right, t);
    }
    return search_target_helper(A, left, mid - 1, t);
  }
  // A[left] == A[mid]
  int l_res = search_target_helper(A, left, mid - 1, t);
  int r_res = search_target_helper(A, mid + 1, right, t);
  return l_res || r_res;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    int n;
    if (argc == 2) {
      n = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(0, 10000);
      n = dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> A_dis(0, 9999);
    generate_n(back_inserter(A), n, [&] { return A_dis(gen); });
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
    cout << "target = " << t << endl;
    */
    int t = A_dis(gen);
    assert((find(A.begin(), A.end(), t) != A.end()) == search_target(A, t));
  }

  // hand-made tests
  vector<int> A;
  A.emplace_back(2);
  A.emplace_back(2);
  A.emplace_back(2);
  assert(search_target(A, 2));
  assert(!search_target(A, 3));

  A.clear();
  A.emplace_back(100);
  A.emplace_back(2);
  A.emplace_back(5);
  A.emplace_back(5);
  assert(search_target(A, 5));
  assert(search_target(A, 100));
  assert(!search_target(A, 3));

  A.clear();
  A.emplace_back(1);
  A.emplace_back(2);
  A.emplace_back(3);
  A.emplace_back(3);
  A.emplace_back(3);
  assert(search_target(A, 1));
  assert(search_target(A, 3));
  assert(!search_target(A, 4));

  A.clear();
  A.emplace_back(5);
  A.emplace_back(2);
  A.emplace_back(3);
  A.emplace_back(3);
  A.emplace_back(3);
  assert(search_target(A, 5));
  assert(search_target(A, 3));
  assert(!search_target(A, 1));
  return 0;
}
