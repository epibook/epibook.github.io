import collections
import sys
import random

# @include
MinMax = collections.namedtuple('MinMax', ('smallest', 'largest'))


def find_min_max(A):
    def min_max(a, b):
        return MinMax(a, b) if a < b else MinMax(b, a)

    if len(A) <= 1:
        return MinMax(A[0], A[0])

    global_min_max = min_max(A[0], A[1])
    # Process two elements at a time.
    for i in range(2, len(A) - 1, 2):
        local_min_max = min_max(A[i], A[i + 1])
        global_min_max = MinMax(
            min(global_min_max.smallest, local_min_max.smallest),
            max(global_min_max.largest, local_min_max.largest))
    # If there is odd number of elements in the array, we still need to
    # compare the last element with the existing answer.
    if len(A) % 2:
        global_min_max = MinMax(
            min(global_min_max.smallest, A[-1]),
            max(global_min_max.largest, A[-1]))
    return global_min_max
# @exclude


def simple_test():
    A = [-1, 3, -4, 6, 4, 10, 4, 4, 9]
    res = find_min_max(A)
    assert res == MinMax(-4, 10)
    A[5] = -12
    res = find_min_max(A)
    assert res == MinMax(-12, 9)
    A = [-1, 3, -4]
    res = find_min_max(A)
    assert res == MinMax(-4, 3)


def main():
    simple_test()
    for _ in range(10000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        A = [random.randrange(1000000) for _ in range(n)]
        res = find_min_max(A)
        assert res == MinMax(min(A), max(A))


if __name__ == '__main__':
    main()
