// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <functional>
#include <iostream>
#include <random>
#include <stdexcept>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::greater;
using std::length_error;
using std::less;
using std::max;
using std::min;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

template <typename Compare>
int FindKth(int, Compare, vector<int>*);
template <typename Compare>
int PartitionAroundPivot(int, int, int, Compare, vector<int>*);

// @include
// The numbering starts from one, i.e., if A = [3,1,-1,2] then
// FindKthLargest(1, A) returns 3, FindKthLargest(2, A) returns 2,
// FindKthLargest(3, A) returns 1, and FindKthLargest(4, A) returns -1.
int FindKthLargest(int k, vector<int>* A_ptr) {
  return FindKth(k, greater<int>(), A_ptr);
}

// @exclude

// The numbering starts from one, i.e., if A = [3,1,-1,2] then
// FindKthSmallest(1, A) returns -1, FindKthSmallest(2, A) returns 1,
// FindKthSmallest(3, A) returns 2, and FindKthSmallest(4, A) returns 3.
int FindKthSmallest(int k, vector<int>* A_ptr) {
  return FindKth(k, less<int>(), A_ptr);
}
// @include
template <typename Compare>
int FindKth(int k, Compare comp, vector<int>* A_ptr) {
  vector<int>& A = *A_ptr;
  int left = 0, right = A.size() - 1;
  default_random_engine gen((random_device())());
  while (left <= right) {
    // Generates a random integer in [left, right].
    int pivot_idx = uniform_int_distribution<int>{left, right}(gen);
    int new_pivot_idx =
        PartitionAroundPivot(left, right, pivot_idx, comp, &A);
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
//
// Note: "less than" is defined by the Compare object.
//
// Returns the new index of the pivot element after partition.
template <typename Compare>
int PartitionAroundPivot(int left, int right, int pivot_idx, Compare comp,
                         vector<int>* A_ptr) {
  vector<int>& A = *A_ptr;
  int pivot_value = A[pivot_idx];
  int new_pivot_idx = left;
  swap(A[pivot_idx], A[right]);
  for (int i = left; i < right; ++i) {
    if (comp(A[i], pivot_value)) {
      swap(A[i], A[new_pivot_idx++]);
    }
  }
  swap(A[right], A[new_pivot_idx]);
  return new_pivot_idx;
}
// @exclude

static void SimpleTestKthSmallest() {
  vector<int> A = {3, 1, 2, 0, 4, 6, 5};
  assert(0 == FindKthSmallest(1, &A));
  assert(1 == FindKthSmallest(2, &A));
  assert(2 == FindKthSmallest(3, &A));
  assert(3 == FindKthSmallest(4, &A));
  assert(4 == FindKthSmallest(5, &A));
  assert(5 == FindKthSmallest(6, &A));
  assert(6 == FindKthSmallest(7, &A));
  A[2] = 6;
  assert(6 == FindKthSmallest(6, &A));
  assert(6 == FindKthSmallest(7, &A));
  assert(5 == FindKthSmallest(5, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  // -7 0 0 3 4 4 6 10 12
  assert(-7 == FindKthSmallest(1, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(0 == FindKthSmallest(2, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(0 == FindKthSmallest(3, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(3 == FindKthSmallest(4, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(4 == FindKthSmallest(5, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(4 == FindKthSmallest(6, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(6 == FindKthSmallest(7, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(10 == FindKthSmallest(8, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(12 == FindKthSmallest(9, &A));

  assert(4 == FindKthSmallest(6, &A));
  for (int i = 0; i < A.size(); i++) {
    if (i < 4) {
      assert(A[i] < 4);
    } else if (i > 5) {
      assert(A[i] > 4);
    }
  }
}

static void SimpleTestKthLargest() {
  vector<int> A = {3, 1, 2, 0, 4, 6, 5};
  assert(6 == FindKthLargest(1, &A));
  assert(5 == FindKthLargest(2, &A));
  assert(4 == FindKthLargest(3, &A));
  assert(3 == FindKthLargest(4, &A));
  assert(2 == FindKthLargest(5, &A));
  assert(1 == FindKthLargest(6, &A));
  assert(0 == FindKthLargest(7, &A));
  A[2] = 6;
  assert(6 == FindKthLargest(1, &A));
  assert(6 == FindKthLargest(2, &A));
  assert(5 == FindKthLargest(3, &A));

  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  // 12 10 6 4 4 3 0 0 -7
  assert(12 == FindKthLargest(1, &A));
  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(10 == FindKthLargest(2, &A));
  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(6 == FindKthLargest(3, &A));
  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(4 == FindKthLargest(4, &A));
  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(4 == FindKthLargest(5, &A));
  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(3 == FindKthLargest(6, &A));
  A = {0, -7, 3, 4, 4, 12, 6, 10, 0};
  assert(4 == FindKthLargest(5, &A));
  for (int i = 0; i < A.size(); ++i) {
    if (i < 3) {
      assert(A[i] > 4);
    } else if (i > 4) {
      assert(A[i] < 4);
    }
  }
}

static void SimpleTest() {
  vector<int> C = {9, 5};
  assert(9 == FindKthLargest(1, &C));
  assert(5 == FindKthSmallest(1, &C));

  vector<int> B = {3, 2, 3, 5, 7, 3, 1};
  int c = FindKthSmallest(4, &B);

  vector<int> A = {123};
  assert(123 == FindKthLargest(1, &A));
}

static void CheckOrderStatistic(int K, bool increasing_order,
                                vector<int>* A_ptr) {
  vector<int> B(*A_ptr);
  if (increasing_order) {
    FindKthSmallest(K, A_ptr);
  } else {
    FindKthLargest(K, A_ptr);
  }

  vector<int> B_sort(B);
  sort(B_sort.begin(), B_sort.end());
  if (!increasing_order) {
    reverse(B_sort.begin(), B_sort.end());
  }

  vector<int> A_sort(*A_ptr);
  sort(A_sort.begin(), A_sort.end());
  if (!increasing_order) {
    reverse(A_sort.begin(), A_sort.end());
  }

  assert(equal(A_sort.begin(), A_sort.end(), B_sort.begin(), B_sort.end()));
}

static void TestAllOrders(const vector<int>& order, vector<int>* A_ptr) {
  for (int K : order) {
    CheckOrderStatistic(K, true, A_ptr);
    CheckOrderStatistic(K, false, A_ptr);
  }
}

static void RandomTestFixedN(int N) {
  vector<int> order;
  for (int i = 0; i < 5; ++i) {
    order.emplace_back(min(N, i + 1));
  }
  order.emplace_back(min(N, 7));
  order.emplace_back(min(N, 9));
  order.emplace_back(min(N, 12));
  order.emplace_back(min(N, max(N / 2 - 1, 1)));
  order.emplace_back(min(N, max(N / 2, 1)));
  order.emplace_back(min(N, N / 2 + 1));
  order.emplace_back(max(1, N - 1));
  order.emplace_back(N);

  vector<int> A;
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis(0, 9999999);
  for (int i = 0; i < N; ++i) {
    A.emplace_back(dis(gen));
  }
  TestAllOrders(order, &A);

  A.clear();
  uniform_int_distribution<int> n_dis(0, N - 1);
  for (int i = 0; i < N; ++i) {
    A.emplace_back(n_dis(gen));
  }
  TestAllOrders(order, &A);

  A.clear();
  uniform_int_distribution<int> n2_dis(0, 2 * N - 1);
  for (int i = 0; i < N; ++i) {
    A.emplace_back(n2_dis(gen));
  }
  TestAllOrders(order, &A);

  A.clear();
  uniform_int_distribution<int> n_half_dis(0, max(N / 2, 1) - 1);
  for (int i = 0; i < N; ++i) {
    A.emplace_back(n_half_dis(gen));
  }
  TestAllOrders(order, &A);
}

static void ComplexRandomTest() {
  vector<int> N = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 50, 100};
  for (int i = 0; i < N.size(); ++i) {
    for (int j = 0; j < 100; ++j) {
      RandomTestFixedN(N[i]);
    }
  }
}

int main(int argc, char* argv[]) {
  SimpleTest();
  SimpleTestKthLargest();
  SimpleTestKthSmallest();
  ComplexRandomTest();
  cout << "Finished ComplexRandomTest()" << endl;
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
    int result = FindKthLargest(k, &A);
    nth_element(A.begin(), A.begin() + A.size() - k, A.end());
    assert(result == A[A.size() - k]);
  }
  return 0;
}
