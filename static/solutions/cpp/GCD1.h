// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_GCD1_H_
#define SOLUTIONS_GCD1_H_

namespace GCD1 {

// @include
long long GCD(long long x, long long y) {
  if (x == y) {
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

}  // namespace GCD1

#endif  // SOLUTIONS_GCD1_H_
