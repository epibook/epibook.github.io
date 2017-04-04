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
using std::swap;
using std::uniform_int_distribution;
using std::vector;

void SolveNQueens(int, vector<int>*, vector<vector<string>>*);
vector<string> ToTextRepresentation(const vector<int>& col_placement);
bool IsValid(const vector<int>&, int);

// @include
vector<vector<string>> NQueens(int n) {
  vector<int> placement(n);
  iota(placement.begin(), placement.end(), 0);
  vector<vector<string>> result;
  SolveNQueens(0, &placement, &result);
  return result;
}

void SolveNQueens(int row, vector<int>* col_placement,
                  vector<vector<string>>* result) {
  if (row == col_placement->size()) {
    // All queens are legal placed.
    result->emplace_back(ToTextRepresentation(*col_placement));
  } else {
    for (int i = row; i < col_placement->size(); ++i) {
      swap((*col_placement)[row], (*col_placement)[i]);
      if (IsValid(*col_placement, row)) {
        SolveNQueens(row + 1, col_placement, result);
      }
      swap((*col_placement)[row], (*col_placement)[i]);
    }
  }
}

vector<string> ToTextRepresentation(const vector<int>& col_placement) {
  vector<string> sol;
  for (int row : col_placement) {
    string line(col_placement.size(), '.');
    line[row] = 'Q';
    sol.emplace_back(line);
  }
  return sol;
}

// Test if a newly placed queen on row_id will conflict any earlier queens
// placed before.
bool IsValid(const vector<int>& col_placement, int row_id) {
  for (int i = 0; i < row_id; ++i) {
    if (abs(col_placement[i] - col_placement[row_id]) == row_id - i) {
      // A column or diagonal constraint is violated.
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
  for (const vector<string>& vec : result) {
    for (const string& s : vec) {
      cout << s << endl;
    }
    cout << endl;
  }
  return 0;
}
