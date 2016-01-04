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
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
struct Subarray {
  int start, end;
};

Subarray find_longest_subarray_less_equal_k(const vector<int>& A, int k) {
  // Build the prefix sum according to A.
  vector<int> prefix_sum;
  partial_sum(A.cbegin(), A.cend(), back_inserter(prefix_sum));

  vector<int> min_prefix_sum(prefix_sum);
  for (int i = min_prefix_sum.size() - 2; i >= 0; --i) {
    min_prefix_sum[i] = min(min_prefix_sum[i], min_prefix_sum[i + 1]);
  }

  Subarray arr_idx = Subarray{
      0, static_cast<int>(distance(
             min_prefix_sum.cbegin(),
             upper_bound(min_prefix_sum.cbegin(), min_prefix_sum.cend(), k) -
                 1))};
  for (int i = 0; i < prefix_sum.size(); ++i) {
    auto idx =
        distance(min_prefix_sum.cbegin(),
                 upper_bound(min_prefix_sum.cbegin(), min_prefix_sum.cend(),
                             k + prefix_sum[i])) -
        1;
    if (idx - i - 1 > arr_idx.end - arr_idx.start) {
      arr_idx = {i + 1, static_cast<int>(idx)};
    }
  }
  return arr_idx;
}
// @exclude

// O(n^2) checking answer
void check_answer(const vector<int>& A, const Subarray& ans, int k) {
  vector<int> sum(A.size() + 1, 0);
  sum[0] = 0;
  for (size_t i = 0; i < A.size(); ++i) {
    sum[i + 1] = sum[i] + A[i];
  }
  if (ans.start != -1 && ans.end != -1) {
    int s = 0;
    for (size_t i = ans.start; i <= ans.end; ++i) {
      s += A[i];
    }
    assert(s <= k);
    for (size_t i = 0; i < sum.size(); ++i) {
      for (size_t j = i + 1; j < sum.size(); ++j) {
        if (sum[j] - sum[i] <= k) {
          assert((j - i) <= (ans.end - ans.start + 1));
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

int main(int argc, char* argv[]) {
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
    */
    Subarray ans = find_longest_subarray_less_equal_k(A, k);
    cout << k << ' ' << ans.start << ' ' << ans.end << endl;
    check_answer(A, ans, k);
  }
  return 0;
}
