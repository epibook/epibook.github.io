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
unsigned long ClosestIntSameBitCount(unsigned long x) {
  for (int i = 0; i < 63; ++i) {
    if (((x >> i) & 1) != ((x >> (i + 1)) & 1)) {
      x ^= (1UL << i) | (1UL << (i + 1));  // Swaps bit-i and bit-(i + 1).
      return x;
    }
  }

  // Throw error if all bits of x are 0 or 1.
  throw invalid_argument("all bits are 0 or 1");
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

void SmallTest() {
  assert(ClosestIntSameBitCount(6) == 5);
  assert(ClosestIntSameBitCount(7) == 11);
  assert(ClosestIntSameBitCount(2) == 1);
  assert(ClosestIntSameBitCount(32) == 16);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  unsigned long x;
  if (argc == 2) {
    x = atol(argv[1]);
  } else {
    uniform_int_distribution<int> dis(0, numeric_limits<int>::max());
    x = dis(gen);
  }
  try {
    unsigned long res = ClosestIntSameBitCount(x);
    cout << x << ' ' << res << endl;
    assert(CountBitsSetTo1(x) == CountBitsSetTo1(res));
  } catch (const exception& e) {
    cout << x << ' ' << e.what() << endl;
  }
  return 0;
}
