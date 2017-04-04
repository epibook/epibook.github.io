# Elias_coding.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random


# @include
def encode(A):
    ret = []
    for a in A:
        binary = trans_int_to_binary(a)
        binary = '0' * (len(binary) - 1) + binary  # prepend 0s.
        ret.append(binary)
    return ''.join(ret)


def trans_int_to_binary(decimal):
    ret = []
    while decimal:
        ret.append(chr(ord('0') + (decimal & 1)))
        decimal >>= 1
    return ''.join(reversed(ret))


# @exclude


# Pythonic solution
def trans_int_to_binary_pythonic(decimal):
    return bin(decimal)[2:]


# @include


def decode(s):
    ret = []
    idx = 0
    while idx < len(s):
        # Count the number of consecutive 0s.
        zero_idx = idx
        while zero_idx < len(s) and s[zero_idx] == '0':
            zero_idx += 1

        length = zero_idx - idx + 1
        ret.append(trans_binary_to_int(s[zero_idx:zero_idx + length]))
        idx = zero_idx + length
    return ret


def trans_binary_to_int(binary):
    ret = 0
    for c in binary:
        ret = (ret * 2) + ord(c) - ord('0')
    return ret


# @exclude


# Pythonic solution
def trans_binary_to_int_pythonic(binary):
    return int(binary, 2)


def main():
    if len(sys.argv) == 1:
        A = [
            random.randint(1, (1 << random.randint(1, 31)) - 1)
            for i in range(random.randint(1, 10000))
        ]
    else:
        A = [
            random.randint(1, (1 << random.randint(1, 31)) - 1)
            for i in range(int(sys.argv[1]))
        ]

    ret = encode(A)
    print(ret)

    res = decode(ret)
    assert A == res


if __name__ == '__main__':
    main()
