// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

// @include
bool is_scramble(const string& s1, const string& s2) {
  int n = s1.size();
  if (n != s2.size()) {
    return false;
  }
  if (!s1.compare(s2)) {
    return true;
  }

  vector<vector<deque<bool>>> T(n, vector<deque<bool>>(n, deque<bool>(n)));
  for (size_t i = 0; i < n; ++i) {
    for (size_t j = 0; j < n; ++j) {
      T[i][j][0] = s1[i] == s2[j];
    }
  }

  for (int len = 1; len < n; ++len) {
    for (int i = 0; i + len < n; ++i) {
      for (int j = 0; j + len < n; ++j) {
        T[i][j][len] = false;
        for (int k = 0; k < len; ++k) {
          if ((T[i][j][k] && T[i + k + 1][j + k + 1][len - 1 - k]) ||
              (T[i][j + len - k][k] && T[i + k + 1][j][len - 1 - k])) {
            T[i][j][len] = true;
            break;
          }
        }
      }
    }
  }
  return T[0][0][n - 1];
}
// @exclude

int main(int argc, char** argv) {
  assert(is_scramble("great", "rgtae"));
  return 0;
}
