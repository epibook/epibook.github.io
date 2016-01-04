// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <deque>
#include <iostream>
#include <queue>
#include <random>
#include <string>
#include <vector>

using std::array;
using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

void PrintMatrix(const vector<deque<bool>>& A) {
  for (size_t i = 0; i < A.size(); ++i) {
    for (size_t j = 0; j < A.size(); ++j) {
      cout << A[i][j] << ' ';
    }
    cout << endl;
  }
}

// @include
void FlipColor(int x, int y, vector<deque<bool>>* A_ptr) {
  vector<deque<bool>>& A = *A_ptr;
  const array<array<int, 2>, 4> kDirs = {
      {{{0, 1}}, {{0, -1}}, {{1, 0}}, {{-1, 0}}}};
  const bool color = A[x][y];

  A[x][y] = !color;  // Flips.
  for (const array<int, 2>& dir : kDirs) {
    const int next_x = x + dir[0], next_y = y + dir[1];
    if (next_x >= 0 && next_x < A.size() && next_y >= 0 &&
        next_y < A[next_x].size() && A[next_x][next_y] == color) {
      FlipColor(next_x, next_y, A_ptr);
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  size_t n;
  default_random_engine gen((random_device())());
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    uniform_int_distribution<size_t> dis(1, 100);
    n = dis(gen);
  }
  vector<deque<bool>> A(n, deque<bool>(n));
  for (size_t i = 0; i < n; ++i) {
    for (size_t j = 0; j < n; ++j) {
      uniform_int_distribution<int> zero_or_one(0, 1);
      A[i][j] = zero_or_one(gen);
    }
  }
  uniform_int_distribution<size_t> dis(0, n - 1);
  size_t i = dis(gen), j = dis(gen);
  cout << "color = " << i << ' ' << j << ' ' << A[i][j] << endl;
  PrintMatrix(A);
  FlipColor(static_cast<int>(i), static_cast<int>(j), &A);
  cout << endl;
  PrintMatrix(A);
  return 0;
}
