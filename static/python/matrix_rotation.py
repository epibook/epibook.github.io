import sys
import random
import itertools


def print_matrix(A):
    for i in A:
        print(*i)


def check_answer(A):
    k = itertools.count(1)
    all(next(k) == i[j] for i in A for j in reversed(range(len(A))))


# @include
def rotate_matrix(A):
    rotate_matrix_helper(0, len(A), 0, len(A), A)


def rotate_matrix_helper(x_s, x_e, y_s, y_e, A):
    if x_e > x_s + 1:
        mid_x = x_s + ((x_e - x_s) // 2)
        mid_y = y_s + ((y_e - y_s) // 2)
        # Move submatrices.
        C = [[0] * (mid_y - y_s) for i in range(mid_x - x_s)]
        copy_matrix(0, len(C), 0, len(C), A, x_s, y_s, C)
        copy_matrix(x_s, mid_x, y_s, mid_y, A, mid_x, y_s, A)
        copy_matrix(mid_x, x_e, y_s, mid_y, A, mid_x, mid_y, A)
        copy_matrix(mid_x, x_e, mid_y, y_e, A, x_s, mid_y, A)
        copy_matrix(x_s, mid_x, mid_y, y_e, C, 0, 0, A)

        # Recursively rotate submatrices.
        rotate_matrix_helper(x_s, mid_x, y_s, mid_y, A)
        rotate_matrix_helper(x_s, mid_x, mid_y, y_e, A)
        rotate_matrix_helper(mid_x, x_e, mid_y, y_e, A)
        rotate_matrix_helper(mid_x, x_e, y_s, mid_y, A)


def copy_matrix(A_x_s, A_x_e, A_y_s, A_y_e, S, S_x, S_y, A):
    for i in range(A_x_e - A_x_s):
        A[A_x_s + i][A_y_s:A_y_e] = S[S_x + i][S_y:S_y + A_y_e - A_y_s]
# @exclude


def main():
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
        k = itertools.count(1)
        A = []
        for i in range(1 << n):
            A.append([next(k) for j in range(1 << n)])
        print_matrix(A)
        rotate_matrix(A)
        check_answer(A)
        print()
        print_matrix(A)
    else:
        for _ in range(100):
            n = random.randint(1, 10)
            k = itertools.count(1)
            A = []
            for i in range(1 << n):
                A.append([next(k) for j in range(1 << n)])
            # print_matrix(A)
            rotate_matrix(A)
            check_answer(A)
            # print()
            # print_matrix(A)


if __name__ == '__main__':
    main()
