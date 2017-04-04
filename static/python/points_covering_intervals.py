import bintrees
import collections
import sys
import random


# @include
Interval = collections.namedtuple('Interval', ('left', 'right'))


def find_minimum_visits(intervals):
    L = bintrees.RBTree([((i.left, i.right), i) for i in intervals])
    R = bintrees.RBTree([((i.right, i.left), i) for i in intervals])

    S = []
    while L and R:
        b = R.min_item()[1].right
        S.append(b)

        # Removes the intervals which intersect with R[0]
        while L and L.min_item()[1].left <= b:
            interval = L.pop_min()[1]
            R.discard((interval.right, interval.left))
    return S
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
    ans = find_minimum_visits(intervals)
    assert ans == [4, 10]
    intervals = [
        Interval(1, 2), Interval(2, 3), Interval(3, 4), Interval(4, 5),
        Interval(5, 6), Interval(6, 7)
    ]
    ans = find_minimum_visits(intervals)
    assert ans == [2, 4, 6]


def main():
    simple_test()
    for times in range(1000):
        print('Test', times)
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        A = []
        for _ in range(n):
            left = random.randrange(10000)
            right = random.randint(left + 1, left + 100)
            A.append(Interval(left, right))
        ans = find_minimum_visits(A)
        check_ans(A, ans)


if __name__ == '__main__':
    main()
