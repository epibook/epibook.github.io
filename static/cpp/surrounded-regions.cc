// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <array>
#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <string>
#include <vector>
#include <utility>

using std::array;
using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

void MarkRegionIfSurrounded(int, int, vector<vector<char>>*,
                            vector<deque<bool>>*);

// @include
void FillSurroundedRegions(vector<vector<char>>* board) {
  if (board->empty()) {
    return;
  }

  vector<deque<bool>> visited(board->size(),
                              deque<bool>(board->front().size(), false));
  for (size_t i = 1; i < board->size() - 1; ++i) {
    for (size_t j = 1; j < (*board)[i].size() - 1; ++j) {
      if ((*board)[i][j] == 'W' && !visited[i][j]) {
        MarkRegionIfSurrounded(i, j, board, &visited);
      }
    }
  }
}

void MarkRegionIfSurrounded(int i, int j, vector<vector<char>>* board,
                            vector<deque<bool>>* visited) {
  struct Coordinate {
    int x, y;
  };
  vector<Coordinate> q;  // Uses it as an queue.
  q.emplace_back(Coordinate{i, j}), (*visited)[i][j] = true;
  bool is_surrounded = true;
  size_t idx = 0;
  // Uses BFS to traverse this region.
  while (idx < q.size()) {
    const auto curr = q[idx++];
    // A 'W' on the border means this region is not surrounded.
    if (curr.x == 0 || curr.x == board->size() - 1 || curr.y == 0 ||
        curr.y == (*board)[curr.x].size() - 1) {
      is_surrounded = false;
    } else {
      const static array<array<int, 2>, 4> kDirs = {
          {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}};
      for (const array<int, 2>& d : kDirs) {
        const Coordinate next = Coordinate{curr.x + d[0], curr.y + d[1]};
        if ((*board)[next.x][next.y] == 'W' && !(*visited)[next.x][next.y]) {
          (*visited)[next.x][next.y] = true;
          q.emplace_back(next);
        }
      }
    }
  }

  if (is_surrounded) {
    // Marks surrounded regions in q.
    for (const Coordinate& p : q) {
      (*board)[p.x][p.y] = 'B';
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<size_t> dis(1, 1000);
  size_t n, m;
  if (argc == 3) {
    n = static_cast<size_t>(stoi(argv[1]));
    m = static_cast<size_t>(stoi(argv[2]));
  } else {
    n = dis(gen), m = dis(gen);
  }
  vector<vector<char>> board(n, vector<char>(m));
  uniform_int_distribution<int> zero_or_one(0, 1);
  for (size_t i = 0; i < n; ++i) {
    for (size_t j = 0; j < m; ++j) {
      board[i][j] = zero_or_one(gen) ? 'B' : 'W';
    }
  }
  for (size_t i = 0; i < board.size(); ++i) {
    for (size_t j = 0; j < board[i].size(); ++j) {
      cout << board[i][j];
    }
    cout << endl;
  }
  FillSurroundedRegions(&board);
  cout << endl;
  for (size_t i = 0; i < board.size(); ++i) {
    for (size_t j = 0; j < board[i].size(); ++j) {
      cout << board[i][j];
    }
    cout << endl;
  }
  return 0;
}
