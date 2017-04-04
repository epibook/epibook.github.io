// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <random>

#include "./GCD1.h"
#include "./GCD2.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;

void check(long long a, long long b) {
  assert(GCD1::GCD(a, b) == GCD2::GCD(a, b));
}

int main(int argc, char *argv[]) {
  check(2LL, 2LL);
  check(2LL, 3LL);
  check(17LL, 289LL);
  check(13LL, 17LL);
  check(17LL, 289LL);
  check(18LL, 24LL);
  check(1024LL * 1023LL, 1023LL * 1025LL);
  check(317LL * 1024LL * 1023LL, 317LL * 1023LL * 1025LL);
  check(numeric_limits<long long>::max(),
        numeric_limits<long long>::max() - 1LL);
  check(numeric_limits<long long>::max() - 1LL,
        (numeric_limits<long long>::max() - 1LL) / (2LL));
  check(0LL, 0LL);
  check(0LL, 1LL);
  check(10LL, 100LL);
  long long x = 18, y = 12;
  assert(GCD1::GCD(x, y) == 6);
  cout << GCD2::GCD(0, 1) << endl;
  cout << GCD1::GCD(0, 1) << endl;
  if (argc == 3) {
    x = atoll(argv[1]), y = atoll(argv[2]);
    cout << GCD1::GCD(x, y) << endl;
    assert(GCD1::GCD(x, y) == GCD2::GCD(x, y));
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 1000; ++times) {
      uniform_int_distribution<long long> dis(
          1, numeric_limits<long long>::max());
      x = dis(gen), y = dis(gen);
      assert(GCD1::GCD(x, y) == GCD2::GCD(x, y));
    }
  }
  return 0;
}
