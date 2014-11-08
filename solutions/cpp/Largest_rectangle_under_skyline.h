// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_LARGEST_RECTANGLE_UNDER_SKYLINE_H_
#define SOLUTIONS_LARGEST_RECTANGLE_UNDER_SKYLINE_H_

#include <algorithm>
#include <stack>
#include <vector>

using std::max;
using std::stack;
using std::vector;

int CalculateLargestRectangleAlternative(const vector<int>& A) {
  // Calculate L.
  stack<int> s;
  vector<int> L;
  for (int i = 0; i < A.size(); ++i) {
    while (!s.empty() && A[s.top()] >= A[i]) {
      s.pop();
    }
    L.emplace_back(s.empty() ? -1 : s.top());
    s.emplace(i);
  }

  // Clear stack for calculating R.
  while (!s.empty()) {
    s.pop();
  }
  vector<int> R(A.size());
  for (int i = A.size() - 1; i >= 0; --i) {
    while (!s.empty() && A[s.top()] >= A[i]) {
      s.pop();
    }
    R[i] = s.empty() ? A.size() : s.top();
    s.emplace(i);
  }

  // For each A[i], find its maximum area include it.
  int max_area = 0;
  for (int i = 0; i < A.size(); ++i) {
    max_area = max(max_area, A[i] * (R[i] - L[i] - 1));
  }
  return max_area;
}

// @include
int CalculateLargestRectangle(const vector<int>& A) {
  stack<int> s;
  int max_area = 0;
  for (int i = 0; i <= A.size(); ++i) {
    while (!s.empty() && (i == A.size() || A[i] < A[s.top()])) {
      int height = A[s.top()];
      s.pop();
      max_area = max(max_area, height * (s.empty() ? i : i - s.top() - 1));
    }
    s.emplace(i);
  }
  return max_area;
}
// @exclude

#endif  // SOLUTIONS_LARGEST_RECTANGLE_UNDER_SKYLINE_H_
