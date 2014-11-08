// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <iostream>
#include <limits>
#include <random>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_real_distribution;

int Compare(double a, double b);

// @include
double SquareRoot(double x) {
  // Decides the search range according to x.
  double left, right;
  if (Compare(x, 1.0) < 0) {  // x < 1.0.
    left = x, right = 1.0;
  } else {  // x >= 1.0.
    left = 1.0, right = x;
  }

  // Keeps searching if left < right.
  while (Compare(left, right) == -1) {
    double mid = left + 0.5 * (right - left);
    double square_m = mid * mid;
    if (Compare(square_m, x) == 0) {
      return mid;
    } else if (Compare(square_m, x) == 1) {
      right = mid;
    } else {
      left = mid;
    }
  }
  return left;
}

// 0 means equal, -1 means smaller, and 1 means larger.
int Compare(double a, double b) {
  // Uses normalization for precision problem.
  double diff = (a - b) / b;
  return diff < -numeric_limits<double>::epsilon()
             ? -1
             : diff > numeric_limits<double>::epsilon();
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 100000; ++times) {
    double x;
    if (argc == 2) {
      x = atof(argv[1]);
    } else {
      uniform_real_distribution<double> dis(0.0, 100000000.0);
      x = dis(gen);
    }
    double res[2];
    cout << "x is " << x << endl;
    cout << (res[0] = SquareRoot(x)) << ' ' << (res[1] = sqrt(x)) << endl;
    assert(Compare(res[0], res[1]) == 0);
  }
  return 0;
}
