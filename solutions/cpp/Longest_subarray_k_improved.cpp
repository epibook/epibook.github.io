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


pair<int, int> FindLongestSubarrayLessEqualKAnother(const vector<int> &A,
                                                          int k) {
  // Builds the prefix sum according to A.
  vector<int> prefix_sum;
  partial_sum(A.cbegin(), A.cend(), back_inserter(prefix_sum));

  if (prefix_sum.back() <= k) {
    return {0, A.size() - 1};
  }

  // Finds all possible ending points by scanning from right to left.
  pair<int, int> res_idx(0, -1);
  vector<int> potential_ends;
  potential_ends.emplace_back(A.size() - 1);
  for (int i = A.size() - 2; i >= 0; --i) {
    if (prefix_sum[i] < prefix_sum[potential_ends.back()]) {
      potential_ends.emplace_back(i);
    }

    // Gets the longest subarray whose sum <= k and starts from A[0].
    if (res_idx.second == -1 && prefix_sum[i] <= k) {
      res_idx = {0, i};
    }
  }

  // Scans prefix_sum from left to right. Tries each element as the starting
  // point, finds the rightmost ending point where the subarray sum <= k.
  int start_idx = 0;
  while (start_idx < A.size() && !potential_ends.empty()) {
    int end_idx = potential_ends.back();
    if (start_idx >= end_idx) {
      potential_ends.pop_back();
    } else if (prefix_sum[end_idx] - prefix_sum[start_idx] <= k) {
      if (end_idx - start_idx > res_idx.second - res_idx.first + 1) {
        res_idx = {start_idx + 1, end_idx};
      }
      potential_ends.pop_back();
    } else {
      ++start_idx;
    }
  }
  return res_idx;
}

// @include
pair<int, int> FindLongestSubarrayLessEqualK(const vector<int> &A, int k) {
  // Builds the prefix sum according to A.
  vector<int> prefix_sum;
  partial_sum(A.cbegin(), A.cend(), back_inserter(prefix_sum));

  // Early returns if the sum of A is smaller than or equal to k.
  if (prefix_sum.back() <= k) {
    return {0, A.size() - 1};
  }

  // Builds min_prefix_sum.
  vector<int> min_prefix_sum(A.size());
  min_prefix_sum.back() = prefix_sum.back();
  for (int i = min_prefix_sum.size() - 2; i >= 0; --i) {
    min_prefix_sum[i] = min(prefix_sum[i], min_prefix_sum[i + 1]);
  }

  int a = 0, b = 0, max_length = 0;
  pair<int, int> res_idx(-1, -1);
  while (a < A.size() && b < A.size()) {
    int min_curr_sum =
        a > 0 ? min_prefix_sum[b] - prefix_sum[a - 1] : min_prefix_sum[b];
    if (min_curr_sum <= k) {
      int curr_length = b - a + 1;
      if (curr_length > max_length) {
        max_length = curr_length;
        res_idx = {a, b};
      }
      ++b;
    } else {  // min_curr_sum > k.
      ++a;
    }
  }
  return res_idx;
}
// @exclude

// O(n^2) checking answer.
template <typename T>
void CheckAnswer(const vector<T> &A, const pair<int, int> &ans, const T &k) {
  vector<T> sum(A.size() + 1, 0);
  sum[0] = 0;
  for (size_t i = 0; i < A.size(); ++i) {
    sum[i + 1] = sum[i] + A[i];
  }
  if (ans.first != -1 && ans.second != -1) {
    T s = 0;
    for (size_t i = ans.first; i <= ans.second; ++i) {
      s += A[i];
    }
    assert(s <= k);
    for (size_t i = 0; i < sum.size(); ++i) {
      for (size_t j = i + 1; j < sum.size(); ++j) {
        if (sum[j] - sum[i] <= k) {
          assert((j - i) <= (ans.second - ans.first + 1));
        }
      }
    }
  } else {
    for (size_t i = 0; i < sum.size(); ++i) {
      for (size_t j = i + 1; j < sum.size(); ++j) {
        assert(sum[j] - sum[i] > k);
      }
    }
  }
}

void SmallTest() {
  vector<int> A = {1, 1};
  int k = 0;
  auto res = FindLongestSubarrayLessEqualK(A, k);
  cout << "res = " << res.first << ", " << res.second << endl;
  assert(res.first == -1 && res.second == -1);
  k = -100;
  res = FindLongestSubarrayLessEqualK(A, k);
  cout << "res = " << res.first << ", " << res.second << endl;
  assert(res.first == -1 && res.second == -1);
}

int main(int argc, char *argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n, k;
    if (argc == 3) {
      n = atoi(argv[1]), k = atoi(argv[2]);
    } else if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> dis(0, 9999);
      k = dis(gen);
    } else {
      uniform_int_distribution<int> n_dis(1, 10000);
      n = n_dis(gen);
      uniform_int_distribution<int> k_dis(0, 9999);
      k = k_dis(gen);
    }
    vector<int> A;
    for (size_t i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(-1000, 1000);
      A.emplace_back(dis(gen));
    }
    /*
    copy(A.begin(), A.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    //*/
    auto ans = FindLongestSubarrayLessEqualK(A, k);
    auto another_ans = FindLongestSubarrayLessEqualKAnother(A, k);
    assert(ans.second - ans.first == another_ans.second - another_ans.first);
    cout << k << ' ' << ans.first << ' ' << ans.second << endl;
    CheckAnswer(A, ans, k);
  }
  return 0;
}
