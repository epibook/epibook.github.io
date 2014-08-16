// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <numeric>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::min;
using std::pair;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// EPI 1.4 solution
pair<int, int> find_longest_subarray_less_equal_k(const vector<int> &A,
                                                  int k) {
  // Builds the prefix sum according to A.
  vector<int> prefix_sum;
  partial_sum(A.cbegin(), A.cend(), back_inserter(prefix_sum));

  // Early return if the sum of A is smaller than or equal to k.
  if (prefix_sum.back() <= k) {
    return {0, A.size() - 1};
  }

  // Builds min_prefix_sum.
  vector<int> min_prefix_sum(A.size());
  min_prefix_sum.back() = prefix_sum.back();
  for (int i = min_prefix_sum.size() - 2; i >= 0; --i) {
    min_prefix_sum[i] = min(prefix_sum[i], min_prefix_sum[i + 1]);
  }

  int a = 0, b = 0, max_len = 0;
  pair<int, int> res_idx(-1, -1);
  while (a < A.size() && b < A.size()) {
    int min_curr_sum =     // dhu revision
      //a > 0 ? min_prefix_sum[b] - prefix_sum[a - 1] : min_prefix_sum[b];
      a > 0 ? min_prefix_sum[b] - prefix_sum.at(a-1) : min_prefix_sum[b];

    cout << "a = " << a << ", b = " << b << ": min_curr_sum = " << min_curr_sum << ", k = " << k << endl; // debug: print out a and b

    if (min_curr_sum <= k) {
      int curr_len = b - a + 1;
      if (curr_len > max_len) {
        max_len = curr_len;
        res_idx = {a, b};
      }
      ++b;
    } 
    else {  // min_curr_sum > k.
      ++a;
    }
  }

  return res_idx;
}

// Test
int main(int argc, char *argv[]) {
  vector<int> v(2, 1);
  int k = -100;
  pair<int, int> ret = find_longest_subarray_less_equal_k(v, k);
  cout << "Solution: " << ret.first << ", " << ret.second << endl;

  return 0;
}
