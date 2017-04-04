// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;

unsigned Add(unsigned a, unsigned b);

// @include
unsigned Multiply(unsigned x, unsigned y) {
  unsigned sum = 0;
  while (x) {
    // Examines each bit of x.
    if (x & 1) {
      sum = Add(sum, y);
    }
    x >>= 1, y <<= 1;
  }
  return sum;
}

unsigned Add(unsigned a, unsigned b) {
  unsigned sum = 0, carryin = 0, k = 1, temp_a = a, temp_b = b;
  while (temp_a || temp_b) {
    unsigned ak = a & k, bk = b & k;
    unsigned carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
    sum |= (ak ^ bk ^ carryin);
    carryin = carryout << 1, k <<= 1, temp_a >>= 1, temp_b >>= 1;
  }
  return sum | carryin;
}
// @exclude

int main(int argc, char* argv[]) {
  if (argc == 3) {
    unsigned int x = atoi(argv[1]), y = atoi(argv[2]);
    unsigned int res = Multiply(x, y);
    assert(res == x * y);
    cout << "PASS: x = " << x << ", y = " << y << "; prod = " << res << endl;
  } else {
    default_random_engine gen((random_device())());
    // Random test, only works if the product is not greater than 2^32 - 1.
    for (int i = 0; i < 100000; ++i) {
      uniform_int_distribution<int> dis(0, 65534);
      unsigned int x = dis(gen), y = dis(gen);
      unsigned int prod = Multiply(x, y);
      assert(prod == x * y);
      cout << "PASS: x = " << x << ", y = " << y << "; prod = " << prod
           << endl;
    }
  }
  return 0;
}
