import sys
import random
import bisect


# @include
def binary_search_unknown_length(A, k):
    # Find the possible range where k exists.
    p = 0
    while True:
        try:
            idx = (1 << p) - 1  # 2^p - 1.
            if A[idx] == k:
                return idx
            elif A[idx] > k:
                break
        except IndexError:
            break
        p += 1

    # Binary search between indices 2^(p - 1) and 2^p - 2, inclusive.
    left, right = 1 << max(0, (p - 1)), (1 << p) - 2
    while left <= right:
        mid = left + (right - left) // 2
        try:
            if A[mid] == k:
                return mid
            elif A[mid] > k:
                right = mid - 1
            else:  # A[mid] < k
                left = mid + 1
        except IndexError:
            right = mid - 1  # Search the left part if out-of-bound.
    return -1  # Nothing matched k.
# @exclude


def small_test():
    A = [1, 2, 3]
    assert binary_search_unknown_length(A, 3) == 2
    assert binary_search_unknown_length(A, 1) == 0
    assert binary_search_unknown_length(A, 2) == 1
    assert binary_search_unknown_length(A, 4) == -1
    assert binary_search_unknown_length(A, -1) == -1


def binary_search(a, x):
    i = bisect.bisect_left(a, x)
    if i != len(a) and a[i] == x:
        return i
    return -1


def main():
    small_test()
    for _ in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
            k = random.randrange(100000)
        elif len(sys.argv) == 3:
            n = int(sys.argv[1])
            k = int(sys.argv[2])
        else:
            n = random.randint(1, 1000000)
            k = random.randrange(n * 2)

        A = sorted(random.randrange(n * 2) for _ in range(n))
        print(n, k)
        idx = binary_search_unknown_length(A, k)
        print(idx)
        assert idx == -1 and binary_search(A, k) == -1 or A[idx] == k


if __name__ == '__main__':
    main()
