// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <cassert>
#include <limits>
#include <random>

#include "./GCD.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;

int main(int argc, char *argv[]) {
  long long x = 18, y = 12;
  assert(GCD(x, y) == 6);
  if (argc == 3) {
    x = atoll(argv[1]), y = atoll(argv[2]);
    cout << GCD(x, y) << endl;
    assert(GCD(x, y) == AnotherGCD(x, y));
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 1000; ++times) {
      uniform_int_distribution<long long> dis(1,
                                              numeric_limits<long long>::max());
      x = dis(gen), y = dis(gen);
      assert(GCD(x, y) == AnotherGCD(x, y));
    }
  }
  return 0;
}
