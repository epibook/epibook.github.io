import collections
import operator
import sys
import random

# @include
Interval = collections.namedtuple('Interval', ('left', 'right'))


def find_minimum_visits(intervals):
    if not intervals:
        return []

    # Sort intervals based on the right endpoints.
    intervals.sort(key=operator.attrgetter('right'))
    visits = []
    last_visit_time = float('-inf')
    for interval in intervals:
        if interval.left > last_visit_time:
            # The current right endpoint, last_visit_time, will not cover any
            # more intervals.
            last_visit_time = interval.right
            visits.append(last_visit_time)
    return visits
# @exclude


# O(n^2) checking solution
def check_ans(intervals, ans):
    is_visited = [False] * len(intervals)
    for a in ans:
        for i, interval in enumerate(intervals):
            if interval.left <= a <= interval.right:
                is_visited[i] = True
    assert all(is_visited)


def simple_test():
    intervals = [
        Interval(1, 4), Interval(2, 8), Interval(3, 6), Interval(3, 5),
        Interval(7, 10), Interval(9, 11)
    ]
    assert find_minimum_visits(intervals) == [4, 10]
    intervals = [
        Interval(1, 2), Interval(2, 3), Interval(3, 4), Interval(4, 5),
        Interval(5, 6), Interval(6, 7)
    ]
    assert find_minimum_visits(intervals) == [2, 4, 6]
    intervals = [Interval(1, 5), Interval(2, 3), Interval(3, 4)]
    assert find_minimum_visits(intervals) == [3]


def main():
    simple_test()
    for times in range(1000):
        print('Test', times)
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        A = []
        for i in range(n):
            left = random.randrange(10000)
            right = random.randint(left, left + 100)
            A.append(Interval(left, right))
        ans = find_minimum_visits(A)
        check_ans(A, ans)


if __name__ == '__main__':
    main()
