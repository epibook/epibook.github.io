// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_MAX_SUBMATRIX_RECTANGLE_BRUTE_FORCE_H_
#define SOLUTIONS_MAX_SUBMATRIX_RECTANGLE_BRUTE_FORCE_H_

#include <deque>
#include <vector>

using std::deque;
using std::vector;

// O(m^3 n^3) time solution.
int MaxRectangleSubmatrixBruteForce(const vector<deque<bool>> &A) {
  int max = 0;
  for (int a = 0; a < A.size(); ++a) {
    for (int b = 0; b < A[a].size(); ++b) {
      for (int c = a; c < A.size(); ++c) {
        for (int d = b; d < A[c].size(); ++d) {
          bool all_1 = true;
          int count = 0;
          for (int i = a; i <= c; ++i) {
            for (int j = b; j <= d; ++j) {
              if (A[i][j] == false) {
                all_1 = false;
                count = 0;
                break;
              } else {
                ++count;
              }
            }
            if (all_1 == false) {
              break;
            }
          }
          if (all_1 == true && count > max) {
            max = count;
          }
        }
      }
    }
  }
  return max;
}
#endif  // SOLUTIONS_MAX_SUBMATRIX_RECTANGLE_BRUTE_FORCE_H_
