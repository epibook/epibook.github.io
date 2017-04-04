import sys
import random
import collections


# @include
def calculate_trapping_water(heights):
    # Finds the index with maximum height.
    max_h = heights.index(max(heights))

    # Assume heights[-1] is maximum height.
    def trapping_water_till_end(heights):
        partial_sum, highest_level_seen = 0, float('-inf')
        for h in heights:
            if h >= highest_level_seen:
                highest_level_seen = h
            else:
                partial_sum += highest_level_seen - h
        return partial_sum

    return (trapping_water_till_end(heights[:max_h]) +
            trapping_water_till_end(reversed(heights[max_h + 1:])))
# @exclude

# Stack approach, O(n) time, O(n) space
HeightBound = collections.namedtuple('HeightBound',
                                     ('left_bound', 'right_bound'))


def check_answer(A):
    s = []
    sum_ = 0
    for i, a in enumerate(A):
        while s and s[-1].right_bound <= a:
            bottom = s[-1].right_bound
            del s[-1]
            if not s:
                break
            top = min(s[-1].right_bound, a)
            sum_ += (top - bottom) * (i - s[-1].left_bound - 1)
        s.append(HeightBound(i, a))
    return sum_


def small_test():
    A = (1, 0, 3, 2, 5, 0, 1)
    assert calculate_trapping_water(A) == 3
    A = (1, 2, 1, 3, 4, 4, 5, 6, 2, 1, 3, 1, 3, 2, 1, 2, 4, 1)
    assert calculate_trapping_water(A) == 18


def main():
    small_test()
    for _ in range(10000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
        A = [random.randint(0, 10) for _ in range(n)]
        print(*A)
        print(check_answer(A), calculate_trapping_water(A))
        assert check_answer(A) == calculate_trapping_water(A)


if __name__ == '__main__':
    main()
