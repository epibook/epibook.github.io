import sys
import random
import collections


# O(m^3 n^3) time solution.
def check_ans(A):
    max = 0
    for a in range(len(A)):
        for b in range(len(A[a])):
            r = 1
            while a + r <= len(A) and b + r <= len(A[0]):
                count = 0
                all_1 = True
                for c in range(a, a + r):
                    for d in range(b, b + r):
                        if A[c][d]:
                            count += 1
                        else:
                            all_1 = False
                            count = 0
                            break
                    if not all_1:
                        break
                if count > max:
                    max = count
                r += 1
    return max


# @include
def max_square_submatrix(A):
    MaxHW = collections.namedtuple('MaxHW', ('h', 'w'))
    # DP table stores (h, w) for each (i, j).
    table = [[None] * len(A[0]) for _ in A]
    for i, row in reversed(list(enumerate(A))):
        for j, v in reversed(list(enumerate(row))):
            # Finds the largest h such that (i, j) to (i + h - 1, j) are feasible.
            # Finds the largest w such that (i, j) to (i, j + w - 1) are feasible.
            table[i][j] = (MaxHW(table[i + 1][j].h + 1
                                 if i + 1 < len(A) else 1, table[i][j + 1].w + 1
                                 if j + 1 < len(row) else 1)
                           if v else MaxHW(0, 0))

    # A table stores the length of the largest square for each (i, j).
    s = [[0] * len(A[0]) for _ in A]
    max_square_area = 0
    for i, row in reversed(list(enumerate(A))):
        for j, v in reversed(list(enumerate(row))):
            if v:
                side = min(table[i][j].h, table[i][j].w)
                # Gets the length of largest square with bottom-left corner (i,
                # j).
                if i + 1 < len(A) and j + 1 < len(A[i + 1]):
                    side = min(s[i + 1][j + 1] + 1, side)
                s[i][j] = side
                max_square_area = max(max_square_area, side**2)
    return max_square_area


# @exclude


def max_square_submatrix_space_efficient(A):
    pre = [0] * len(A[0])
    max_side = 0
    for row in A:
        for j, v in enumerate(row[1:], 1):
            row[j] *= min(pre[j - 1], pre[j], row[j - 1]) + 1
        max_side = max(max_side, max(row))
        pre = row
    return max_side**2


def main():
    for _ in range(1000):
        if len(sys.argv) == 3:
            n = int(sys.argv[1])
            m = int(sys.argv[2])
        else:
            n = random.randint(1, 50)
            m = random.randint(1, 50)
        A = [[bool(random.randrange(2)) for _ in range(m)] for _ in range(n)]
        for i in range(n):
            print(*map(int, A[i]))
        print(max_square_submatrix(A))
        print(check_ans(A))
        assert check_ans(A) == max_square_submatrix(
            A) == max_square_submatrix_space_efficient(A)


if __name__ == '__main__':
    main()
