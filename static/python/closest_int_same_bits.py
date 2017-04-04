import sys
import random


# @include
def closest_int_same_bit_count(x):
    NUM_UNSIGNED_BITS = 64
    for i in range(NUM_UNSIGNED_BITS - 1):
        if ((x >> i) & 1) != ((x >> (i + 1)) & 1):
            x ^= (1 << i) | (1 << (i + 1))  # Swaps bit-i and bit-(i + 1).
            return x

    # Raise error if all bits of x are 0 or 1.
    raise ValueError('All bits are 0 or 1')
# @exclude


def count_bits_set_to1(x):
    count = 0
    while x:
        x &= (x - 1)
        count += 1
    return count


def small_test():
    assert closest_int_same_bit_count(6) == 5
    assert closest_int_same_bit_count(7) == 11
    assert closest_int_same_bit_count(2) == 1
    assert closest_int_same_bit_count(32) == 16
    assert closest_int_same_bit_count(2**64 - 2) == 2**64 - 3

    try:
        closest_int_same_bit_count(2**64 - 1)
        assert False
    except ValueError as e:
        print(e)
    try:
        closest_int_same_bit_count(0)
        assert False
    except ValueError as e:
        print(e)


def main():
    small_test()
    x = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(0,
                                                                   sys.maxsize)
    try:
        res = closest_int_same_bit_count(x)
        print(x, res)
        assert count_bits_set_to1(x) == count_bits_set_to1(res)
    except ValueError as e:
        print(x, e)


if __name__ == '__main__':
    main()
