// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <iostream>
#include <limits>
#include <random>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::stod;
using std::stoi;
using std::uniform_int_distribution;
using std::uniform_real_distribution;

// 0 means equal, -1 means smaller, and 1 means larger.
int Compare(double a, double b) {
  // Uses normalization for precision problem.
  double diff = (a - b) / b;
  return diff < -1.0e-9 ? -1 : diff > 1.0e-9;
}

// @include
double Power(double x, int y) {
  double result = 1.0;
  long long power = y;
  if (y < 0) {
    power = -power, x = 1.0 / x;
  }
  while (power) {
    if (power & 1) {
      result *= x;
    }
    x *= x, power >>= 1;
  }
  return result;
}
// @exclude

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  double x;
  int y;
  if (argc == 3) {
    x = stod(argv[1]), y = stoi(argv[2]);
    cout << x << "^" << y << ": " << Power(x, y) << ", " << std::pow(x, y)
         << endl;
    assert(!Compare(Power(x, y), std::pow(x, y)));
  } else {
    uniform_real_distribution<> dis_x(0.0, 10.0);
    uniform_int_distribution<> dis_y(-128, 128);
    for (int times = 0; times < 10000; ++times) {
      x = dis_x(gen);
      y = dis_y(gen);
      cout << x << "^" << y << ": " << Power(x, y) << " " << std::pow(x, y)
           << endl;
      assert(!Compare(Power(x, y), std::pow(x, y)));
    }
  }
  return 0;
}
