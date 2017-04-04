import sys
import random
import swap_bits


def reverse_x(x, n):
    i, j = 0, n
    while i < j:
        x = swap_bits.swap_bits(x, i, j)
        i += 1
        j -= 1
    return x


PRECOMPUTED_REVERSE = [reverse_x(i, 15) for i in range(1 << 16)]


# @include
def reverse_bits(x):
    MASK_SIZE = 16
    BIT_MASK = 0xFFFF
    return (PRECOMPUTED_REVERSE[x & BIT_MASK] << (3 * MASK_SIZE) |
            PRECOMPUTED_REVERSE[(x >> MASK_SIZE) & BIT_MASK] << (2 * MASK_SIZE) |
            PRECOMPUTED_REVERSE[(x >> (2 * MASK_SIZE)) & BIT_MASK] <<
            MASK_SIZE | PRECOMPUTED_REVERSE[(x >> (3 * MASK_SIZE)) & BIT_MASK])
# @exclude


def main():
    if len(sys.argv) == 2:
        x = int(sys.argv[1])
        print('x = %#x, reverse x = %#x' % (x, reverse_bits(x)))
        print('%#x' % reverse_x(x, 63))
        assert reverse_bits(x) == reverse_x(x, 63)
    else:
        for _ in range(1000):
            x = random.randint(0, sys.maxsize)
            print('x = %#x, reverse x = %#x' % (x, reverse_bits(x)))
            assert reverse_bits(x) == reverse_x(x, 63)


if __name__ == '__main__':
    main()
