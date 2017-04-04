# Parity3.h 848813e190b1b85a8e75107fe8513c3be38ad1a9
import parity1

PRECOMPUTED_PARITY = [parity1.parity(i) for i in range(1 << 16)]


# @include
def parity(x):
    MASK_SIZE = 16
    BIT_MASK = 0xFFFF
    return (PRECOMPUTED_PARITY[x >> (3 * MASK_SIZE)] ^
            PRECOMPUTED_PARITY[(x >> (2 * MASK_SIZE)) & BIT_MASK] ^
            PRECOMPUTED_PARITY[(x >> MASK_SIZE) & BIT_MASK] ^
            PRECOMPUTED_PARITY[x & BIT_MASK])
# @exclude
