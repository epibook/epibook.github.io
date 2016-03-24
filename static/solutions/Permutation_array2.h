// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_PERMUTATION_ARRAY2_H_
#define SOLUTIONS_PERMUTATION_ARRAY2_H_

#include <vector>

using std::vector;

namespace ApplyPermutation2 {

void CyclicPermutation(int, const vector<int>&, vector<int>*);

// @include
void ApplyPermutation(const vector<int>& perm, vector<int>* A_ptr) {
  vector<int>& A = *A_ptr;
  for (int i = 0; i < A.size(); ++i) {
    // Traverses the cycle to see if i is the minimum element.
    bool is_min = true;
    int j = perm[i];
    while (j != i) {
      if (j < i) {
        is_min = false;
        break;
      }
      j = perm[j];
    }

    if (is_min) {
      CyclicPermutation(i, perm, &A);
    }
  }
}

void CyclicPermutation(int start, const vector<int>& perm,
                       vector<int>* A_ptr) {
  vector<int>& A = *A_ptr;
  int i = start;
  int temp = A[start];
  do {
    int next_i = perm[i];
    int next_temp = A[next_i];
    A[next_i] = temp;
    i = next_i, temp = next_temp;
  } while (i != start);
}
// @exclude

}  // namespace ApplyPermutation2

#endif  // SOLUTIONS_PERMUTATION_ARRAY2_H_
