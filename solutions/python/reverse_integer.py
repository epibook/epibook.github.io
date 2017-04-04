import sys
import random


# @include
def reverse(x):
    result, x_remaining = 0, abs(x)
    while x_remaining:
        result = result * 10 + x_remaining % 10
        x_remaining //= 10
    return -result if x < 0 else result
# @exclude


def check_ans(x):
    s = str(x)
    s = '-' + s[:0:-1] if s[0] == '-' else s[::-1]
    return int(s)


def main():
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
        print('n = %d, reversed n = %d' % (n, reverse(n)))
        assert check_ans(n) == reverse(n)
    else:
        for _ in range(1000):
            n = random.randint(-sys.maxsize, sys.maxsize)
            print('n = %d, reversed n = %d' % (n, reverse(n)))
            assert check_ans(n) == reverse(n)


if __name__ == '__main__':
    main()
