// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <vector>

using std::cout;
using std::endl;
using std::min;
using std::vector;

// @include
int FindMinimumTotal(const vector<vector<int>>& triangle) {
  if (triangle.empty()) {
    return 0;
  }

  // Stores the minimum path sum of triangle[i - 1].
  vector<int> prev_row(triangle.front());
  for (size_t i = 1; i < triangle.size(); ++i) {
    // Stores the minimum path sum of triangle[i].
    vector<int> curr_row(triangle[i]);
    curr_row.front() += prev_row.front();  // For the first element.
    for (size_t j = 1; j < curr_row.size() - 1; ++j) {
      curr_row[j] += min(prev_row[j - 1], prev_row[j]);
    }
    curr_row.back() += prev_row.back();  // For the last element.

    prev_row.swap(curr_row);
  }
  return *min_element(prev_row.cbegin(), prev_row.cend());
}
// @exclude

int main(int argc, char** argv) {
  vector<vector<int>> A = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
  assert(11 == FindMinimumTotal(A));
  return 0;
}
