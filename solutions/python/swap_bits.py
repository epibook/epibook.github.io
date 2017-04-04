import sys
import random


# @include
def swap_bits(x, i, j):
    # Extract the i-th and j-th bits, and see if they differ.
    if (x >> i) & 1 != (x >> j) & 1:
        # i-th and j-th bits differ. We will swap them by flipping their values.
        # Select the bits to flip with bit_mask. Since x^1 = 0 when x = 1 and 1
        # when x = 0, we can perform the flip XOR.
        bit_mask = (1 << i) | (1 << j)
        x ^= bit_mask
    return x
# @exclude


def simple_test():
    assert swap_bits(0b101111, 1, 4) == 0b111101
    assert swap_bits(0b11100, 0, 2) == 0b11001


def main():
    simple_test()
    if len(sys.argv) == 4:
        x = int(sys.argv[1])
        i = int(sys.argv[2])
        j = int(sys.argv[3])
    else:
        x = random.randint(0, sys.maxsize)
        i = random.randrange(sys.maxsize.bit_length())
        j = random.randrange(sys.maxsize.bit_length())
    print('x = %#x, i = %d, j = %d' % (x, i, j))
    print('%#x' % swap_bits(x, i, j))


if __name__ == '__main__':
    main()
