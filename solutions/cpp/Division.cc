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

// Alternative solution.
unsigned DivideBsearch(unsigned x, unsigned y) {
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
  return part | DivideBsearch(x - (y << power_left), y);
}

// @include
unsigned Divide(unsigned x, unsigned y) {
  unsigned result = 0;
  int power = 32;
  unsigned long long y_power = static_cast<unsigned long long>(y) << power;
  while (x >= y) {
    while (y_power > x) {
      y_power >>= 1;
      --power;
    }

    result += 1U << power;
    x -= y_power;
  }
  return result;
}
// @exclude

void SimpleTest() {
  assert(Divide(64, 1) == 64);
  assert(Divide(64, 2) == 32);
  assert(Divide(64, 3) == 21);
  assert(Divide(64, 4) == 16);
  assert(Divide(64, 5) == 12);
  assert(Divide(65, 2) == 32);
  assert(Divide(2600540749, 2590366779) == 1);
  assert(DivideBsearch(4u, 2u));
  assert(DivideBsearch(64, 1) == 64);
  assert(DivideBsearch(64, 2) == 32);
  assert(DivideBsearch(64, 3) == 21);
  assert(DivideBsearch(64, 4) == 16);
  assert(DivideBsearch(64, 5) == 12);
  assert(DivideBsearch(65, 2) == 32);
  assert(DivideBsearch(9444, 4714) == 2);
  assert(DivideBsearch(8186, 19) == 430);
  assert(DivideBsearch(8186, 19) == 430);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  if (argc == 3) {
    unsigned x = static_cast<size_t>(stoul(argv[1]));
    unsigned y = static_cast<size_t>(stoul(argv[2]));
    assert(x / y == Divide(x, y));
    assert(x / y == DivideBsearch(x, y));
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<size_t> dis(0, numeric_limits<size_t>::max());
    for (int times = 0; times < 100000; ++times) {
      unsigned x = dis(gen), y = dis(gen);
      y = (y == 0) ? 1 : y;  // ensure no divide by 0.
      cout << "times = " << times << ", x = " << x << ", y = " << y << endl;
      cout << "first = " << x / y << ", second = " << Divide(x, y) << endl;
      assert(x / y == Divide(x, y));
      assert(x / y == DivideBsearch(x, y));
    }
  }
  return 0;
}
