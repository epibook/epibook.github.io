// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <array>
#include <cassert>
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
using std::equal;
using std::queue;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

void MarkBoundaryRegion(int, int, vector<vector<char>>*);

// @include
void FillSurroundedRegions(vector<vector<char>>* board_ptr) {
  vector<vector<char>>& board = *board_ptr;
  // Identifies the regions that are reachable via white path starting from
  // the first or last columns.
  for (int i = 0; i < board.size(); ++i) {
    if (board[i][0] == 'W') {
      MarkBoundaryRegion(i, 0, board_ptr);
    }
    if (board[i][board[i].size() - 1] == 'W') {
      MarkBoundaryRegion(i, board[i].size() - 1, board_ptr);
    }
  }
  // Identifies the regions that are reachable via white path starting from
  // the first or last rows.
  for (int j = 0; j < board.front().size(); ++j) {
    if (board[0][j] == 'W') {
      MarkBoundaryRegion(0, j, board_ptr);
    }
    if (board[board.size() - 1][j] == 'W') {
      MarkBoundaryRegion(board.size() - 1, j, board_ptr);
    }
  }

  // Marks the surrounded white regions as black.
  for (vector<char>& row : board) {
    for (char& c : row) {
      c = c != 'T' ? 'B' : 'W';
    }
  }
}

void MarkBoundaryRegion(int i, int j, vector<vector<char>>* board_ptr) {
  struct Coordinate {
    int x, y;
  };

  queue<Coordinate> q(deque<Coordinate>(1, Coordinate{i, j}));
  auto& board = *board_ptr;
  // Uses BFS to traverse this region.
  while (!q.empty()) {
    const Coordinate curr = q.front();
    q.pop();
    if (curr.x >= 0 && curr.x < board.size() && curr.y >= 0 &&
        curr.y < board[curr.x].size() && board[curr.x][curr.y] == 'W') {
      board[curr.x][curr.y] = 'T';
      q.emplace(Coordinate{curr.x - 1, curr.y});
      q.emplace(Coordinate{curr.x + 1, curr.y});
      q.emplace(Coordinate{curr.x, curr.y + 1});
      q.emplace(Coordinate{curr.x, curr.y - 1});
    }
  }
}
// @exclude

void SimpleTest() {
  vector<vector<char>> A = {{'B', 'B', 'B', 'B'},
                            {'W', 'B', 'W', 'B'},
                            {'B', 'W', 'W', 'B'},
                            {'B', 'B', 'B', 'B'}};
  FillSurroundedRegions(&A);
  vector<vector<char>> golden = {{'B', 'B', 'B', 'B'},
                                 {'W', 'B', 'B', 'B'},
                                 {'B', 'B', 'B', 'B'},
                                 {'B', 'B', 'B', 'B'}};
  assert(equal(A.begin(), A.end(), golden.begin(), golden.end()));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis(1, 1000);
  int n, m;
  if (argc == 3) {
    n = static_cast<int>(stoi(argv[1]));
    m = static_cast<int>(stoi(argv[2]));
  } else {
    n = dis(gen), m = dis(gen);
  }
  vector<vector<char>> board(n, vector<char>(m));
  uniform_int_distribution<int> zero_or_one(0, 1);
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < m; ++j) {
      board[i][j] = zero_or_one(gen) ? 'B' : 'W';
    }
  }
  for (int i = 0; i < board.size(); ++i) {
    for (int j = 0; j < board[i].size(); ++j) {
      cout << board[i][j];
    }
    cout << endl;
  }
  FillSurroundedRegions(&board);
  cout << endl;
  for (int i = 0; i < board.size(); ++i) {
    for (int j = 0; j < board[i].size(); ++j) {
      cout << board[i][j];
    }
    cout << endl;
  }
  return 0;
}
