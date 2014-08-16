// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_LONGEST_NONDECREASING_SUBSEQUENCE_N2_H_
#define SOLUTIONS_LONGEST_NONDECREASING_SUBSEQUENCE_N2_H_

#include <vector>

using std::vector;

namespace LNS_n2 {

// @include
vector<int> LongestNondecreasingSubsequence(const vector<int>& A) {
  // Empty array.
  if (A.empty() == true) {
    return A;
  }

  vector<int> longest_length(A.size(), 1), previous_index(A.size(), -1);
  int max_length_idx = 0;
  for (int i = 1; i < A.size(); ++i) {
    for (int j = 0; j < i; ++j) {
      if (A[i] >= A[j] && longest_length[j] + 1 > longest_length[i]) {
        longest_length[i] = longest_length[j] + 1;
        previous_index[i] = j;
      }
    }
    // Records the index where longest subsequence ends.
    if (longest_length[i] > longest_length[max_length_idx]) {
      max_length_idx = i;
    }
  }

  // Builds the longest nondecreasing subsequence.
  int max_length = longest_length[max_length_idx];
  vector<int> ret(max_length);
  while (max_length-- > 0) {
    ret[max_length] = A[max_length_idx];
    max_length_idx = previous_index[max_length_idx];
  }
  return ret;
}
// @exclude

}  // LNS_n2

#endif  // SOLUTIONS_LONGEST_NONDECREASING_SUBSEQUENCE_N2_H_
