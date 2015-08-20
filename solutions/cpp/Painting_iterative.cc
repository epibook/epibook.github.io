// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <deque>
#include <iostream>
#include <queue>
#include <random>
#include <string>
#include <utility>
#include <vector>

using std::array;
using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::pair;
using std::queue;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

void PrintMatrix(const vector<deque<bool>> &A) {
  for (size_t i = 0; i < A.size(); ++i) {
    for (size_t j = 0; j < A[i].size(); ++j) {
      cout << A[i][j] << ' ';
    }
    cout << endl;
  }
}

// @include
void FilpColor(int x, int y, vector<deque<bool>> *A) {
  const array<array<int, 2>, 4> dir = {
      {{{0, 1}}, {{0, -1}}, {{1, 0}}, {{-1, 0}}}};
  const bool color = (*A)[x][y];

  queue<pair<int, int>> q;
  (*A)[x][y] = !(*A)[x][y];  // Flips.
  q.emplace(x, y);
  while (!q.empty()) {
    auto curr(q.front());
    for (const auto &d : dir) {
      const pair<int, int> next(curr.first + d[0], curr.second + d[1]);
      if (next.first >= 0 && next.first < A->size() && next.second >= 0 &&
          next.second < (*A)[next.first].size() &&
          (*A)[next.first][next.second] == color) {
        // Flips the color.
        (*A)[next.first][next.second] = !(*A)[next.first][next.second];
        q.emplace(next);
      }
    }
    q.pop();
  }
}
// @exclude

int main(int argc, char *argv[]) {
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
  FilpColor(static_cast<int>(i), static_cast<int>(j), &A);
  cout << endl;
  PrintMatrix(A);
  return 0;
}
