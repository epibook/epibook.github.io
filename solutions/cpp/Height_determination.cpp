// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <vector>

using std::cout;
using std::endl;
using std::vector;

int GetHeightHelper(int c, int d, vector<vector<int>>* F);

// @include
int GetHeight(int c, int d) {
  vector<vector<int>> F(c + 1, vector<int>(d + 1, -1));
  return GetHeightHelper(c, d, &F);
}

int GetHeightHelper(int c, int d, vector<vector<int>>* F) {
  if (c == 0 || d == 0) {
    return 0;
  } else if (c == 1) {
    return d;
  } else {
    if ((*F)[c][d] == -1) {
      (*F)[c][d] =
          GetHeightHelper(c, d - 1, F) + GetHeightHelper(c - 1, d - 1, F) + 1;
    }
    return (*F)[c][d];
  }
}
// @exclude

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
  return 0;
}
