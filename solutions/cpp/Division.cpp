// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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
using std::stoul;
using std::uniform_int_distribution;

unsigned DivideXYBsearch(unsigned x, unsigned y) {
  if (x < y) {
    return 0;
  }

  int power_left = 0;
  int power_right = sizeof(unsigned) << 3;
  int power_mid = -1;
  while (power_left < power_right) {
    int tmp = power_mid;
    power_mid = power_left + ((power_right - power_left) / 2);
    if (tmp == power_mid) {
      break;
    }
    unsigned yshift = y << power_mid;
    if ((yshift >> power_mid) != y) {
      // yshift overflowed, use a smaller shift.
      power_right = power_mid;
      continue;
    }
    if ((y << power_mid) > x) {
      power_right = power_mid;
    } else if ((y << power_mid) < x) {
      power_left = power_mid;
    } else {
      return (1U << power_mid);
    }
  }
  unsigned part = 1U << power_left;
  return part | DivideXYBsearch(x - (y << power_left), y);
}

// @include
unsigned DivideXY(unsigned x, unsigned y) {
  unsigned res = 0;
  while (x >= y) {
    int power = 1;
    // Checks (y << power) >= (y << (power - 1)) to prevent potential
    // overflow of unsigned.
    while ((y << power) >= (y << (power - 1)) && (y << power) <= x) {
      ++power;
    }

    res += 1U << (power - 1);
    x -= y << (power - 1);
  }
  return res;
}
// @exclude

void SimpleTest() {
  assert(DivideXY(64, 1) == 64);
  assert(DivideXY(64, 2) == 32);
  assert(DivideXY(64, 3) == 21);
  assert(DivideXY(64, 4) == 16);
  assert(DivideXY(64, 5) == 12);
  assert(DivideXY(65, 2) == 32);
  assert(DivideXY(2600540749, 2590366779) == 1);
  assert(DivideXYBsearch(4u, 2u));
  assert(DivideXYBsearch(64, 1) == 64);
  assert(DivideXYBsearch(64, 2) == 32);
  assert(DivideXYBsearch(64, 3) == 21);
  assert(DivideXYBsearch(64, 4) == 16);
  assert(DivideXYBsearch(64, 5) == 12);
  assert(DivideXYBsearch(65, 2) == 32);
  assert(DivideXYBsearch(9444, 4714) == 2);
  assert(DivideXYBsearch(8186, 19) == 430);
  assert(DivideXYBsearch(8186, 19) == 430);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  if (argc == 3) {
    unsigned x = static_cast<size_t>(stoul(argv[1]));
    unsigned y = static_cast<size_t>(stoul(argv[2]));
    assert(x / y == DivideXY(x, y));
    assert(x / y == DivideXYBsearch(x, y));
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<size_t> dis(0, numeric_limits<size_t>::max());
    for (int times = 0; times < 100000; ++times) {
      unsigned x = dis(gen), y = dis(gen);
      y = (y == 0) ? 1 : y;  // ensure no divide by 0.
      cout << "times = " << times << ", x = " << x << ", y = " << y << endl;
      cout << "first = " << x / y << ", second = " << DivideXY(x, y) << endl;
      assert(x / y == DivideXY(x, y));
      assert(x / y == DivideXYBsearch(x, y));
    }
  }
  return 0;
}
