import sys
import random


# @include
def matrix_search(A, x):
    row, col = 0, len(A[0]) - 1  # Start from the top-right corner.
    # Keeps searching while there are unclassified rows and columns.
    while row < len(A) and col >= 0:
        if A[row][col] == x:
            return True
        elif A[row][col] < x:
            row += 1  # Eliminate this row.
        else:  # A[row][col] > x.
            col -= 1  # Eliminate this column.
    return False
# @exclude


def simple_test():
    A = [[1]]
    assert not matrix_search(A, 0)
    assert matrix_search(A, 1)

    A = [[1, 5], [2, 6]]
    assert not matrix_search(A, 0)
    assert matrix_search(A, 1)
    assert matrix_search(A, 2)
    assert matrix_search(A, 5)
    assert matrix_search(A, 6)
    assert not matrix_search(A, 3)
    assert not matrix_search(A, float('inf'))

    A = [[2, 5], [2, 6]]
    assert not matrix_search(A, 1)
    assert matrix_search(A, 2)

    A = [[1, 5, 7], [3, 10, 100], [3, 12, float('inf')]]
    assert matrix_search(A, 1)
    assert not matrix_search(A, 2)
    assert not matrix_search(A, 4)
    assert matrix_search(A, 3)
    assert matrix_search(A, 10)
    assert matrix_search(A, float('inf'))
    assert matrix_search(A, 12)


# O(n^2) solution for verifying answer.
def brute_force_search(A, x):
    for row in A:
        for cell in row:
            if cell == x:
                return True
    return False


def main():
    simple_test()
    for _ in range(10000):
        if len(sys.argv) == 3:
            n = int(sys.argv[1])
            m = int(sys.argv[2])
        else:
            n = random.randint(1, 100)
            m = random.randint(1, 100)
        A = [[0] * m for i in range(n)]
        A[0][0] = random.randrange(100)
        for i in range(n):
            for j in range(m):
                up = 0 if i == 0 else A[i - 1][j]
                left = 0 if j == 0 else A[i][j - 1]
                A[i][j] = max(up, left) + random.randint(1, 20)
        x = random.randrange(100)
        assert brute_force_search(A, x) == matrix_search(A, x)


if __name__ == '__main__':
    main()
