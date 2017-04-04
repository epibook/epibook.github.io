// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_PARITY1_H_
#define SOLUTIONS_PARITY1_H_

namespace Parity1 {

// @include
short Parity(unsigned long x) {
  short result = 0;
  while (x) {
    result ^= (x & 1);
    x >>= 1;
  }
  return result;
}
// @exclude

}  // namespace Parity1

#endif  // SOLUTIONS_PARITY1_H_
