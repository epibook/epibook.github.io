// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_PARITY2_H_
#define SOLUTIONS_PARITY2_H_

namespace Parity2 {

// @include
short Parity(unsigned long x) {
  short result = 0;
  while (x) {
    result ^= 1;
    x &= (x - 1);  // Drops the lowest set bit of x.
  }
  return result;
}
// @exclude

}  // namespace Parity2

#endif  // SOLUTIONS_PARITY2_H_
