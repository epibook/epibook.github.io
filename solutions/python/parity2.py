# @include
def parity(x):
    result = 0
    while x:
        result ^= 1
        x &= x - 1  # Drops the lowest set bit of x.
    return result
# @exclude
