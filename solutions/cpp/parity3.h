// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_PARITY3_H_
#define SOLUTIONS_CPP_PARITY3_H_

#include <array>

using std::array;

namespace Parity3 {

array<short, 1 << 16> BuildTable() {
  array<short, 1 << 16> result;
  for (int i = 0; i < (1 << 16); ++i) {
    result[i] = Parity1::Parity(i);
  }
  return result;
}

static array<short, 1 << 16> precomputed_parity = BuildTable();

// @include
short Parity(unsigned long x) {
  const int kMaskSize = 16;
  const int kBitMask = 0xFFFF;
  return precomputed_parity[x >> (3 * kMaskSize)] ^
         precomputed_parity[(x >> (2 * kMaskSize)) & kBitMask] ^
         precomputed_parity[(x >> kMaskSize) & kBitMask] ^
         precomputed_parity[x & kBitMask];
}
// @exclude

}  // namespace Parity3

#endif  // SOLUTIONS_CPP_PARITY3_H_
