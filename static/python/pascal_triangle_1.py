import sys
import random


# @include
def generate_pascal_triangle(n):
    result = [[1] * (i + 1) for i in range(n)]
    for i in range(n):
        for j in range(1, i):
            # Sets this entry to the sum of the two above adjacent entries.
            result[i][j] = result[i - 1][j - 1] + result[i - 1][j]
    return result
# @exclude


def small_test():
    result = generate_pascal_triangle(3)
    assert result == [[1], [1, 1], [1, 2, 1]]


def main():
    small_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(0, 10)
    print('n =', n)
    result = generate_pascal_triangle(n)
    for i in result:
        print(*i)


if __name__ == '__main__':
    main()
