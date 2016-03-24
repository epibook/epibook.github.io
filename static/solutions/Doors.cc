// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <deque>
#include <random>

using std::default_random_engine;
using std::deque;
using std::random_device;
using std::uniform_int_distribution;

// @include
bool IsDoorOpen(int i) {
  double sqrt_i = sqrt(i);
  int floor_sqrt_i = floor(sqrt_i);
  return floor_sqrt_i * floor_sqrt_i == i;
}
// @exclude

void CheckAnswer(int n) {
  deque<bool> doors(n + 1, false);  // false means closed door.
  for (int i = 1; i <= n; ++i) {
    int start = 0;
    while (start + i <= n) {
      start += i;
      doors[start] = doors[start] ? false : true;
    }
  }

  for (int i = 1; i <= n; ++i) {
    assert(IsDoorOpen(i) == doors[i]);
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    n = dis(gen);
  }
  CheckAnswer(n);
  return 0;
}
