// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <array>
#include <cassert>
#include <deque>
#include <iostream>
#include <queue>
#include <random>
#include <string>
#include <vector>
#include <utility>

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

void MarkBoundaryRegion(int, int, const vector<vector<char>>&,
                        vector<deque<bool>>*);

// @include
void FillSurroundedRegions(vector<vector<char>>* board_ptr) {
  vector<vector<char>>& board = *board_ptr;
  if (board.empty()) {
    return;
  }

  vector<deque<bool>> visited(board.size(),
                              deque<bool>(board.front().size(), false));
  // Identifies the regions that are reachable via white path starting from
  // the first or last columns.
  for (int i = 0; i < board.size(); ++i) {
    if (board[i][0] == 'W' && !visited[i][0]) {
      MarkBoundaryRegion(i, 0, board, &visited);
    }
    if (board[i][board[i].size() - 1] == 'W' &&
        !visited[i][board[i].size() - 1]) {
      MarkBoundaryRegion(i, board[i].size() - 1, board, &visited);
    }
  }
  // Identifies the regions that are reachable via white path starting from
  // the first or last rows.
  for (int j = 0; j < board.front().size(); ++j) {
    if (board[0][j] == 'W' && !visited[0][j]) {
      MarkBoundaryRegion(0, j, board, &visited);
    }
    if (board[board.size() - 1][j] == 'W' && !visited[board.size() - 1][j]) {
      MarkBoundaryRegion(board.size() - 1, j, board, &visited);
    }
  }

  // Marks the surrounded white regions as black.
  for (int i = 1; i < board.size() - 1; ++i) {
    for (int j = 1; j < board[i].size() - 1; ++j) {
      if (board[i][j] == 'W' && !visited[i][j]) {
        board[i][j] = 'B';
      }
    }
  }
}

void MarkBoundaryRegion(int i, int j, const vector<vector<char>>& board,
                        vector<deque<bool>>* visited_ptr) {
  struct Coordinate {
    int x, y;
  };

  queue<Coordinate> q;
  vector<deque<bool>>& visited = *visited_ptr;
  q.emplace(Coordinate{i, j}), visited[i][j] = true;
  // Uses BFS to traverse this region.
  while (!q.empty()) {
    const Coordinate curr = q.front();
    q.pop();
    const static array<array<int, 2>, 4> kDirs = {
        {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}};
    for (const array<int, 2>& d : kDirs) {
      const Coordinate next = Coordinate{curr.x + d[0], curr.y + d[1]};
      if (next.x >= 0 && next.x < board.size() && next.y >= 0 &&
          next.y < board[next.x].size() && board[next.x][next.y] == 'W' &&
          !visited[next.x][next.y]) {
        visited[next.x][next.y] = true;
        q.emplace(next);
      }
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
