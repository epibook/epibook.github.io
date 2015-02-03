// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_INTERSECT_SORTED_ARRAYS1_H_
#define SOLUTIONS_INTERSECT_SORTED_ARRAYS1_H_

#include <vector>

using std::vector;

namespace IntersectTwoSortedArrays1 {

// @include
vector<int> IntersectTwoSortedArrays(const vector<int>& A,
                                     const vector<int>& B) {
  vector<int> insersection_A_B;
  for (int i = 0; i < A.size(); ++i) {
    if (i == 0 || A[i] != A[i - 1]) {
      for (int b : B) {
        if (A[i] == b) {
          insersection_A_B.emplace_back(A[i]);
          break;
        }
      }
    }
  }
  return insersection_A_B;
}
// @exclude

}  // namespace IntersectTwoSortedArrays1

#endif  // SOLUTIONS_INTERSECT_SORTED_ARRAYS1_H_
