// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <stdexcept>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::length_error;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

int PartitionAroundPivot(int, int, int, vector<int>*);

// @include
int FindKthLargest(vector<int> A, int k) {
  int left = 0, right = A.size() - 1;
  default_random_engine gen((random_device())());
  while (left <= right) {
    // Generates a random integer in [left, right].
    uniform_int_distribution<int> dis(left, right);
    int pivot_idx = dis(gen);
    int new_pivot_idx = PartitionAroundPivot(left, right, pivot_idx, &A);
    if (new_pivot_idx == k - 1) {
      return A[new_pivot_idx];
    } else if (new_pivot_idx > k - 1) {
      right = new_pivot_idx - 1;
    } else {  // new_pivot_idx < k - 1.
      left = new_pivot_idx + 1;
    }
  }
  // @exclude
  throw length_error("no k-th node in array A");
  // @include
}

// Partition A[left : right] around pivot_idx, returns the new index of the
// pivot, new_pivot_idx, after partition. After partitioning,
// A[left : new_pivot_idx - 1] contains elements that are greater than the
// pivot, and A[new_pivot_idx + 1 : right] contains elements that are less
// than the pivot.
int PartitionAroundPivot(int left, int right, int pivot_idx,
                         vector<int>* A_ptr) {
  auto& A = *A_ptr;
  int pivot_value = A[pivot_idx];
  int new_pivot_idx = left;
  swap(A[pivot_idx], A[right]);
  for (int i = left; i < right; ++i) {
    if (A[i] > pivot_value) {
      swap(A[i], A[new_pivot_idx++]);
    }
  }
  swap(A[right], A[new_pivot_idx]);
  return new_pivot_idx;
}
// @exclude

static void SimpleTest() {
  vector<int> A = {3,1,2,0,4,6,5};
  assert(6==FindKthLargest(A,1));
  assert(5==FindKthLargest(A,2));
  assert(4==FindKthLargest(A,3));
  assert(3==FindKthLargest(A,4));
  assert(2==FindKthLargest(A,5));
  assert(1==FindKthLargest(A,6));
  assert(0==FindKthLargest(A,7));
  A[2] = 6;
  assert(6==FindKthLargest(A,1));
  assert(6==FindKthLargest(A,2));
  assert(5==FindKthLargest(A,3));
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n, k;
    if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> k_dis(1, n);
      k = k_dis(gen);
    } else if (argc == 3) {
      n = atoi(argv[1]), k = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> n_dis(1, 100000);
      n = n_dis(gen);
      uniform_int_distribution<int> k_dis(1, n - 1);
      k = k_dis(gen);
    }
    vector<int> A;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(0, 9999999);
      A.emplace_back(dis(gen));
    }
    int result = FindKthLargest(A, k);
    nth_element(A.begin(), A.begin() + A.size() - k, A.end());
    assert(result == A[A.size() - k]);
  }
  return 0;
}
