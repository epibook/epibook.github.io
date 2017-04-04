import heapq
import sys
import operator
import random


# @include
# The numbering starts from one, i.e., if A = [3, 1, -1, 2]
# find_kth_largest(1, A) returns 3, find_kth_largest(2, A) returns 2,
# find_kth_largest(3, A) returns 1, and find_kth_largest(4, A) returns -1.
def find_kth_largest(k, A):
    def find_kth(comp):
        # Partition A[left:right + 1] around pivot_idx, returns the new index of
        # the pivot, new_pivot_idx, after partition. After partitioning,
        # A[left:new_pivot_idx] contains elements that are greater than the
        # pivot, and A[new_pivot_idx + 1:right + 1] contains elements that are
        # less than the pivot.
        #
        # Note: "less than" is defined by the comp object.
        #
        # Returns the new index of the pivot element after partition.
        def partition_around_pivot(left, right, pivot_idx):
            pivot_value = A[pivot_idx]
            new_pivot_idx = left
            A[pivot_idx], A[right] = A[right], A[pivot_idx]
            for i in range(left, right):
                if comp(A[i], pivot_value):
                    A[i], A[new_pivot_idx] = A[new_pivot_idx], A[i]
                    new_pivot_idx += 1
            A[right], A[new_pivot_idx] = A[new_pivot_idx], A[right]
            return new_pivot_idx

        left, right = 0, len(A) - 1
        while left <= right:
            # Generates a random integer in [left, right].
            pivot_idx = random.randint(left, right)
            new_pivot_idx = partition_around_pivot(left, right, pivot_idx)
            if new_pivot_idx == k - 1:
                return A[new_pivot_idx]
            elif new_pivot_idx > k - 1:
                right = new_pivot_idx - 1
            else:  # new_pivot_idx < k - 1.
                left = new_pivot_idx + 1
    # @exclude
        raise IndexError('no k-th node in array A')
    # @include

    return find_kth(operator.gt)
# @exclude


# The numbering starts from one, i.e., if A = [3, 1, -1, 2] then
# find_kth_smallest(1, A) returns -1, find_kth_smallest(2, A) returns 1,
# find_kth_smallest(3, A) returns 2, and find_kth_smallest(4, A) returns 3.
def find_kth_smallest(k, A):
    def find_kth(comp):
        # Partition A[left:right + 1] around pivot_idx, returns the new index of
        # the pivot, new_pivot_idx, after partition. After partitioning,
        # A[left:new_pivot_idx] contains elements that are greater than the
        # pivot, and A[new_pivot_idx + 1:right + 1] contains elements that are
        # less than the pivot.
        #
        # Note: "less than" is defined by the comp object.
        #
        # Returns the new index of the pivot element after partition.
        def partition_around_pivot(left, right, pivot_idx):
            pivot_value = A[pivot_idx]
            new_pivot_idx = left
            A[pivot_idx], A[right] = A[right], A[pivot_idx]
            for i in range(left, right):
                if comp(A[i], pivot_value):
                    A[i], A[new_pivot_idx] = A[new_pivot_idx], A[i]
                    new_pivot_idx += 1
            A[right], A[new_pivot_idx] = A[new_pivot_idx], A[right]
            return new_pivot_idx

        left, right = 0, len(A) - 1
        while left <= right:
            # Generates a random integer in [left, right].
            pivot_idx = random.randint(left, right)
            new_pivot_idx = partition_around_pivot(left, right, pivot_idx)
            if new_pivot_idx == k - 1:
                return A[new_pivot_idx]
            elif new_pivot_idx > k - 1:
                right = new_pivot_idx - 1
            else:  # new_pivot_idx < k - 1.
                left = new_pivot_idx + 1
        raise IndexError('no k-th node in array A')

    return find_kth(operator.lt)


