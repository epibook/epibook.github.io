// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

int SearchSmallestHelper(const vector<int>&, int, int);

// @include
int SearchSmallest(const vector<int>& A) {
  return SearchSmallestHelper(A, 0, A.size() - 1);
}

int SearchSmallestHelper(const vector<int>& A, int left, int right) {
  if (left == right) {
    return left;
  }

  int mid = left + ((right - left) / 2);
  if (A[mid] > A[right]) {
    return SearchSmallestHelper(A, mid + 1, right);
  } else if (A[mid] < A[right]) {
    return SearchSmallestHelper(A, left, mid);
  } else {  // A[mid] == A[right].
    if (A[left] < A[mid]) {
      return left;
    }
    // We cannot eliminate either side so we compare the results from both
    // sides.
    int left_result = SearchSmallestHelper(A, left, mid);
    int right_result = SearchSmallestHelper(A, mid + 1, right);
    if (A[left_result] != A[right_result]) {
      return A[right_result] < A[left_result] ? right_result : left_result;
    }
    return right == A.size() - 1 ? right_result : left_result;
  }
}
// @exclude

// Hand-made tests
static void SimpleTest() {
  vector<int> A = {3, 1, 2};
  assert(1 == SearchSmallest(A));
  A = {0, 2, 4, 8};
  assert(0 == SearchSmallest(A));
  A = {16, 2, 4, 8};
  assert(1 == SearchSmallest(A));

  A = {100, 2, 5, 5};
  assert(1 == SearchSmallest(A));
  A = {1, 2, 3, 3, 3};
  assert(0 == SearchSmallest(A));
  A = {5, 2, 3, 3, 3};
  assert(1 == SearchSmallest(A));
  A = {5, 5, 2, 2, 2, 3, 3, 3};
  assert(2 == SearchSmallest(A));
  A = {0, 0, 4, 0};
  assert(3 == SearchSmallest(A));
  A = {-1, 0, 0, 0, 0};
  assert(0 == SearchSmallest(A));
  A = {0, -1, 0, 0, 0};
  assert(1 == SearchSmallest(A));
  A = {0, 0, -1, 0, 0};
  assert(2 == SearchSmallest(A));
  A = {0, 0, 0, -1, 0};
  assert(3 == SearchSmallest(A));
  A = {0, 0, 0, 0, -1};
  assert(4 == SearchSmallest(A));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    int n;
    if (argc == 2) {
      n = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> A_dis(0, 999999);
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
    */
    assert((shift + 1) % n == SearchSmallest(A));
  }
  return 0;
}
