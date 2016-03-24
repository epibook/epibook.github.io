// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::generate_n;
using std::max;
using std::random_device;
using std::string;
using std::stoi;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

// @include
int LongestSubarrayWithDistinctEntries(const vector<int>& A) {
  // Records the most recent occurrences of each entry.
  unordered_map<int, size_t> most_recent_occurrence;
  size_t longest_dup_free_subarray_start_idx = 0, result = 0;
  for (size_t i = 0; i < A.size(); ++i) {
    auto dup_idx = most_recent_occurrence.emplace(A[i], i);
    // Defer updating dup_idx until we see a duplicate.
    if (!dup_idx.second) {
      // A[i] appeared before. Did it appear in the longest current subarray?
      if (dup_idx.first->second >= longest_dup_free_subarray_start_idx) {
        result = max(result, i - longest_dup_free_subarray_start_idx);
        longest_dup_free_subarray_start_idx = dup_idx.first->second + 1;
      }
      dup_idx.first->second = i;
    }
  }
  result = max(result, A.size() - longest_dup_free_subarray_start_idx);
  return result;
}
// @exclude

// O(n^2) checking solution.
int CheckAns(const vector<int>& A) {
  size_t len = 0;
  for (size_t i = 0; i < A.size(); ++i) {
    unordered_set<int> table;
    size_t j;
    for (j = i; A.size() - i > len && j < A.size(); ++j) {
      if (!table.emplace(A[j]).second) {
        break;
      }
    }
    len = max(len, j - i);
  }
  return len;
}

void SimpleTest() {
  assert(1 == LongestSubarrayWithDistinctEntries({1, 1, 1}));
  assert(2 == LongestSubarrayWithDistinctEntries({1, 2, 1}));
  assert(3 == LongestSubarrayWithDistinctEntries({1, 2, 1, 3, 1, 2, 1}));
  assert(2 ==
         LongestSubarrayWithDistinctEntries({1, 2, 2, 3, 3, 1, 1, 2, 1}));
}

int main(int argc, char** argv) {
  SimpleTest();
  default_random_engine gen((random_device())());
  uniform_int_distribution<size_t> dis(0, 1000);
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    n = dis(gen);
  }
  cout << "n = " << n << endl;
  for (int times = 0; times < 1000; ++times) {
    vector<int> A;
    generate_n(back_inserter(A), n, [&] { return dis(gen); });
    /*
    for (int a : A) {
      cout << a << " ";
    }
    cout << endl;
    */
    int ans = LongestSubarrayWithDistinctEntries(A);
    int golden_ans = CheckAns(A);
    cout << ans << " " << golden_ans << endl;
    assert(ans == golden_ans);
  }
  return 0;
}
