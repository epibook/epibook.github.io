import copy
import sys
import random
import itertools


def rotate_matrix(A):
    for i in range(len(A) // 2):
        for j in range(i, len(A) - i - 1):
            temp = A[i][j]
            A[i][j] = A[-1 - j][i]
            A[-1 - j][i] = A[-1 - i][-1 - j]
            A[-1 - i][-1 - j] = A[j][-1 - i]
            A[j][-1 - i] = temp


# @include
class RotatedMatrix:

    def __init__(self, square_matrix):
        self._square_matrix = square_matrix

    def read_entry(self, i, j):
        # Note that A[~i] for i in [0, len(A) - 1] is A[~(i + 1)].
        return self._square_matrix[~j][i]

    def write_entry(self, i, j, v):
        self._square_matrix[~j][i] = v
# @exclude


def check_answer(A, B):
    rA = RotatedMatrix(A)
    for i in range(len(A)):
        for j in range(len(A)):
            assert rA.read_entry(i, j) == B[i][j]


def main():
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
        k = itertools.count(1)
        A = []
        for _ in range(1 << n):
            A.append([next(k) for _ in range(1 << n)])
        B = copy.deepcopy(A)
        rotate_matrix(B)
        check_answer(A, B)
    else:
        for _ in range(100):
            n = random.randint(1, 10)
            k = itertools.count(1)
            A = []
            for _ in range(1 << n):
                A.append([next(k) for _ in range(1 << n)])
            B = copy.deepcopy(A)
            rotate_matrix(B)
            check_answer(A, B)


if __name__ == '__main__':
    main()
