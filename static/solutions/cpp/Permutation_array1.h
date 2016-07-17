// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_PERMUTATION_ARRAY1_H_
#define SOLUTIONS_PERMUTATION_ARRAY1_H_

#include <algorithm>
#include <vector>

using std::swap;
using std::vector;

namespace ApplyPermutation1 {

// @include
void ApplyPermutation(vector<int>* perm_ptr, vector<int>* A_ptr) {
  vector<int> &perm = *perm_ptr, &A = *A_ptr;
  for (int i = 0; i < A.size(); ++i) {
    // Check if the element at index i has not been moved by checking if
    // perm[i] is nonnegative.
    int next = i;
    while (perm[next] >= 0) {
      swap(A[i], A[perm[next]]);
      int temp = perm[next];
      // Subtracts perm.size() from an entry in perm to make it negative,
      // which indicates the corresponding move has been performed.
      perm[next] -= perm.size();
      next = temp;
    }
  }

  // Restore perm.
  for_each(begin(perm), end(perm), [&perm](int& x) { x += perm.size(); });
}
// @exclude

}  // ApplyPermutation1

#endif  // SOLUTIONS_PERMUTATION_ARRAY1_H_
