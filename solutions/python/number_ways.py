# Number_ways.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random
import itertools


# @include
def number_of_ways(n, m):
    if n < m:
        n, m = m, n

    A = [1] * m
    for i in range(1, n):
        prev_res = 0
        for j in range(m):
            A[j] += prev_res
            prev_res = A[j]
    return A[m - 1]
# @exclude


# Pythonic solution
def number_of_ways_pythonic(n, m):
    if n < m:
        n, m = m, n

    A = [1] * m
    for i in range(1, n):
        A = list(itertools.accumulate(A))
    return A[-1]


def check_ans(n, m):
    table = [[0] * m for i in range(n)]
    # Basic case: C(i, 0) = 1.
    for i in range(n):
        table[i][0] = 1
    # Basic case: C(0, i) = 1.
    for i in range(1, m):
        table[0][i] = 1
    # C(i, j) = C(i - 1, j) + C(i, j - 1).
    for i in range(1, n):
        for j in range(1, m):
            table[i][j] = table[i - 1][j] + table[i][j - 1]
    return table[n-1][m-1]


def main():
    for _ in range(1000):
        if len(sys.argv) == 3:
            n = int(sys.argv[1])
            m = int(sys.argv[2])
        else:
            n = random.randint(1, 10)
            m = random.randint(1, 10)
        print('n =', n, ', m =', m, ', ways =', number_of_ways(n, m))
        assert check_ans(n, m) == number_of_ways(n, m) == number_of_ways_pythonic(n, m)
        if len(sys.argv) == 3:
            break


if __name__ == '__main__':
    main()
