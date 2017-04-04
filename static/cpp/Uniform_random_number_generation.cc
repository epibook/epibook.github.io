// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <iostream>
#include <random>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;

int ZeroOneRandom() {
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis(0, 1);
  return dis(gen);
}

// @include
int UniformRandom(int lower_bound, int upper_bound) {
  int number_of_outcomes = upper_bound - lower_bound + 1, result;
  do {
    result = 0;
    for (int i = 0; (1 << i) < number_of_outcomes; ++i) {
      // ZeroOneRandom() is the provided random number generator.
      result = (result << 1) | ZeroOneRandom();
    }
  } while (result >= number_of_outcomes);
  return result + lower_bound;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int a, b;
    if (argc == 3) {
      a = atoi(argv[1]), b = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> a_dis(0, 99);
      a = a_dis(gen);
      uniform_int_distribution<int> b_dis(a + 1, a + 100);
      b = b_dis(gen);
    }
    int x = UniformRandom(a, b);
    cout << "a = " << a << " b = " << b << endl;
    cout << "random result = " << x << endl;
    assert(x >= a && x <= b);
  }
  return 0;
}