def simple_test_kth_smallest():
    A = [3, 1, 2, 0, 4, 6, 5]
    assert 0 == find_kth_smallest(1, A)
    assert 1 == find_kth_smallest(2, A)
    assert 2 == find_kth_smallest(3, A)
    assert 3 == find_kth_smallest(4, A)
    assert 4 == find_kth_smallest(5, A)
    assert 5 == find_kth_smallest(6, A)
    assert 6 == find_kth_smallest(7, A)
    A[2] = 6
    assert 6 == find_kth_smallest(6, A)
    assert 6 == find_kth_smallest(7, A)
    assert 5 == find_kth_smallest(5, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    # -7 0 0 3 4 4 6 10 12
    assert -7 == find_kth_smallest(1, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 0 == find_kth_smallest(2, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 0 == find_kth_smallest(3, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 3 == find_kth_smallest(4, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 4 == find_kth_smallest(5, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 4 == find_kth_smallest(6, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 6 == find_kth_smallest(7, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 10 == find_kth_smallest(8, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 12 == find_kth_smallest(9, A)

    assert 4 == find_kth_smallest(6, A)
    for i in range(len(A)):
        if i < 4:
            assert A[i] < 4
        elif i > 5:
            assert A[i] > 4


def simple_test_kth_largest():
    A = [3, 1, 2, 0, 4, 6, 5]
    assert 6 == find_kth_largest(1, A)
    assert 5 == find_kth_largest(2, A)
    assert 4 == find_kth_largest(3, A)
    assert 3 == find_kth_largest(4, A)
    assert 2 == find_kth_largest(5, A)
    assert 1 == find_kth_largest(6, A)
    assert 0 == find_kth_largest(7, A)
    A[2] = 6
    assert 6 == find_kth_largest(1, A)
    assert 6 == find_kth_largest(2, A)
    assert 5 == find_kth_largest(3, A)

    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    # 12 10 6 4 4 3 0 0 -7
    assert 12 == find_kth_largest(1, A)
    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 10 == find_kth_largest(2, A)
    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 6 == find_kth_largest(3, A)
    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 4 == find_kth_largest(4, A)
    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 4 == find_kth_largest(5, A)
    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 3 == find_kth_largest(6, A)
    A = [0, -7, 3, 4, 4, 12, 6, 10, 0]
    assert 4 == find_kth_largest(5, A)
    for i in range(len(A)):
        if i < 3:
            assert A[i] > 4
        elif i > 4:
            assert A[i] < 4


def simple_test():
    C = [9, 5]
    assert 9 == find_kth_largest(1, C)
    assert 5 == find_kth_smallest(1, C)

    A = [123]
    assert 123 == find_kth_largest(1, A)


def check_order_statistic(k, increasing_order, A):
    B = list(A)
    if increasing_order:
        find_kth_smallest(k, A)
    else:
        find_kth_largest(k, A)

    B_sort = sorted(B)
    if not increasing_order:
        B_sort = B_sort[::-1]

    A_sort = sorted(A)
    if not increasing_order:
        A_sort = A_sort[::-1]

    assert A_sort == B_sort


def test_all_orders(order, A):
    for k in order:
        check_order_statistic(k, True, A)
        check_order_statistic(k, False, A)


def random_test_fixed_n(n):
    order = []
    for i in range(5):
        order.append(min(n, i + 1))
    order.append(min(n, 7))
    order.append(min(n, 9))
    order.append(min(n, 12))
    order.append(min(n, max(n // 2 - 1, 1)))
    order.append(min(n, max(n // 2, 1)))
    order.append(n)

    A = [random.randint(0, 9999999) for _ in range(n)]
    test_all_orders(order, A)

    A = [random.randint(0, n - 1) for _ in range(n)]
    test_all_orders(order, A)

    A = [random.randint(0, max(n // 2, 1) - 1) for _ in range(n)]
    test_all_orders(order, A)


def complex_random_test():
    N = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 50, 100]
    for i in range(len(N)):
        for _ in range(100):
            random_test_fixed_n(N[i])


def main():
    simple_test()
    simple_test_kth_largest()
    simple_test_kth_smallest()
    complex_random_test()
    for i in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
            k = random.randint(1, n)
        elif len(sys.argv) == 3:
            n = int(sys.argv[1])
            k = int(sys.argv[2])
        else:
            n = random.randint(1, 100000)
            k = random.randint(1, n)
        A = [random.randint(0, 9999999) for _ in range(n)]
        assert find_kth_largest(k, A) == heapq.nlargest(k, A)[-1]


if __name__ == '__main__':
    main()
