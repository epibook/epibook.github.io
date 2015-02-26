# Parity2.h 848813e190b1b85a8e75107fe8513c3be38ad1a9
# @include
def parity(x):
    result = 0
    while x != 0:
        result ^= 1
        x &= x - 1  # Drops the lowest set bit of x.
    return result
# @exclude
