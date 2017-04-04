import sys
import random
import heapq


# @include
def find_k_closest_to_median(A, k):
    median = find_median(A)
    return heapq.nsmallest(k, A, key=lambda x: abs(x - median))


# Computes the median of A.
def find_median(A):
    target = len(A) // 2
    smallest = heapq.nsmallest(target + 1, A)
    if len(A) % 2 == 1:  # A has odd number of elements.
        return smallest[-1]
    else:  # A has even number of elements.
        return (smallest[-1] + smallest[-2]) / 2
# @exclude


def check_ans(A, res, k):
    A.sort()
    median = A[len(A) // 2] if len(A) % 2 else 0.5 * (
        A[len(A) // 2 - 1] + A[len(A) // 2])
    temp = [abs(median - a) for a in A]
    temp.sort()
    for r in res:
        assert abs(r - median) <= temp[k - 1]
    A_set = set(A)
    assert all(a in A_set for a in res)


def simple_test():
    D = [3, 2, 3, 5, 7, 3, 1]
    Dres = find_k_closest_to_median(D, 3)
    check_ans(D, Dres, 3)
    D = [0, 9, 2, 9, 8]
    Dres = find_k_closest_to_median(D, 2)
    check_ans(D, Dres, 2)


def test(A, k):
    res = find_k_closest_to_median(A, k)
    assert len(res) == k
    check_ans(A, res, k)


def test_multiple_k(A, order):
    for k in order:
        test(A, k)


def random_test_fixed_n(N):
    order = [min(N, i + 1) for i in range(5)]
    order.append(min(N, 7))
    order.append(min(N, 9))
    order.append(min(N, 12))
    order.append(min(N, max(N // 2 - 1, 1)))
    order.append(min(N, max(N // 2, 1)))
    order.append(min(N, N // 2 + 1))
    order.append(min(1, N // 2 + 1))
    order.append(max(1, N - 1))
    order.append(N)

    A = [random.randrange(10000000) for _ in range(N)]
    test_multiple_k(A, order)

    A = [random.randrange(N) for _ in range(N)]
    test_multiple_k(A, order)

    A = [random.randrange(2 * N) for _ in range(N)]
    test_multiple_k(A, order)

    A = [random.randint(0, max(N // 2, 1)) for _ in range(N)]
    test_multiple_k(A, order)


def complex_random_test():
    N = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 50, 100]
    for n in N:
        for _ in range(100):
            random_test_fixed_n(n)


def main():
    simple_test()
    complex_random_test()
    for _ in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
            k = random.randint(1, n)
        elif len(sys.argv) == 3:
            n = int(sys.argv[1])
            k = int(sys.argv[2])
        else:
            n = random.randint(1, 10000)
            k = random.randint(1, n)
        A = [random.randrange(n * 2) for _ in range(n)]
        res = find_k_closest_to_median(A, k)
        assert len(res) == k
        print('n = ', n, ', k = ', k, sep='')
        check_ans(A, res, k)


if __name__ == '__main__':
    main()
