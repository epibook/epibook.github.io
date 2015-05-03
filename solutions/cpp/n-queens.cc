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

void SolveNQueens(int, int, vector<int>*, vector<vector<int>>*);
bool IsValid(const vector<int>&);

// @include
vector<vector<int>> NQueens(int n) {
  vector<int> placement;
  vector<vector<int>> result;
  SolveNQueens(n, 0, &placement, &result);
  return result;
}

void SolveNQueens(int n, int row, vector<int>* col_placement,
                  vector<vector<int>>* result) {
  if (row == n) {
    // All queens are legally placed.
    result->emplace_back(*col_placement);
  } else {
    for (int col = 0; col < n; ++col) {
      col_placement->emplace_back(col);
      if (IsValid(*col_placement)) {
        SolveNQueens(n, row + 1, col_placement, result);
      }
      col_placement->pop_back();
    }
  }
}

// Test if a newly placed queen on row_id will conflict any earlier queens
// placed before.
bool IsValid(const vector<int>& col_placement) {
  int row_id = col_placement.size() - 1;
  for (int i = 0; i < row_id; ++i) {
    int diff = abs(col_placement[i] - col_placement[row_id]);
    if (diff == 0 || diff == row_id - i) {
      // A column or diagonal constraint is violated.
      return false;
    }
  }
  return true;
}
// @exclude

vector<string> ToTextRepresentation(const vector<int>& col_placement) {
  vector<string> sol;
  for (int row : col_placement) {
    string line(col_placement.size(), '.');
    line[row] = 'Q';
    sol.emplace_back(line);
  }
  return sol;
}

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
    vector<string> text_rep = ToTextRepresentation(vec);
    for (const string& s : text_rep) {
      cout << s << endl;
    }
    cout << endl;
  }
  return 0;
}
