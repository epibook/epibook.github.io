import sys
import random
import math


# @include
def is_palindrome_number(x):
    if x < 0:
        return False

    num_digits = math.floor(math.log10(x)) + 1
    msd_mask = 10**(num_digits - 1)
    for i in range(num_digits // 2):
        if x // msd_mask != x % 10:
            return False
        x %= msd_mask  # Remove the most significant digit of x.
        x //= 10  # Remove the least significant digit of x.
        msd_mask //= 100
    return True
# @exclude


def check_ans(x):
    s = str(x)
    i, j = 0, len(s) - 1
    while i < j:
        if s[i] != s[j]:
            return False
        i += 1
        j -= 1
    return True


def main():
    if len(sys.argv) == 2:
        x = int(sys.argv[1])
        print(x, is_palindrome_number(x))
        assert check_ans(x) == is_palindrome_number(x)
    else:
        for _ in range(1000):
            x = random.randint(-99999, 99999)
            assert check_ans(x) == is_palindrome_number(x)


if __name__ == '__main__':
    main()
