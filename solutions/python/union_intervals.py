import sys
import collections
import random

# @include
Endpoint = collections.namedtuple('Endpoint', ('is_closed', 'val'))


class Interval:
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def __lt__(self, other):
        if self.left.val != other.left.val:
            return self.left.val < other.left.val
        # Left endpoints are equal, so now see if one is closed and the other open
        # - closed intervals should appear first.
        return self.left.is_closed and not other.left.is_closed


def union_of_intervals(intervals):
    # Empty input.
    if not intervals:
        return []

    # Sort intervals according to left endpoints of intervals.
    intervals.sort()
    result = [intervals[0]]
    for i in intervals:
        if intervals and (i.left.val < result[-1].right.val or
                          (i.left.val == result[-1].right.val and
                           (i.left.is_closed or result[-1].right.is_closed))):
            if (i.right.val > result[-1].right.val or
                (i.right.val == result[-1].right.val and i.right.is_closed)):
                result[-1].right = i.right
        else:
            result.append(i)
    return result


# @exclude


def check_intervals(A):
    # Only check the intervals do not overlap with each other.
    assert all(A[i - 1].right.val < A[i].left.val or
               (A[i - 1].right.val == A[i].left.val and
                not A[i - 1].right.is_closed and not A[i].left.is_closed)
               for i in range(1, len(A)))


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
        A = []
        for _ in range(n):
            left = Endpoint(bool(random.randrange(2)), random.randrange(10000))
            right = Endpoint(
                bool(random.randrange(2)),
                random.randrange(left.val + 1, left.val + 100))
            temp = Interval(left, right)
            A.append(temp)
        ret = union_of_intervals(A)
        if ret:
            check_intervals(ret)


if __name__ == '__main__':
    main()
