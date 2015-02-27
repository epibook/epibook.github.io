# Parity4.h 848813e190b1b85a8e75107fe8513c3be38ad1a9
# @include
def parity(x):
    x ^= x >> 32
    x ^= x >> 16
    x ^= x >> 8
    x ^= x >> 4
    x &= 0xf  # Only wants the last 4 bits of x.
    # Return the LSB, which is the parity.
    return _four_bit_parity_lookup(x)

# The LSB of _FOUR_BIT_PARITY_LOOKUP_TABLE is the parity of 0,
# next bit is parity of 1, followed by the parity of 2, etc.
_FOUR_BIT_PARITY_LOOKUP_TABLE = 0b0110100110010110

def _four_bit_parity_lookup(x):
    return (_FOUR_BIT_PARITY_LOOKUP_TABLE >> x) & 1
# @exclude
