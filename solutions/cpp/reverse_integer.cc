// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::stoi;
using std::stol;
using std::string;
using std::to_string;
using std::uniform_int_distribution;

// @include
long Reverse(int x) {
  long result = 0, x_remaining = abs(x);
  while (x_remaining) {
    result = result * 10 + x_remaining % 10;
    x_remaining /= 10;
  }
  return x < 0 ? -result : result;
}
// @exclude

long CheckAns(int x) {
  string s = to_string(x);
  if (s.front() == '-') {
    reverse(s.begin() + 1, s.end());
  } else {
    reverse(s.begin(), s.end());
  }
  return stol(s);
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
    cout << "n = " << n << ", reversed n = " << Reverse(n) << endl;
    assert(CheckAns(n) == Reverse(n));
  } else {
    uniform_int_distribution<int> dis(numeric_limits<int>::min(),
                                      numeric_limits<int>::max());
    for (int times = 0; times < 1000; ++times) {
      n = dis(gen);
      cout << "n = " << n << ", reversed n = " << Reverse(n) << endl;
      assert(CheckAns(n) == Reverse(n));
    }
  }
  return 0;
}
