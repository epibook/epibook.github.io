# Parity1.h 848813e190b1b85a8e75107fe8513c3be38ad1a9
# @include
def parity(x):
    result = 0
    while x != 0:
        result ^= x & 1
        x >>= 1
    return result
# @exclude
