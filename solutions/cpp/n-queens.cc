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

void NQueensHelper(int n, int row, vector<int>* col_placement,
                   vector<vector<string>>* result);
vector<string> CreateOutput(const vector<int>& col_placement);
bool IsFeasible(const vector<int>& col_placement, int row);

// @include
vector<vector<string>> NQueens(int n) {
  vector<int> placement(n);
  vector<vector<string>> result;
  NQueensHelper(n, 0, &placement, &result);
  return result;
}

void NQueensHelper(int n, int row, vector<int>* col_placement,
                   vector<vector<string>>* result) {
  if (row == n) {
    result->emplace_back(CreateOutput(*col_placement));
  } else {
    for (int col = 0; col < n; ++col) {
      (*col_placement)[row] = col;
      if (IsFeasible(*col_placement, row)) {
        NQueensHelper(n, row + 1, col_placement, result);
      }
    }
  }
}

vector<string> CreateOutput(const vector<int>& col_placement) {
  vector<string> sol;
  for (int row : col_placement) {
    string line(col_placement.size(), '.');
    line[row] = 'Q';
    sol.emplace_back(line);
  }
  return sol;
}

bool IsFeasible(const vector<int>& col_placement, int row) {
  for (int i = 0; i < row; ++i) {
    int diff = abs(col_placement[i] - col_placement[row]);
    if (diff == 0 || diff == row - i) {
      return false;
    }
  }
  return true;
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
  auto result = NQueens(n);
  for (const auto& vec : result) {
    for (const string& s : vec) {
      cout << s << endl;
    }
    cout << endl;
  }
  return 0;
}
