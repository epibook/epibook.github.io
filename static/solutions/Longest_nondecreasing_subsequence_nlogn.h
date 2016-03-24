// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_LONGEST_NONDECREASING_SUBSEQUENCE_NLOGN_H_
#define SOLUTIONS_LONGEST_NONDECREASING_SUBSEQUENCE_NLOGN_H_

#include <algorithm>
#include <vector>

using std::vector;

namespace LNS_nlogn {

// @include
int LongestNondecreasingSubsequenceLength(const vector<int>& A) {
  vector<int> tail_values;
  for (int a : A) {
    auto it = upper_bound(tail_values.begin(), tail_values.end(), a);
    if (it == tail_values.end()) {
      tail_values.emplace_back(a);
    } else {
      *it = a;
    }
  }
  return tail_values.size();
}
// @exclude

}  // LNS_nlogn

#endif  // SOLUTIONS_LONGEST_NONDECREASING_SUBSEQUENCE_NLOGN_H_
