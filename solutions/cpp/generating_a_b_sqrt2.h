// Copyright (c) 2016 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_GENERATING_A_B_SQRT2_H_
#define SOLUTIONS_CPP_GENERATING_A_B_SQRT2_H_

#include <set>
#include <vector>

using std::set;
using std::vector;

namespace GeneratingABSqrt2 {

// These numbers have very interesting property, and people called it ugly
// numbers. It is also called Quadratic integer rings.
// @include
struct ABSqrt2 {
  ABSqrt2(int a, int b) : a(a), b(b), val(a + b * sqrt(2)) {}

  bool operator<(const ABSqrt2& that) const { return val < that.val; }

  int a, b;
  double val;
};

vector<ABSqrt2> GenerateFirstKABSqrt2(int k) {
  set<ABSqrt2> candidates;
  // Initial for 0 + 0 * sqrt(2).
  candidates.emplace(0, 0);

  vector<ABSqrt2> result;
  while (result.size() < k) {
    auto next_smallest = candidates.cbegin();
    result.emplace_back(*next_smallest);

    // Adds the next two numbers derived from next_smallest.
    candidates.emplace(next_smallest->a + 1, next_smallest->b);
    candidates.emplace(next_smallest->a, next_smallest->b + 1);
    candidates.erase(next_smallest);
  }
  return result;
}
// @exclude

}  // namespace GeneratingABSqrt2

#endif  // SOLUTIONS_CPP_GENERATING_A_B_SQRT2_H_
