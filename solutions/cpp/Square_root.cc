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

// @include
typedef enum { SMALLER, EQUAL, LARGER } Ordering;

// @exclude
Ordering Compare(double a, double b);
// @include
double SquareRoot(double x) {
  // Decides the search range according to x's value relative to 1.0.
  double left, right;
  if (x < 1.0) {
    left = x, right = 1.0;
  } else {  // x >= 1.0.
    left = 1.0, right = x;
  }

  // Keeps searching as long as left < right, within tolerance.
  while (Compare(left, right) == SMALLER) {
    double mid = left + 0.5 * (right - left);
    double mid_squared = mid * mid;
    if (Compare(mid_squared, x) == EQUAL) {
      return mid;
    } else if (Compare(mid_squared, x) == LARGER) {
      right = mid;
    } else {
      left = mid;
    }
  }
  return left;
}

Ordering Compare(double a, double b) {
  // Uses normalization for precision problem.
  double diff = (a - b) / b;
  return diff < -numeric_limits<double>::epsilon()
             ? SMALLER
             : diff > numeric_limits<double>::epsilon() ? LARGER : EQUAL;
}
// @exclude

static void SimpleTest() {
  assert(Compare(SquareRoot(1.0), sqrt(1.0) == EQUAL));
  assert(Compare(SquareRoot(2.0), sqrt(2.0) == EQUAL));
  assert(Compare(SquareRoot(0.001), sqrt(0.001) == EQUAL));
  assert(Compare(SquareRoot(0.5), sqrt(0.5) == EQUAL));
  assert(Compare(SquareRoot(100000000.001), sqrt(100000000.001) == EQUAL));
  assert(Compare(SquareRoot(1024.0), sqrt(1024.0) == EQUAL));
}

int main(int argc, char* argv[]) {
  SimpleTest();
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
    assert(Compare(res[0], res[1]) == EQUAL);
  }
  return 0;
}
