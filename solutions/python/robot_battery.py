# Robot_battery.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random
import itertools
import operator


# @include
def find_battery_capacity(h):
    min_height = h[0]
    capacity = 0
    for height in h:
        capacity = max(capacity, height - min_height)
        min_height = min(min_height, height)
    return capacity
# @exclude


def check_ans(h):
    """O(n^2) checking answer."""
    cap = 0
    for i in range(1, len(h)):
        for j in range(i):
            cap = max(cap, h[i] - h[j])
    return cap


def main():
    for _ in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 1000)
        a = [random.randint(0, sys.maxsize) for i in range(n)]
        print(find_battery_capacity(a))
        assert check_ans(a) == find_battery_capacity(a)


if __name__ == '__main__':
    main()
