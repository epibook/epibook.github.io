// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_INTERSECT_SORTED_ARRAYS3_H_
#define SOLUTIONS_INTERSECT_SORTED_ARRAYS3_H_

#include <vector>

using std::vector;

namespace IntersectTwoSortedArrays3 {

// @include
vector<int> IntersectTwoSortedArrays(const vector<int>& A,
                                     const vector<int>& B) {
  vector<int> intersection_A_B;
  int i = 0, j = 0;
  while (i < A.size() && j < B.size()) {
    if (A[i] == B[j] && (i == 0 || A[i] != A[i - 1])) {
      intersection_A_B.emplace_back(A[i]);
      ++i, ++j;
    } else if (A[i] < B[j]) {
      ++i;
    } else {  // A[i] > B[j].
      ++j;
    }
  }
  return intersection_A_B;
}
// @exclude

}  // namespace IntersectTwoSortedArrays3

#endif  // SOLUTIONS_INTERSECT_SORTED_ARRAYS3_H_
