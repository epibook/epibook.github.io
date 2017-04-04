// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <bitset>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <string>

using std::bitset;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;

// @include
short CountBits(unsigned int x) {
  short num_bits = 0;
  while (x) {
    num_bits += x & 1;
    x >>= 1;
  }
  return num_bits;
}
// @exclude

int main(int argc, char* argv[]) {
  if (argc == 2) {
    int x = stoul(argv[1]);
    cout << "x = " << x << ", = " << CountBits(x) << endl;
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 1000; ++times) {
      uniform_int_distribution<int> dis(0, numeric_limits<int>::max());
      int x = dis(gen);
      cout << "x = " << x << ",  = " << CountBits(x) << endl;
      bitset<32> checker(x);
      assert(CountBits(x) == checker.count());
    }
  }
  return 0;
}
