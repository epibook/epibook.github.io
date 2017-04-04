// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
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

int CountSubarrayInversions(int, int, vector<int>*);
int MergeSortAndCountInversionsAcrossSubarrays(int, int, int, vector<int>*);

// @include
int CountInversions(vector<int> A) {
  return CountSubarrayInversions(0, A.size(), &A);
}

// Return the number of inversions in (*A_ptr)[start : end - 1].
int CountSubarrayInversions(int start, int end, vector<int>* A_ptr) {
  if (end - start <= 1) {
    return 0;
  }

  int mid = start + ((end - start) / 2);
  return CountSubarrayInversions(start, mid, A_ptr) +
         CountSubarrayInversions(mid, end, A_ptr) +
         MergeSortAndCountInversionsAcrossSubarrays(start, mid, end, A_ptr);
}

// Merge two sorted subarrays (*A_ptr)[start : mid - 1] and (*A_ptr)[mid : end
// - 1] into (*A_ptr)[start : end - 1] and return the number of inversions
// across (*A_ptr)[start : mid - 1] and (*A_ptr)[mid : end - 1].
int MergeSortAndCountInversionsAcrossSubarrays(int start, int mid, int end,
                                               vector<int>* A_ptr) {
  vector<int> sorted_A;
  int left_start = start, right_start = mid, inversion_count = 0;

  vector<int>& A = *A_ptr;
  while (left_start < mid && right_start < end) {
    if (A[left_start] <= A[right_start]) {
      sorted_A.emplace_back(A[left_start++]);
    } else {
      // A[left_start : mid - 1] are the inversions of A[right_start].
      inversion_count += mid - left_start;
      sorted_A.emplace_back(A[right_start++]);
    }
  }
  copy(A.begin() + left_start, A.begin() + mid, back_inserter(sorted_A));
  copy(A.begin() + right_start, A.begin() + end, back_inserter(sorted_A));

  // Updates A with sorted_A.
  copy(sorted_A.begin(), sorted_A.end(), A.begin() + start);
  return inversion_count;
}
// @exclude

// O(n^2) check of inversions.
template <typename T>
int N2Check(const vector<T>& A) {
  int count = 0;
  for (size_t i = 0; i < A.size(); ++i) {
    for (size_t j = i + 1; j < A.size(); ++j) {
      if (A[i] > A[j]) {
        ++count;
      }
    }
  }
  cout << count << endl;
  return count;
}

int main(int argc, char* argv[]) {
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
    for (size_t i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(-1000000, 1000000);
      A.emplace_back(dis(gen));
    }
    assert(N2Check(A) == CountInversions(A));
  }
  return 0;
}
