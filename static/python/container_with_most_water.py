import itertools
import sys
import random


# @include
def get_max_trapped_water(heights):
    i, j, max_water = 0, len(heights) - 1, 0
    while i < j:
        width = j - i
        max_water = max(max_water, width * min(heights[i], heights[j]))
        if heights[i] > heights[j]:
            j -= 1
        else:  # heights[i] <= heights[j].
            i += 1
    return max_water
# @exclude


# O(n^2) checking answer.
def check_ans(heights):
    return max(
        min(heights[i], heights[j]) * (j - i)
        for i, j in itertools.combinations(range(len(heights)), 2) or [0])


def small_test():
    A = (1, 2, 1, 3, 4, 4, 5, 6, 2, 1, 3, 1, 3, 2, 1, 2, 4, 1)
    assert 48 == get_max_trapped_water(A)


def main():
    small_test()
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(2, 10000)
        heights = [random.randint(1, 1000) for _ in range(n)]
        print(get_max_trapped_water(heights))
        assert get_max_trapped_water(heights) == check_ans(heights)


if __name__ == '__main__':
    main()
