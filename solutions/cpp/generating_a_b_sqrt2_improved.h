// Copyright (c) 2016 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_GENERATING_A_B_SQRT2_IMPROVED_H_
#define SOLUTIONS_CPP_GENERATING_A_B_SQRT2_IMPROVED_H_

#include <functional>
#include <set>
#include <vector>

#include "./generating-a-b-sqrt2.h"

using std::min;
using std::set;
using std::vector;
using GeneratingABSqrt2::ABSqrt2;

namespace GeneratingABSqrt2Improved {

// @include
vector<ABSqrt2> GenerateFirstKABSqrt2(int k) {
  // Will store the first k numbers of the form a + b sqrt(2).
  vector<ABSqrt2> result;
  result.emplace_back(0, 0);
  int i = 0, j = 0;
  for (int n = 1; n < k; ++n) {
    ABSqrt2 result_i_plus_1(result[i].a + 1, result[i].b);
    ABSqrt2 result_j_plus_sqrt2(result[j].a, result[j].b + 1);
    result.emplace_back(min(result_i_plus_1, result_j_plus_sqrt2));
    if (result_i_plus_1.val == result.back().val) {
      ++i;
    }
    if (result_j_plus_sqrt2.val == result.back().val) {
      ++j;
    }
  }
  return result;
}
// @exclude

}  // namespace GeneratingABSqrt2Improved

#endif  // SOLUTIONS_CPP_GENERATING_A_B_SQRT2_IMPROVED_H_
