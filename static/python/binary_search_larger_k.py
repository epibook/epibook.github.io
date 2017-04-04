import sys
import random


# @include
def search_first_larger_of_k(A, k):
    left = 0
    right = len(A) - 1
    result = -1
    # A[left:right + 1] is the candidate set.
    while left <= right:
        m = (left + right) // 2
        if A[m] > k:
            result = m
            right = m - 1  # Nothing to the right of mid can be solution.
        else:  # A[m] <= k.
            left = m + 1
    return result
# @exclude


def check_ans(A, k):
    for i, a in enumerate(A):
        if a > k:
            return i
    return -1


def simple_test():
    A = [0, 1, 2, 3, 4, 5, 6, 7]
    assert 1 == search_first_larger_of_k(A, 0)
    assert 2 == search_first_larger_of_k(A, 1)
    assert 5 == search_first_larger_of_k(A, 4)
    assert 7 == search_first_larger_of_k(A, 6)
    assert -1 == search_first_larger_of_k(A, 7)
    assert 0 == search_first_larger_of_k(A, -1)
    assert 0 == search_first_larger_of_k(A, float('-inf'))
    assert -1 == search_first_larger_of_k(A, float('inf'))
    A[0] = 1
    assert 2 == search_first_larger_of_k(A, 1)
    A[5] = 4
    A[6] = 4
    assert 7 == search_first_larger_of_k(A, 4)
    A = [1, 1, 1, 1, 1, 2]
    assert 5 == search_first_larger_of_k(A, 1)
    assert -1 == search_first_larger_of_k(A, 5)


def main():
    simple_test()
    for _ in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 10000)

        A = sorted(random.randrange(n) for i in range(n))
        k = random.randrange(n)
        ans = search_first_larger_of_k(A, k)
        print('k =', k, 'locates at', ans)
        if ans != -1:
            print('A[k] =', A[ans])
        assert ans == check_ans(A, k)


if __name__ == '__main__':
    main()
