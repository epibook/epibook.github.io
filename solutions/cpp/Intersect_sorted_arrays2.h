// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_INTERSECT_SORTED_ARRAYS2_H_
#define SOLUTIONS_INTERSECT_SORTED_ARRAYS2_H_

#include <algorithm>
#include <vector>

using std::vector;

namespace IntersectTwoSortedArrays2 {

// @include
vector<int> IntersectTwoSortedArrays(const vector<int>& A,
                                     const vector<int>& B) {
  vector<int> intersect;
  for (int i = 0; i < A.size(); ++i) {
    if ((i == 0 || A[i] != A[i - 1]) &&
        binary_search(B.cbegin(), B.cend(), A[i])) {
      intersect.emplace_back(A[i]);
    }
  }
  return intersect;
}
// @exclude

}  // namespace IntersectTwoSortedArrays2

#endif  // SOLUTIONS_INTERSECT_SORTED_ARRAYS2_H_
