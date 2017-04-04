import sys
import random


# @include
def search_smallest(A):
    left, right = 0, len(A) - 1
    while left < right:
        mid = (left + right) // 2
        if A[mid] > A[right]:
            # Minimum must be in A[mid + 1:right + 1].
            left = mid + 1
        else:  # A[mid] < A[right].
            # Minimum cannot be in A[mid + 1:right + 1] so it must be in A[left:mid + 1].
            right = mid
    # Loop ends when left == right.
    return left
# @exclude


def simple_test():
    A = [3, 1, 2]
    assert 1 == search_smallest(A)
    A = [0, 2, 4, 8]
    assert 0 == search_smallest(A)
    A[0] = 16
    assert 1 == search_smallest(A)
    A = [2, 3, 4]
    assert 0 == search_smallest(A)
    A = [100, 101, 102, 2, 5]
    assert 3 == search_smallest(A)
    A = [10, 20, 30, 40, 5]
    assert 4 == search_smallest(A)


def main():
    simple_test()
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        A = sorted(random.sample(range(100000), n))
        shift = random.randrange(n)
        A = A[n - shift:] + A[:n - shift]
        assert shift == search_smallest(A)


if __name__ == '__main__':
    main()
