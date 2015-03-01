// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int FindBiggestNMinusOneProduct(const vector<int>& A) {
  int zero_count = 0, zero_idx = -1;
  int positive_count = 0, smallest_positive_idx = -1;
  int negative_count = 0, smallest_negative_idx = -1,
      biggest_negative_idx = -1;

  for (int i = 0; i < A.size(); ++i) {
    if (A[i] < 0) {
      ++negative_count;
      if (smallest_negative_idx == -1 || A[i] < A[smallest_negative_idx]) {
        smallest_negative_idx = i;
      }
      if (biggest_negative_idx == -1 || A[biggest_negative_idx] < A[i]) {
        biggest_negative_idx = i;
      }
    } else if (A[i] == 0) {
      zero_idx = i, ++zero_count;
    } else {  // A[i] > 0.
      ++positive_count;
      if (smallest_positive_idx == -1 || A[i] < A[smallest_positive_idx]) {
        smallest_positive_idx = i;
      }
    }
  }

  // Try to find a number whose elimination could maximize the product of
  // the remaining (n - 1) numbers.
  int x;  // Stores the idx of eliminated one.
  if (zero_count >= 2) {
    return 0;
  } else if (zero_count == 1) {
    if (negative_count & 1) {  // Odd number of negatives.
      return 0;
    } else {
      x = zero_idx;
    }
  } else {  // No zero in A.
    if (negative_count & 1) {  // Odd number of negatives.
      x = biggest_negative_idx;
    } else {  // Even number of negatives.
      if (positive_count > 0) {
        x = smallest_positive_idx;
      } else {
        x = smallest_negative_idx;
      }
    }
  }

  int product = 1;
  for (int i = 0; i < A.size(); ++i) {
    if (i != x) {
      product *= A[i];
    }
  }
  return product;
}
// @exclude

// n^2 checking
int check_ans(const vector<int>& A) {
  int max_product = numeric_limits<int>::min();
  for (int i = 0; i < A.size(); ++i) {
    int product = 1;
    for (int j = 0; j < i; ++j) {
      product *= A[j];
    }
    for (int j = i + 1; j < A.size(); ++j) {
      product *= A[j];
    }
    if (product > max_product) {
      max_product = product;
    }
  }
  cout << max_product << endl;
  return max_product;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 100000; ++times) {
    int n;
    vector<int> A;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(2, 11);
      n = dis(gen);
    }
    for (size_t i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(-9, 9);
      A.emplace_back(dis(gen));
      cout << A[i] << ' ';
    }
    cout << endl;
    int res = FindBiggestNMinusOneProduct(A);
    cout << res << endl;
    assert(res == check_ans(A));
  }
  return 0;
}
