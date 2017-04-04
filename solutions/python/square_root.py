import sys
import math
import random

# @include


def square_root(x):
    # Decides the search range according to x's value relative to 1.0.
    left, right = (x, 1.0) if x < 1.0 else (1.0, x)

    # Keeps searching as long as left != right.
    while not math.isclose(left, right):
        mid = 0.5 * (left + right)
        mid_squared = mid * mid
        if mid_squared > x:
            right = mid
        else:
            left = mid
    return left
# @exclude


def simple_test():
    assert math.isclose(square_root(1.0), math.sqrt(1.0))
    assert math.isclose(square_root(2.0), math.sqrt(2.0))
    assert math.isclose(square_root(0.001), math.sqrt(0.001))
    assert math.isclose(square_root(0.5), math.sqrt(0.5))
    assert math.isclose(square_root(100000000.001), math.sqrt(100000000.001))
    assert math.isclose(square_root(1024.0), math.sqrt(1024.0))


def main():
    simple_test()
    for _ in range(100000):
        x = float(sys.argv[1]) if len(sys.argv) == 2 else random.uniform(
            0.0, 100000000.0)
        res = [square_root(x), math.sqrt(x)]
        print('x is', x)
        print(res[0], res[1])
        assert math.isclose(res[0], res[1])


if __name__ == '__main__':
    main()
