// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_SMALLEST_SUBARRAY_COVERING_SET_H_
#define SOLUTIONS_SMALLEST_SUBARRAY_COVERING_SET_H_

#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

using std::pair;
using std::string;
using std::unordered_map;
using std::unordered_set;
using std::vector;

// @include
pair<int, int> FindSmallestSubarrayCoveringSubset(const vector<string> &A,
                                                  const vector<string> &Q) {
  unordered_set<string> dict(Q.cbegin(), Q.cend());
  unordered_map<string, int> count_Q;
  int l = 0, r = 0;
  pair<int, int> res(-1, -1);
  while (r < static_cast<int>(A.size())) {
    // Keeps moving r until it reaches end or count_Q has |Q| items.
    while (r < static_cast<int>(A.size()) && count_Q.size() < Q.size()) {
      if (dict.find(A[r]) != dict.end()) {
        ++count_Q[A[r]];
      }
      ++r;
    }

    if (count_Q.size() == Q.size() &&  // Found |Q| keywords.
        ((res.first == -1 && res.second == -1) ||
         r - 1 - l < res.second - res.first)) {
      res = {l, r - 1};
    }

    // Keep smoving l until it reaches end or count_Q has less |Q| items.
    while (l < r && count_Q.size() == Q.size()) {
      if (dict.find(A[l]) != dict.end()) {
        auto it = count_Q.find(A[l]);
        if (--(it->second) == 0) {
          count_Q.erase(it);
          if ((res.first == -1 && res.second == -1) ||
              r - 1 - l < res.second - res.first) {
            res = {l, r - 1};
          }
        }
      }
      ++l;
    }
  }
  return res;
}
// @exclude
#endif  // SOLUTIONS_SMALLEST_SUBARRAY_COVERING_SET_H_
