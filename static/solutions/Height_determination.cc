// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <vector>

using std::vector;

int GetHeightHelper(int, int, vector<vector<int>>*);

// @include
int GetHeight(int cases, int drops) {
  vector<vector<int>> F(cases + 1, vector<int>(drops + 1, -1));
  return GetHeightHelper(cases, drops, &F);
}

int GetHeightHelper(int cases, int drops, vector<vector<int>>* F) {
  if (cases == 0 || drops == 0) {
    return 0;
  } else if (cases == 1) {
    return drops;
  } else {
    if ((*F)[cases][drops] == -1) {
      (*F)[cases][drops] = GetHeightHelper(cases, drops - 1, F) +
                           GetHeightHelper(cases - 1, drops - 1, F) + 1;
    }
    return (*F)[cases][drops];
  }
}
// @exclude

int CheckAnswer(int cases, int drops) {
  if (cases <= 0 || drops <= 0) {
    return 0;
  }
  vector<int> row(cases, 0);
  for (int d = 0; d < drops; ++d) {
    for (int c = cases - 1; c >= 0; --c) {
      row[c] += (c == 0 ? 0 : row[c - 1]) + 1;
    }
  }
  return row.back();
}

int main(int argc, char* argv[]) {
  assert(GetHeight(1, 10) == 10);
  assert(GetHeight(2, 1) == 1);
  assert(GetHeight(2, 2) == 3);
  assert(GetHeight(2, 3) == 6);
  assert(GetHeight(2, 4) == 10);
  assert(GetHeight(2, 5) == 15);
  assert(GetHeight(3, 2) == 3);
  assert(GetHeight(100, 2) == 3);
  assert(GetHeight(3, 5) == 25);
  assert(GetHeight(8, 11) == 1980);
  assert(GetHeight(3, 0) == 0);
  assert(GetHeight(3, 1) == 1);
  assert(GetHeight(3, 3) == 7);
  assert(GetHeight(0, 10) == 0);
  assert(GetHeight(0, 0) == 0);

  assert(CheckAnswer(1, 10) == 10);
  assert(CheckAnswer(2, 1) == 1);
  assert(CheckAnswer(2, 2) == 3);
  assert(CheckAnswer(2, 3) == 6);
  assert(CheckAnswer(2, 4) == 10);
  assert(CheckAnswer(2, 5) == 15);
  assert(CheckAnswer(3, 2) == 3);
  assert(CheckAnswer(100, 2) == 3);
  assert(CheckAnswer(3, 5) == 25);
  assert(CheckAnswer(8, 11) == 1980);
  assert(CheckAnswer(3, 0) == 0);
  assert(CheckAnswer(3, 1) == 1);
  assert(CheckAnswer(3, 3) == 7);
  assert(CheckAnswer(0, 10) == 0);
  assert(CheckAnswer(0, 0) == 0);
  return 0;
}
