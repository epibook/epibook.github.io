import sys
import random


# @include
def gray_code(num_bits):
    if num_bits == 0:
        return [0]

    # These implicitly begin with 0 at bit-index (num_bits - 1).
    gray_code_num_bits_minus_1 = gray_code(num_bits - 1)
    # Now, add a 1 at bit-index (num_bits - 1) to all entries in
    # gray_code_num_bits_minus_1.
    leading_bit_one = 1 << (num_bits - 1)
    # Process in reverse order to achieve reflection of
    # gray_code_num_bits_minus_1.
    return gray_code_num_bits_minus_1 + [
        leading_bit_one | i for i in reversed(gray_code_num_bits_minus_1)
    ]


# Pythonic solution uses list comprehension.
def gray_code_pythonic(num_bits):
    result = [0]
    for i in range(num_bits):
        result += [x + 2**i for x in reversed(result)]
    return result
# @exclude


def small_test():
    vec = gray_code(3)
    expected = [0, 1, 3, 2, 6, 7, 5, 4]
    assert vec == expected == gray_code_pythonic(3)


def check_ans(A):
    for i in range(len(A)):
        num_differ_bits = 0
        prev = A[i]
        now = A[(i + 1) % len(A)]
        prev_s = '{:010b}'.format(prev)
        now_s = '{:010b}'.format(now)
        for i in range(10):
            if prev_s[i] != now_s[i]:
                num_differ_bits += 1
        assert num_differ_bits == 1


def main():
    small_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 9)
    print('n =', n)
    vec = gray_code(n)
    print(*vec)
    check_ans(vec)
    check_ans(gray_code_pythonic(n))


if __name__ == '__main__':
    main()
