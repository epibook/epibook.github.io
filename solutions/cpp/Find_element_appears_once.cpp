// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int FindElementAppearsOnce(const vector<int> &A) {
  int ones = 0, twos = 0;
  int next_ones, next_twos;
  for (const int &i : A) {
    next_ones = (~i & ones) | (i & ~ones & ~twos);
    next_twos = (~i & twos) | (i & ones);
    ones = next_ones, twos = next_twos;
  }
  return ones;
}
// @exclude

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    vector<int> A;
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> n_dis(1, 10000);
      n = n_dis(gen);
    }
    uniform_int_distribution<int> dis(0, n - 1);
    int single = dis(gen);
    for (int i = 0; i < n; ++i) {
      A.emplace_back(i);
      if (i != single) {
        A.emplace_back(i);
        A.emplace_back(i);
      }
    }
    cout << "Singleton element: " << FindElementAppearsOnce(A) << endl;
    assert(FindElementAppearsOnce(A) == single);
  }
  return 0;
}
