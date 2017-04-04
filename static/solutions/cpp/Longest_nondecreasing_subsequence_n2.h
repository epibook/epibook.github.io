// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_LONGEST_NONDECREASING_SUBSEQUENCE_N2_H_
#define SOLUTIONS_LONGEST_NONDECREASING_SUBSEQUENCE_N2_H_

#include <algorithm>
#include <vector>

using std::max;
using std::max_element;
using std::vector;

namespace LNS_n2 {

// @include
int LongestNondecreasingSubsequenceLength(const vector<int>& A) {
  // max_length[i] holds the length of the longest nondecreasing subsequence
  // of A[0 : i].
  vector<int> max_length(A.size(), 1);
  for (int i = 1; i < A.size(); ++i) {
    for (int j = 0; j < i; ++j) {
      if (A[i] >= A[j]) {
        max_length[i] = max(max_length[i], max_length[j] + 1);
      }
    }
  }
  return *max_element(max_length.begin(), max_length.end());
}
// @exclude

}  // LNS_n2

#endif  // SOLUTIONS_LONGEST_NONDECREASING_SUBSEQUENCE_N2_H_
