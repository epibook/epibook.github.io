// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_INTERSECT_SORTED_ARRAYS1_H_
#define SOLUTIONS_CPP_INTERSECT_SORTED_ARRAYS1_H_

#include <algorithm>
#include <vector>

using std::vector;

namespace IntersectTwoSortedArrays1 {

// @include
vector<int> IntersectTwoSortedArrays(const vector<int>& A,
                                     const vector<int>& B) {
  vector<int> insersection_A_B;
  for (int i = 0; i < A.size(); ++i) {
    if ((!i || A[i] != A[i - 1]) && find(begin(B), end(B), A[i]) != end(B)) {
      insersection_A_B.emplace_back(A[i]);
    }
  }
  return insersection_A_B;
}
// @exclude

}  // namespace IntersectTwoSortedArrays1

#endif  // SOLUTIONS_CPP_INTERSECT_SORTED_ARRAYS1_H_
