import sys
import random
import copy


# @include
def rook_attack(A):
    m, n = len(A), len(A[0])
    has_first_row_zero = any(not A[0][j] for j in range(n))
    has_first_column_zero = any(not A[i][0] for i in range(m))

    for i in range(1, m):
        for j in range(1, n):
            if not A[i][j]:
                A[i][0] = A[0][j] = 0

    for i in range(1, m):
        if not A[i][0]:
            for j in range(1, n):
                A[i][j] = 0

    for j in range(1, n):
        if not A[0][j]:
            for i in range(1, m):
                A[i][j] = 0

    if has_first_row_zero:
        for j in range(n):
            A[0][j] = 0

    if has_first_column_zero:
        for i in range(m):
            A[i][0] = 0
# @exclude


def check_ans(A, ans):
    assert all(not ans[k][j] for i in range(len(A)) for j in range(len(A[i]))
               if not A[i][j] for k in range(len(ans)))
    assert all(not ans[i][k] for i in range(len(A)) for j in range(len(A[i]))
               if not A[i][j] for k in range(len(ans[i])))


def main():
    for _ in range(1000):
        if len(sys.argv) == 3:
            m = int(sys.argv[1])
            n = int(sys.argv[2])
        else:
            m = random.randint(1, 50)
            n = random.randint(1, 50)
        A = [[random.randint(0, 1) for _ in range(n)] for _ in range(m)]
        copy_A = copy.deepcopy(A)
        print('m = %d, n = %d' % (m, n))
        rook_attack(A)
        check_ans(copy_A, A)


if __name__ == '__main__':
    main()
