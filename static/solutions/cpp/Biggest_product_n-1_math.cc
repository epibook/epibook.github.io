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
  int least_nonnegative_idx = -1;
  int number_of_negatives = 0, greatest_negative_idx = -1,
      least_negative_idx = -1;

  // Identify the least negative, greatest negative, and least nonnegative
  // entries.
  for (int i = 0; i < A.size(); ++i) {
    if (A[i] < 0) {
      ++number_of_negatives;
      if (least_negative_idx == -1 || A[least_negative_idx] < A[i]) {
        least_negative_idx = i;
      }
      if (greatest_negative_idx == -1 || A[i] < A[greatest_negative_idx]) {
        greatest_negative_idx = i;
      }
    } else {  // A[i] >= 0.
      if (least_nonnegative_idx == -1 || A[i] < A[least_nonnegative_idx]) {
        least_nonnegative_idx = i;
      }
    }
  }

  int product = 1;
  int idx_to_skip =
      number_of_negatives % 2
          ? least_negative_idx
          // Check if there are any nonnegative entry.
          : (least_nonnegative_idx != -1 ? least_nonnegative_idx
                                         : greatest_negative_idx);
  for (int i = 0; i < A.size(); ++i) {
    if (i != idx_to_skip) {
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
