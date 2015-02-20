// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_PARITY4_H_
#define SOLUTIONS_PARITY4_H_

namespace Parity4 {

short FourBitParityLookup(int x);

// @include
short Parity(unsigned long x) {
  x ^= x >> 32;
  x ^= x >> 16;
  x ^= x >> 8;
  x ^= x >> 4;
  x &= 0xf; // Only wants the last 4 bits of x.
  // Return the LSB, which is the parity.
  return FourBitParityLookup(x);
}

// The LSB of kFourBitParityLookupTable is the parity of 0,
// next bit is parity of 1, followed by the parity of 2, etc.

const int kFourBitParityLookupTable = 0x6996; // = 0b0110100110010110.

short FourBitParityLookup(int x) {
  return (kFourBitParityLookupTable >> x) & 1;
}
// @exclude

}  // namespace Parity4

#endif  // SOLUTIONS_PARITY4_H_
