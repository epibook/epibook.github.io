// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

// @include
bool is_feasible(const vector<int>& col_placement, int row) {
  for (int i = 0; i < row; ++i) {
    int diff = abs(col_placement[i] - col_placement[row]);
    if (diff == 0 || diff == row - i) {
      return false;
    }
  }
  return true;
}

int n_queens_helper(int n, int row, vector<int>* col_placement) {
  if (row == n) {
    return 1;
  } else {
    int sum = 0;
    for (int col = 0; col < n; ++col) {
      (*col_placement)[row] = col;
      if (is_feasible(*col_placement, row)) {
        sum += n_queens_helper(n, row + 1, col_placement);
      }
    }
    return sum;
  }
}

int n_queens(int n) {
  vector<int> placement(n);
  return n_queens_helper(n, 0, &placement);
}
// @exclude

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 15);
    n = dis(gen);
  }
  cout << "n = " << n << endl;
  cout << n_queens(n) << endl;
  return 0;
}
