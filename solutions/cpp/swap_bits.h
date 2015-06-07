// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_SWAP_BITS_H_
#define SOLUTIONS_SWAP_BITS_H_

// @include
long SwapBits(long x, int i, int j) {
  // Extract the i-th and j-th bits, and see if they differ.
  if (((x >> i) & 1) != ((x >> j) & 1)) {
    // i-th and j-th bits differ. We will swap them by flipping their values.
    // Select the bits to flip with bitmask; flip them using an XOR.
    unsigned long bit_mask = (1L << i) | (1L << j);
    x ^= bit_mask;
  }
  return x;
}
// @exclude
#endif  // SOLUTIONS_SWAP_BITS_H_
