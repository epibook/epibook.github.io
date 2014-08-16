// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_SWAP_BITS_H_
#define SOLUTIONS_SWAP_BITS_H_

// @include
long SwapBits(long x, int i, int j) {
  // Extract the i-th and j-th bits, and see if they differ.
  if (((x >> i) & 1) != ((x >> j) & 1)) {
    // Swap i-th and j-th bits by flipping them.
    // Select and flip bits by using a bit mask and XOR.
    x ^= (1L << i) | (1L << j);
  }
  return x;
}
// @exclude
#endif  // SOLUTIONS_SWAP_BITS_H_
