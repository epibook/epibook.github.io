import sys
import random


# @include
def smallest_nonconstructible_value(A):
    max_constructible_value = 0
    for a in sorted(A):
        if a > max_constructible_value + 1:
            break
        max_constructible_value += a
    return max_constructible_value + 1
# @exclude

import functools


def smallest_nonconstructible_value_pythonic(A):
    return functools.reduce(lambda max_val, a: max_val +
                            (0 if a > max_val + 1 else a), sorted(A), 0) + 1


def small_test():
    A = [1, 2, 3, 4]
    assert 11 == smallest_nonconstructible_value(
        A) == smallest_nonconstructible_value_pythonic(A)
    A = [1, 2, 2, 4]
    assert 10 == smallest_nonconstructible_value(
        A) == smallest_nonconstructible_value_pythonic(A)
    A = [2, 3, 4, 5]
    assert 1 == smallest_nonconstructible_value(
        A) == smallest_nonconstructible_value_pythonic(A)
    A = [1, 3, 2, 1]
    assert 8 == smallest_nonconstructible_value(
        A) == smallest_nonconstructible_value_pythonic(A)
    A = [1, 3, 2, 5]
    assert 12 == smallest_nonconstructible_value(
        A) == smallest_nonconstructible_value_pythonic(A)
    A = [1, 3, 2, 6]
    assert 13 == smallest_nonconstructible_value(
        A) == smallest_nonconstructible_value_pythonic(A)
    A = [1, 3, 2, 7]
    assert 14 == smallest_nonconstructible_value(
        A) == smallest_nonconstructible_value_pythonic(A)
    A = [1, 3, 2, 8]
    assert 7 == smallest_nonconstructible_value(
        A) == smallest_nonconstructible_value_pythonic(A)


def main():
    small_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
    A = [random.randint(1, 1000) for _ in range(n)]
    print(*A)
    ans = smallest_nonconstructible_value(A)
    print(ans)


if __name__ == '__main__':
    main()
