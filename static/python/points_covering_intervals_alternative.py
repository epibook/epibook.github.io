import sys
import random


# @include
class Interval:

    def __init__(self, left=0, right=0):
        self.left = left
        self.right = right
# @exclude

    def __eq__(self, other):
        return self.left == other.left and self.right == other.right

    def __repr__(self):
        return '(%s, %s)' % (self.left, self.right)

    def __hash__(self):
        return self.left ^ self.right


# @include


class EndPoint:

    def __init__(self, interval, is_left):
        self.ptr = interval
        self.is_left = is_left

    def __lt__(self, other):
        a = self.ptr.left if self.is_left else self.ptr.right
        b = other.ptr.left if other.is_left else other.ptr.right
        return a < b or (a == b and self.is_left and not other.is_left)
# @exclude

    def __repr__(self):
        return '%s: (%s, %s)' % (
            ('Right', 'Left')[self.is_left], self.ptr.left, self.ptr.right)


# @include
def find_minimum_visits(intervals):
    endpoints = []
    for i in intervals:
        endpoints.append(EndPoint(i, True))
        endpoints.append(EndPoint(i, False))
    endpoints.sort()

    def find_minimum_visits_helper(endpoints):
        S = []  # A minimum set of visit times.
        covered = set()
        covering = []
        for e in endpoints:
            if e.is_left:
                covering.append(e.ptr)
            elif e.ptr not in covered:
                # e's interval has not been covered.
                S.append(e.ptr.right)
                # Adds all intervals in covering to covered.
                covered.update(covering)
                covering.clear()  # e is contained in all intervals in covering.
        return S
    return find_minimum_visits_helper(endpoints)
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
        for i in range(n):
            left = random.randrange(10000)
            right = random.randint(left, left + 100)
            A.append(Interval(left, right))
        ans = find_minimum_visits(A)
        check_ans(A, ans)


if __name__ == '__main__':
    main()
