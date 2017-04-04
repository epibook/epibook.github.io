# @include
def bsearch(t, A):
    L, U = 0, len(A) - 1
    while L <= U:
        M = (L + U) // 2
        if A[M] < t:
            L = M + 1
        elif A[M] == t:
            return M
        else:
            U = M - 1
    return -1
# @exclude


def simple_test():
    A = [1, 2, 3]
    assert bsearch(1, A) == 0
    assert bsearch(2, A) == 1
    assert bsearch(3, A) == 2
    A = [2, 2, 2]
    assert 0 <= bsearch(2, A) <= 2
    assert bsearch(3, A) == -1
    assert bsearch(0, A) == -1


def main():
    simple_test()
    A = [1, 2, 2, 2, 2, 3, 3, 3, 5, 6, 10, 100]
    assert 0 == bsearch(1, A)
    assert 1 <= bsearch(2, A) <= 4
    assert 5 <= bsearch(3, A)
    assert -1 <= bsearch(4, A)


if __name__ == '__main__':
    main()
