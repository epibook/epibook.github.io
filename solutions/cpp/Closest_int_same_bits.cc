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

int SetBit(int x, int bit, int set_value) {
  return set_value ? x | (1 << bit) : x & ~(1 << bit);
}

int Search(int x, int set_value, int default_value) {
  int index = 0, num = 0;
  while (((x >> index) & 1) != set_value) {
    ++index;
  }
  while (((x >> index) & 1) == set_value) {
    ++index;
    ++num;
  }
  if (index == 32) {
    return default_value;
  } else {
    x ^= (1 << index);
    --index;
    --num;
    for (int i = index; i >= num; --i) {
      x = SetBit(x, i, !set_value);
    }
    for (int i = num - 1; i >= 0; --i) {
      x = SetBit(x, i, set_value);
    }
    return x;
  }
}

/*
int ClosestIntSameBits(int x) {
  int prev = Search(x, 0, INT_MIN);
  int next = Search(x, 1, numeric_limits<int>::max());
  return abs(x - prev) < abs(x - next) ? prev : next;
}
*/

// @include
unsigned long ClosestIntSameBits(unsigned long x) {
  for (int i = 0; i < 63; ++i) {
    if (((x >> i) & 1) ^ ((x >> (i + 1)) & 1)) {
      x ^= (1UL << i) | (1UL << (i + 1));  // swaps bit-i and bit-(i + 1).
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
