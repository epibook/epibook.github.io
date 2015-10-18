// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

int PickUpCoinsHelper(const vector<int>&, int, int, vector<vector<int>>*);

// @include
int PickUpCoins(const vector<int>& C) {
  vector<vector<int>> T(C.size(), vector<int>(C.size(), -1));
  return PickUpCoinsHelper(C, 0, C.size() - 1, &T);
}

int PickUpCoinsHelper(const vector<int>& C, int a, int b,
                      vector<vector<int>>* T_ptr) {
  if (a > b) {
    return 0;  // Base condition.
  }

  vector<vector<int>>& T = *T_ptr;
  if (T[a][b] == -1) {
    T[a][b] = max(C[a] + min(PickUpCoinsHelper(C, a + 2, b, T_ptr),
                             PickUpCoinsHelper(C, a + 1, b - 1, T_ptr)),
                  C[b] + min(PickUpCoinsHelper(C, a + 1, b - 1, T_ptr),
                             PickUpCoinsHelper(C, a, b - 2, T_ptr)));
  }
  return T[a][b];
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  vector<int> C;
  if (argc >= 2) {
    for (int i = 1; i < argc; ++i) {
      C.emplace_back(atoi(argv[i]));
    }
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    C.resize(dis(gen));
    for (int i = 0; i < C.size(); ++i) {
      uniform_int_distribution<int> dis(0, 99);
      C[i] = dis(gen);
    }
  }
  for (size_t i = 0; i < C.size(); ++i) {
    cout << C[i] << ' ';
  }
  cout << endl;
  cout << PickUpCoins(C) << endl;
  return 0;
}
