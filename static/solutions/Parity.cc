// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <random>

#include "./Parity1.h"
#include "./Parity2.h"
#include "./Parity3.h"
#include "./Parity4.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;

int main(int argc, char* argv[]) {
  Parity3::BuildTable();
  if (argc == 2) {
    long x = atol(argv[1]);
    cout << "x = " << x << ", parity = " << Parity1::Parity(x)
         << ", parity = " << Parity3::Parity(x) << endl;
    assert(Parity1::Parity(x) == Parity3::Parity(x));
    assert(Parity2::Parity(x) == Parity3::Parity(x));
    assert(Parity3::Parity(x) == Parity4::Parity(x));
    cout << "x = " << x << ", parity = " << Parity3::Parity(x) << endl;
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 1000; ++times) {
      uniform_int_distribution<long> dis(0, numeric_limits<long>::max());
      long x = dis(gen);
      assert(Parity1::Parity(x) == Parity3::Parity(x));
      assert(Parity2::Parity(x) == Parity3::Parity(x));
      assert(Parity4::Parity(x) == Parity3::Parity(x));
      cout << "x = " << x << ", parity = " << Parity3::Parity(x) << endl;
    }
  }
  return 0;
}
