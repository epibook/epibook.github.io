import sys
import math
import random


# @include
def square_root(k):
    left, right = 0, k
    # Candidate interval [left, right] where everything before left has square
    # <= k, everything after right has square > k.
    while left <= right:
        mid = (left + right) // 2
        mid_squared = mid * mid
        if mid_squared <= k:
            left = mid + 1
        else:
            right = mid - 1
    return left - 1
# @exclude


def simple_test():
    assert square_root(0) == 0
    assert square_root(1) == 1
    assert square_root(2) == 1
    assert square_root(3) == 1
    assert square_root(4) == 2
    assert square_root(7) == 2
    assert square_root(121) == 11
    assert square_root(64) == 8
    assert square_root(300) == 17
    assert square_root(2147483647) == 46340


def main():
    simple_test()
    if len(sys.argv) == 2:
        x = int(sys.argv[1])
    else:
        for _ in range(100000):
            x = random.randint(0, 2147483647)
            result = [square_root(x), int(math.sqrt(x))]
            print('x is', x)
            print(result[0], result[1])
            assert result[0] == result[1]


if __name__ == '__main__':
    main()
