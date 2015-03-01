// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./swap_bits.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

vector<long> precomputed_reverse;

long ReverseX(long x, int n) {
  for (int i = 0, j = n; i < j; ++i, --j) {
    x = SwapBits(x, i, j);
  }
  return x;
}

void CreatePrecomputedTable() {
  for (int i = 0; i < (1 << 16); ++i) {
    precomputed_reverse.emplace_back(ReverseX(i, 15));
  }
}

// @include
long ReverseBits(long x) {
  const int kWordSize = 16;
  const int kBitMask = 0xFFFF;
  return precomputed_reverse[x & kBitMask] << (3 * kWordSize) |
         precomputed_reverse[(x >> kWordSize) & kBitMask] << (2 * kWordSize) |
         precomputed_reverse[(x >> (2 * kWordSize)) & kBitMask] << kWordSize |
         precomputed_reverse[(x >> (3 * kWordSize)) & kBitMask];
}
// @exclude

int main(int argc, char* argv[]) {
  CreatePrecomputedTable();
  if (argc == 2) {
    long x = atoi(argv[1]);
    cout << "sizeof(x) = " << sizeof(x) << endl;
    cout << "x = " << x << ", reverse x = " << ReverseBits(x) << endl;
    cout << ReverseX(x, 63) << endl;
    assert(ReverseBits(x) == ReverseX(x, 63));
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 1000; ++times) {
      uniform_int_distribution<long> dis(0, numeric_limits<long>::max());
      long x = dis(gen);
      cout << "x = " << x << ", reverse x = " << ReverseBits(x) << endl;
      assert(ReverseBits(x) == ReverseX(x, 63));
    }
  }
  return 0;
}
