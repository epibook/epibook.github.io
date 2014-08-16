// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::to_string;
using std::uniform_int_distribution;
using std::vector;

// @include
bool IsPalindrome(int x) {
  if (x < 0) {
    return false;
  }

  const int kNumDigits = static_cast<int>(floor(log10(x))) + 1;
  int x_remaining = x, msd_shift = static_cast<int>(pow(10, kNumDigits - 1));
  for (int i = 0; i < (kNumDigits / 2); ++i) {
    if (x / msd_shift != x_remaining % 10) {
      return false;
    }
    x %= msd_shift;
    msd_shift /= 10, x_remaining /= 10;
  }
  return true;
}
// @exclude

bool CheckAns(int x) {
  auto s = to_string(x);
  for (size_t i = 0, j = s.size() - 1; i < j; ++i, --j) {
    if (s[i] != s[j]) {
      return false;
    }
  }
  return true;
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  int x;
  if (argc == 2) {
    x = stoi(argv[1]);
    cout << x << " " << std::boolalpha << IsPalindrome(x) << endl;
    assert(CheckAns(x) == IsPalindrome(x));
  } else {
    uniform_int_distribution<int> dis(-99999, 99999);
    for (int times = 0; times < 1000; ++times) {
      x = dis(gen);
      assert(CheckAns(x) == IsPalindrome(x));
    }
  }
  return 0;
}
