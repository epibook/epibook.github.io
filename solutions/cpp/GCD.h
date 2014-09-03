// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_GCD_H_
#define SOLUTIONS_GCD_H_

// @include
long long GCD(long long x, long long y) {
  if (x == 0) {
    return y;
  } else if (y == 0) {
    return x;
  } else if (!(x & 1) && !(y & 1)) {  // x and y are even.
    return GCD(x >> 1, y >> 1) << 1;
  } else if (!(x & 1) && y & 1) {  // x is even, and y is odd.
    return GCD(x >> 1, y);
  } else if (x & 1 && !(y & 1)) {  // x is odd, and y is even.
    return GCD(x, y >> 1);
  } else if (x > y) {  // Both x and y are odd, and x > y.
    return GCD(x - y, y);
  }
  return GCD(x, y - x);  // Both x and y are odd, and x <= y.
}
// @exclude

long long AnotherGCD(long long a, long long b) {
  if (b) {
    while ((a %= b) && (b %= a));
  }
  return a + b;
}
#endif  // SOLUTIONS_GCD_H_
