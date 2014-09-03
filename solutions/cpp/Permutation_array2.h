// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_PERMUTATION_ARRAY2_H_
#define SOLUTIONS_PERMUTATION_ARRAY2_H_

#include <vector>

namespace ApplyPermutation2 {

using std::vector;

// @include
void ApplyPermutation(vector<int>* perm, vector<int>* A) {
  for (int i = 0; i < A->size(); ++i) {
    // Traverses the cycle to see if i is the minimum element.
    bool is_min = true;
    int j = (*perm)[i];
    while (j != i) {
      if (j < i) {
        is_min = false;
        break;
      }
      j = (*perm)[j];
    }

    if (is_min) {
      int a = i;
      int temp = (*A)[i];
      do {
        int next_a = (*perm)[a];
        int next_temp = (*A)[next_a];
        (*A)[next_a] = temp;
        a = next_a, temp = next_temp;
      } while (a != i);
    }
  }
}
// @exclude

}  // namespace ApplyPermutation2

#endif  // SOLUTIONS_PERMUTATION_ARRAY2_H_
