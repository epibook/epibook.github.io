import sys
import random


# @include
def count_bits(x):
    num_bits = 0
    while x:
        num_bits += x & 1
        x >>= 1
    return num_bits
# @exclude


def count_bits_pythonic(x):
    return bin(x).count('1')


def main():
    if len(sys.argv) == 4:
        x = int(sys.argv[1])
        print('x = %d, = %d' % (x, count_bits(x)))
    else:
        for _ in range(1000):
            x = random.randint(0, sys.maxsize)
            assert all(count_bits(x) == bin(x).count('1') for _ in range(1000))


if __name__ == '__main__':
    main()
