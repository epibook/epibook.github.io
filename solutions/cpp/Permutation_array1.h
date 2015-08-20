// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_PERMUTATION_ARRAY1_H_
#define SOLUTIONS_PERMUTATION_ARRAY1_H_

#include <algorithm>
#include <vector>

using std::vector;

namespace ApplyPermutation1 {

void CyclicPermutation(int, vector<int>*, vector<int>*);

// @include
void ApplyPermutation(vector<int>* perm_ptr, vector<int>* A_ptr) {
  vector<int> &perm = *perm_ptr, &A = *A_ptr;
  for (int i = 0; i < A.size(); ++i) {
    // Check if the element at index i has not been moved by seeing if perm[i]
    // is nonnegative.
    if (perm[i] >= 0) {
      CyclicPermutation(i, &perm, &A);
    }
  }

  // Restore perm.
  for_each(perm.begin(), perm.end(), [&](int& x) { x += perm.size(); });
}

void CyclicPermutation(int start, vector<int>* perm_ptr, vector<int>* A_ptr) {
  vector<int> &perm = *perm_ptr, &A = *A_ptr;
  int i = start;
  int temp = A[start];
  do {
    int next_i = perm[i];
    int next_temp = A[next_i];
    A[next_i] = temp;
    // Subtracts perm.size() from an entry in perm to make it negative, which
    // indicates the corresponding move has been performed.
    perm[i] -= perm.size();
    i = next_i, temp = next_temp;
  } while (i != start);
}
// @exclude

}  // ApplyPermutation1

#endif  // SOLUTIONS_PERMUTATION_ARRAY1_H_
