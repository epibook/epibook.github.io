import functools
import sys
import string
import random


def rand_string(length):
    return ''.join(random.choice(string.ascii_letters) for _ in range(length))


# @include
def string_hash(s, modulus):
    MULT = 997
    return functools.reduce(lambda v, c: (v * MULT + ord(c)) % modulus, s, 0)
# @exclude


def main():
    s = sys.argv[1] if len(sys.argv) == 2 else rand_string(
        random.randint(1, 20))
    print('string =', s)
    print(string_hash(s, 1 << 16))


if __name__ == '__main__':
    main()
