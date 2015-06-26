# Spiral_matrix_clockwise.cc c49e1253eabe0a3ae66bcdfda69fd5c045b18077
import math

# @include
def matrix_in_spiral_order(A):
    result = []
    for offset in range(math.ceil(0.5 * len(A))):
        matrix_clockwise(A, offset, result)
    return result

def matrix_clockwise(A, offset, result):
    last = len(A) - offset - 1
    if offset == last:
        result.append(A[offset][offset])
        return

    for j in range(offset, last):
        result.append(A[offset][j])
    for i in range(offset, last):
        result.append(A[i][last])
    for j in reversed(range(offset + 1, last + 1)):
        result.append(A[last][j])
    for i in reversed(range(offset + 1, last + 1)):
        result.append(A[i][offset])

# @exclude
def main():
    test_matr_1 = [[1, 2, 3, ],
                   [4, 5, 6, ],
                   [7, 8, 9, ], ]
    result_1 = [1, 2, 3, 6, 9, 8, 7, 4, 5, ]
    assert matrix_in_spiral_order(test_matr_1) == result_1

    test_matr_2 = [[1, ]]
    result_2 = [1, ]
    assert matrix_in_spiral_order(test_matr_2) == result_2

    test_matr_3 = [[1,   2,  3,  4, ],
                   [5,   6,  7,  8, ],
                   [9,  10, 11, 12, ],
                   [13, 14, 15, 16, ], ]
    result_3 = [1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10, ]
    assert matrix_in_spiral_order(test_matr_3) == result_3

    test_matr_4 = []
    result_4 = []
    assert matrix_in_spiral_order(test_matr_4) == result_4

if __name__ == '__main__':
    main()
