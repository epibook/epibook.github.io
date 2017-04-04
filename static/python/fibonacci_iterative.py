import sys
import random


# @include
def fibonacci(n):
    if n <= 1:
        return n

    f_minus_2, f_minus_1 = 0, 1
    for _ in range(1, n):
        f = f_minus_2 + f_minus_1
        f_minus_2, f_minus_1 = f_minus_1, f
    return f_minus_1
# @exclude


def small_test():
    assert fibonacci(10) == 55
    assert fibonacci(0) == 0
    assert fibonacci(1) == 1
    assert fibonacci(16) == 987
    assert fibonacci(40) == 102334155


def main():
    small_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(0, 40)
    print('F(%d) = %d' % (n, fibonacci(n)))


if __name__ == '__main__':
    main()
