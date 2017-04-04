// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <functional>
#include <iostream>
#include <limits>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::multiplies;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int FindBiggestNMinusOneProduct(const vector<int>& A) {
  // Builds suffix products.
  vector<int> suffix_products(A.size());
  partial_sum(A.crbegin(), A.crend(), suffix_products.rbegin(),
              multiplies<int>());

  // Finds the biggest product of (n - 1) numbers.
  int prefix_product = 1, max_product = numeric_limits<int>::min();
  for (int i = 0; i < A.size(); ++i) {
    int suffix_product = i + 1 < A.size() ? suffix_products[i + 1] : 1;
    max_product = max(max_product, prefix_product * suffix_product);
    prefix_product *= A[i];
  }
  return max_product;
}
// @exclude

// n^2 checking.
int CheckAns(const vector<int>& A) {
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
  return max_product;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
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
    assert(res == CheckAns(A));
    cout << res << endl;
  }
  return 0;
}
