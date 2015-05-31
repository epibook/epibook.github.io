// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <vector>

using std::array;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> MatrixInSpiralOrder(vector<vector<int>> A) {
  const array<array<int, 2>, 4> shift = {
      {{{0, 1}}, {{1, 0}}, {{0, -1}}, {{-1, 0}}}};
  int dir = 0, x = 0, y = 0;
  vector<int> result;

  for (int i = 0; i < A.size() * A.size(); ++i) {
    result.emplace_back(A[x][y]);
    A[x][y] = 0;
    int next_x = x + shift[dir][0], next_y = y + shift[dir][1];
    if (next_x < 0 || next_x >= A.size() || next_y < 0 || next_y >= A.size() ||
        A[next_x][next_y] == 0) {
      dir = (dir + 1) & 3;
      next_x = x + shift[dir][0], next_y = y + shift[dir][1];
    }
    x = next_x, y = next_y;
  }
  return result;
}
// @exclude

void SimpleTest() {
  vector<vector<int>> A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
  auto result = MatrixInSpiralOrder(A);
  vector<int> golden_result = {1, 2, 3, 6, 9, 8, 7, 4, 5};
  assert(result.size() == golden_result.size() &&
         equal(result.begin(), result.end(), golden_result.begin()));
}

int main(int argc, char *argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  int N;
  if (argc == 2) {
    N = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 50);
    N = dis(gen);
  }
  vector<vector<int>> A(N, vector<int>(N));
  int x = 1;
  for (size_t i = 0; i < N; ++i) {
    for (size_t j = 0; j < N; ++j) {
      A[i][j] = x++;
    }
  }
  auto result = MatrixInSpiralOrder(A);
  copy(result.begin(), result.end(), ostream_iterator<int>(cout, " "));
  cout << endl;
  return 0;
}
