// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <stdexcept>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::exception;
using std::invalid_argument;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;

// @include
unsigned long ClosestIntSameBits(unsigned long x) {
  if (x == 0 || ~x == 0) {
    throw invalid_argument("all bits are 0 or 1");
  }

  int last_set_bit = x & ~(x - 1);
  if (last_set_bit == 1) {
    last_set_bit = (x + 1) & ~x;
  }
  return x ^ last_set_bit ^ (last_set_bit >> 1);
}
// @exclude

int CountBitsSetTo1(int x) {
  int count = 0;
  while (x) {
    x &= (x - 1);
    ++count;
  }
  return count;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  unsigned long x;
  if (argc == 2) {
    x = atol(argv[1]);
  } else {
    uniform_int_distribution<int> dis(0, numeric_limits<int>::max());
    x = dis(gen);
  }
  try {
    unsigned long res = ClosestIntSameBits(x);
    cout << x << ' ' << res << endl;
    assert(CountBitsSetTo1(x) == CountBitsSetTo1(res));
  } catch (const exception& e) {
    cout << x << ' ' << e.what() << endl;
  }
  return 0;
}
