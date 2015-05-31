// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_PERMUTATION_ARRAY1_H_
#define SOLUTIONS_PERMUTATION_ARRAY1_H_

#include <algorithm>
#include <vector>

using std::vector;

namespace ApplyPermutation1 {

// @include
void ApplyPermutation(vector<int>* perm, vector<int>* A) {
  for (int i = 0; i < A->size(); ++i) {
    // Check if the element at index i has already been moved
    // by seeing if (*perm)[i] is negative.
    if ((*perm)[i] >= 0) {
      int a = i;
      int temp = (*A)[i];
      do {
        int next_a = (*perm)[a];
        int next_temp = (*A)[next_a];
        (*A)[next_a] = temp;
        // Mark a as visited by using the sign bit. Specifically,
        // we subtract perm->size() from each entry in perm.
        (*perm)[a] -= perm->size();
        a = next_a, temp = next_temp;
      } while (a != i);
    }
  }

  // Restore perm back.
  for_each(perm->begin(), perm->end(), [&](int& x) { x += perm->size(); });
}
// @exclude

}  // ApplyPermutation1

#endif  // SOLUTIONS_PERMUTATION_ARRAY1_H_
