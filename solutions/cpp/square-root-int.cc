// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <iostream>
#include <limits>
#include <numeric>
#include <random>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;

// @include
int SquareRoot(int k) {
  long left = 0, right = k;
  while (left <= right) {
    long mid = left + ((right - left) / 2);
    long mid_squared = mid * mid;
    if (mid_squared <= k) {
      left = mid + 1;
    } else {
      right = mid - 1;
    }
  }
  return left - 1;
}
// @exclude

int main(int argc, char** argv) {
  assert(SquareRoot(0) == 0);
  assert(SquareRoot(1) == 1);
  assert(SquareRoot(121) == 11);
  assert(SquareRoot(64) == 8);
  int x;
  if (argc == 2) {
    x = stoi(argv[1]);
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(0, numeric_limits<int>::max());
    for (int times = 0; times < 100000; ++times) {
      x = dis(gen);
      int result[2];
      cout << "x is " << x << endl;
      cout << (result[0] = SquareRoot(x)) << ' '
           << (result[1] = static_cast<int>(sqrt(x))) << endl;
      assert(result[0] == result[1]);
    }
  }
  x = 2147395599;
  cout << SquareRoot(x) << endl;
  return 0;
}
