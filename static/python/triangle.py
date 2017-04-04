# @include
def minimum_path_weight(triangle):
    min_weight_to_curr_row = [0]
    for row in triangle:
        min_weight_to_curr_row = [
            row[j] +
            min(min_weight_to_curr_row[max(j - 1, 0)],
                min_weight_to_curr_row[min(j, len(min_weight_to_curr_row) - 1)])
            for j in range(len(row))
        ]
    return min(min_weight_to_curr_row)
# @exclude

import functools


def minimum_path_weight_pythonic(triangle):
    return min(functools.reduce(lambda res, tri: [r + min(a, b) for r, a, b in zip(tri, [float('inf')] + res, res + [float('inf')])], triangle, [0]))


def main():
    A = [[2], [3, 4], [6, 5, 7], [4, 1, 8, 3]]
    assert 11 == minimum_path_weight(A) == minimum_path_weight_pythonic(A)


if __name__ == '__main__':
    main()
