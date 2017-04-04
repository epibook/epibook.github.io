import sys
import random


# @include
def search_smallest(A):
    def search_smallest_helper(left, right):
        if left == right:
            return left

        mid = left + (right - left) // 2
        if A[mid] > A[right]:
            return search_smallest_helper(mid + 1, right)
        elif A[mid] < A[right]:
            return search_smallest_helper(left, mid)
        else:  # A[mid] == A[right].
            # We cannot eliminate either side so we compare the results from both
            # sides.
            left_result = search_smallest_helper(left, mid)
            right_result = search_smallest_helper(mid + 1, right)
            return right_result if A[right_result] < A[
                left_result] else left_result

    return search_smallest_helper(0, len(A) - 1)
# @exclude


def simple_test():
    A = [3, 1, 2]
    assert 1 == search_smallest(A)
    A = [0, 2, 4, 8]
    assert 0 == search_smallest(A)
    A[0] = 16
    assert 1 == search_smallest(A)
    A = [2, 2, 2]
    assert 0 == search_smallest(A)
    A = [100, 2, 5, 5]
    assert 1 == search_smallest(A)
    A = [1, 2, 3, 3, 3]
    assert 0 == search_smallest(A)
    A = [5, 2, 3, 3, 3]
    assert 1 == search_smallest(A)
    A = [5, 5, 2, 2, 2, 3, 3, 3]
    assert 2 == search_smallest(A)


def main():
    simple_test()
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)

        A = sorted([random.randrange(1000000) for i in range(n)])
        shift = random.randrange(n)
        A = A[n - shift:] + A[:n - shift]
        assert shift == search_smallest(A)


if __name__ == '__main__':
    main()
