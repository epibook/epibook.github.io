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

int Partition(int left, int right, int pivot, vector<int>* A);

// @include
int FindKthLargest(vector<int> A, int k) {
  int left = 0, right = A.size() - 1;

  while (left <= right) {
    default_random_engine gen((random_device())());
    // Generates a random int in [left, right].
    uniform_int_distribution<int> dis(left, right);
    int p = Partition(left, right, dis(gen), &A);
    if (p == k - 1) {
      return A[p];
    } else if (p > k - 1) {
      right = p - 1;
    } else {  // p < k - 1.
      left = p + 1;
    }
  }
  // @exclude
  throw length_error("no k-th node in array A");
  // @include
}

// Partitions A according pivot, returns its index after partition.
int Partition(int left, int right, int pivot, vector<int>* A) {
  auto& A_ref = *A;  // Reference to *A.
  int pivot_value = A_ref[pivot];
  int larger_index = left;

  swap(A_ref[pivot], A_ref[right]);
  for (int i = left; i < right; ++i) {
    if (A_ref[i] > pivot_value) {
      swap(A_ref[i], A_ref[larger_index++]);
    }
  }
  swap(A_ref[right], A_ref[larger_index]);
  return larger_index;
}
// @exclude

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
