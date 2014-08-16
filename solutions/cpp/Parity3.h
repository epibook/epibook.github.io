// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_PARITY3_H_
#define SOLUTIONS_PARITY3_H_

static bool is_initialized = false;

namespace Parity3 {

short precomputed_parity[1 << 16];

void BuildTable() {
  if (!is_initialized) {
    for (int i = 0; i < (1 << 16); ++i) {
      precomputed_parity[i] = Parity1::Parity(i);
    }
    is_initialized = true;
  }
}

// @include
short Parity(unsigned long x) {
  const int kWordSize = 16;
  const int kBitMask = 0xFFFF;
  return precomputed_parity[x >> (3 * kWordSize)] ^
         precomputed_parity[(x >> (2 * kWordSize)) & kBitMask] ^
         precomputed_parity[(x >> kWordSize) & kBitMask] ^
         precomputed_parity[x & kBitMask];
}
// @exclude

}  // namespace Parity3

#endif  // SOLUTIONS_PARITY3_H_
