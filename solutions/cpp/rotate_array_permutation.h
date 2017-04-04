// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_ROTATE_ARRAY_PERMUTATION_H_
#define SOLUTIONS_CPP_ROTATE_ARRAY_PERMUTATION_H_

#include <algorithm>
#include <vector>

#include "./GCD2.h"

using std::swap;
using std::vector;
using GCD2::GCD;

namespace rotate_array1 {

void ApplyCyclicPermutation(int, int, int, vector<int>*);

// @include
void RotateArray(int rotate_amount, vector<int>* A_ptr) {
  rotate_amount %= A_ptr->size();
  if (!rotate_amount) {
    return;
  }

  int num_cycles = GCD(A_ptr->size(), rotate_amount);
  int cycle_length = A_ptr->size() / num_cycles;

  for (int c = 0; c < num_cycles; ++c) {
    ApplyCyclicPermutation(rotate_amount, c, cycle_length, A_ptr);
  }
}

void ApplyCyclicPermutation(int rotate_amount, int offset, int cycle_length,
                            vector<int>* A_ptr) {
  vector<int>& A = *A_ptr;
  int temp = A[offset];
  for (int i = 1; i < cycle_length; ++i) {
    swap(A[(offset + i * rotate_amount) % A.size()], temp);
  }
  A[offset] = temp;
}
// @exclude

}  // rotate_array1
#endif  // SOLUTIONS_CPP_ROTATE_ARRAY_PERMUTATION_H_
