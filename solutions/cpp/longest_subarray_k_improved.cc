// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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

// @include
int FindLongestSubarrayLessEqualK(const vector<int> &A, int k) {
  // Builds the prefix sum according to A.
  vector<int> prefix_sum;
  partial_sum(A.cbegin(), A.cend(), back_inserter(prefix_sum));

  // Early returns if the sum of A is smaller than or equal to k.
  if (prefix_sum.back() <= k) {
    return A.size();
  }

  // Builds min_prefix_sum.
  vector<int> min_prefix_sum(A.size());
  min_prefix_sum.back() = prefix_sum.back();
  for (int i = min_prefix_sum.size() - 2; i >= 0; --i) {
    min_prefix_sum[i] = min(prefix_sum[i], min_prefix_sum[i + 1]);
  }

  int a = 0, b = 0, max_length = 0;
  while (a < A.size() && b < A.size()) {
    int min_curr_sum =
        a > 0 ? min_prefix_sum[b] - prefix_sum[a - 1] : min_prefix_sum[b];
    if (min_curr_sum <= k) {
      int curr_length = b - a + 1;
      if (curr_length > max_length) {
        max_length = curr_length;
      }
      ++b;
    } else {  // min_curr_sum > k.
      ++a;
    }
  }
  return max_length;
}
// @exclude

// O(n^2) checking answer.
template <typename T>
void CheckAnswer(const vector<T> &A, int ans, const T &k) {
  vector<T> sum(A.size() + 1, 0);
  sum[0] = 0;
  for (size_t i = 0; i < A.size(); ++i) {
    sum[i + 1] = sum[i] + A[i];
  }
  if (ans != 0) {
    for (size_t i = 0; i < sum.size(); ++i) {
      for (size_t j = i + 1; j < sum.size(); ++j) {
        if (sum[j] - sum[i] <= k) {
          assert((j - i) <= ans);
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
  int res = FindLongestSubarrayLessEqualK(A, k);
  assert(res == 0);
  k = -100;
  res = FindLongestSubarrayLessEqualK(A, k);
  assert(res == 0);
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
    int ans = FindLongestSubarrayLessEqualK(A, k);
    cout << k << ' ' << ans << endl;
    CheckAnswer(A, ans, k);
  }
  return 0;
}
