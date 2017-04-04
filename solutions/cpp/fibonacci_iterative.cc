// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;

// @include
int Fibonacci(int n) {
  if (n <= 1) {
    return n;
  }

  int f_minus_2 = 0, f_minus_1 = 1;
  for (int i = 2; i <= n; ++i) {
    int f = f_minus_2 + f_minus_1;
    f_minus_2 = f_minus_1;
    f_minus_1 = f;
  }
  return f_minus_1;
}
// @exclude

void SmallTest() {
  assert(Fibonacci(10) == 55);
  assert(Fibonacci(0) == 0);
  assert(Fibonacci(1) == 1);
  assert(Fibonacci(16) == 987);
  assert(Fibonacci(40) == 102334155);
}

int main(int argc, char* argv[]) {
  SmallTest();
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> n_dis(0, 40);
    n = n_dis(gen);
  }
  cout << "F(" << n << ") = " << Fibonacci(n) << endl;
  return 0;
}
