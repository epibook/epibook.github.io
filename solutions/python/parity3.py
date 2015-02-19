import parity1

_PRECOMPUTED_PARITY = [parity1.parity(i) for i in range(1 << 16)]

# @include
def parity(x):
    word_size = 16
    bit_mask = 0xFFFF
    return (_PRECOMPUTED_PARITY[x >> (3 * word_size)] ^
            _PRECOMPUTED_PARITY[(x >> (2 * word_size)) & bit_mask] ^
            _PRECOMPUTED_PARITY[(x >> word_size) & bit_mask] ^
            _PRECOMPUTED_PARITY[x & bit_mask])
# @exclude
