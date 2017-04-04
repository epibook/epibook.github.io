import sys
import random
import itertools


# @include
def matrix_in_spiral_order(square_matrix):
    def matrix_layer_in_clockwise(offset):
        if offset == len(square_matrix) - offset - 1:
            # square_matrix has odd dimension, and we are at the center of the
            # matrix square_matrix.
            spiral_ordering.append(square_matrix[offset][offset])
            return

        spiral_ordering.extend(square_matrix[offset][offset:-1 - offset])
        spiral_ordering.extend(
            list(zip(*square_matrix))[-1 - offset][offset:-1 - offset])
        spiral_ordering.extend(
            square_matrix[-1 - offset][-1 - offset:offset:-1])
        spiral_ordering.extend(
            list(zip(*square_matrix))[offset][-1 - offset:offset:-1])

    spiral_ordering = []
    for offset in range((len(square_matrix) + 1) // 2):
        matrix_layer_in_clockwise(offset)
    return spiral_ordering
# @exclude


def simple_test():
    A = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    assert matrix_in_spiral_order(A) == [1, 2, 3, 6, 9, 8, 7, 4, 5]


def main():
    simple_test()
    N = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 50)
    k = itertools.count(1)
    A = [[next(k) for _ in range(N)] for _ in range(N)]
    matrix_in_spiral_order(A)


if __name__ == '__main__':
    main()
