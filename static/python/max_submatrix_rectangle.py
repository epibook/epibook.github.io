import sys
import random
import collections
from max_submatrix_rectangle_brute_force import max_rectangle_submatrix_brute_force


# @include
def max_rectangle_submatrix(A):
    MaxHW = collections.namedtuple('MaxHW', ('h', 'w'))
    # DP table stores (h, w) for each (i, j).
    table = [[None] * len(A[0]) for _ in A]

    for i, row in reversed(list(enumerate(A))):
        for j, v in reversed(list(enumerate(row))):
            # Find the largest h such that (i, j) to (i + h - 1, j) are feasible.
            # Find the largest w such that (i, j) to (i, j + w - 1) are feasible.
            table[i][j] = (MaxHW(table[i + 1][j].h + 1
                                 if i + 1 < len(A) else 1, table[i][j + 1].w + 1
                                 if j + 1 < len(row) else 1)
                           if v else MaxHW(0, 0))

    max_rect_area = 0
    for i, row in enumerate(A):
        for j, v in enumerate(row):
            # Process (i, j) if it is feasible and is possible to update
            # max_rect_area.
            if v and table[i][j].w * table[i][j].h > max_rect_area:
                min_width = float('inf')
                for a in range(table[i][j].h):
                    min_width = min(min_width, table[i + a][j].w)
                    max_rect_area = max(max_rect_area, min_width * (a + 1))
    return max_rect_area
# @exclude


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
        print(max_rectangle_submatrix(A))
        test_area = max_rectangle_submatrix_brute_force(A)
        print(test_area)
        assert test_area == max_rectangle_submatrix(A)


if __name__ == '__main__':
    main()
