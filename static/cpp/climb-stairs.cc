// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;

// @include
int climb_stairs(int n) {
  if (n <= 1) {
    return 1;
  }

  int pre_2 = 1, pre_1 = 1;
  for (int i = 2; i <= n; ++i) {
    int temp = pre_2 + pre_1;
    pre_2 = pre_1;
    pre_1 = temp;
  }
  return pre_1;
}
// @exclude

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 100);
    n = dis(gen);
  }
  cout << climb_stairs(n) << endl;
  return 0;
}
