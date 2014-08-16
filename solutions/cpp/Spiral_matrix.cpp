// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <iostream>
#include <random>
#include <vector>

using std::array;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
void PrintMatrixInSpiralOrder(vector<vector<int>> A) {
  const array<array<int, 2>, 4> shift = {{{{0, 1}}, {{1, 0}},
                                          {{0, -1}}, {{-1, 0}}}};
  int dir = 0, x = 0, y = 0;

  for (int i = 0; i < A.size() * A.size(); ++i) {
    cout << A[x][y] << ' ';
    A[x][y] = 0;
    int nx = x + shift[dir][0], ny = y + shift[dir][1];
    if (nx < 0 || nx >= A.size() || ny < 0 || ny >= A.size() ||
        A[nx][ny] == 0) {
      dir = (dir + 1) & 3;
      nx = x + shift[dir][0], ny = y + shift[dir][1];
    }
    x = nx, y = ny;
  }
}
// @exclude

int main(int argc, char *argv[]) {
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
  PrintMatrixInSpiralOrder(A);
  return 0;
}
