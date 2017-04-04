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

unsigned long LowestSetBit(unsigned long);
unsigned long LowestUnsetBit(unsigned long);

// @include
unsigned long ClosestIntSameBitCount(unsigned long x) {
  if (x == 0 || x == numeric_limits<unsigned long>::max()) {
    throw invalid_argument("All bits are 0 or 1");
  }
  unsigned long mask = (x & 1) == 0 ? LowestSetBit(x) : LowestUnsetBit(x);
  mask = mask | (mask >> 1);
  return x ^ mask;
}

unsigned long LowestSetBit(unsigned long x) { return x & ~(x - 1); }

unsigned long LowestUnsetBit(unsigned long x) { return LowestSetBit(x + 1); }
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
