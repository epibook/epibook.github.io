import random
import sys


# @include
def n_queens(n):
    def solve_n_queens(row):
        if row == n:
            # All queens are legally placed.
            result.append(list(col_placement))
            return
        for col in range(n):
            # Test if a newly placed queen will conflict any earlier queens
            # placed before.
            if all(abs(c - col) not in (0, row - i)
                   for i, c in enumerate(col_placement[:row])):
                col_placement[row] = col
                solve_n_queens(row + 1)

    result, col_placement = [], [0] * n
    solve_n_queens(0)
    return result
# @exclude


def to_text_representation(col_placement):
    sol = []
    for row in col_placement:
        line = ['.'] * len(col_placement)
        line[row] = 'Q'
        sol.append(''.join(line))
    return sol


def simple_test():
    result = n_queens(2)
    assert 0 == len(result)

    result = n_queens(3)
    assert 0 == len(result)

    result = n_queens(4)
    assert 2 == len(result)

    place1 = [1, 3, 0, 2]
    place2 = [2, 0, 3, 1]
    assert result[0] == place1 or result[0] == place2
    assert result[1] == place1 or result[1] == place2
    assert result[0] != result[1]


def main():
    simple_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 15)
    print('n =', n)
    result = n_queens(n)
    for vec in result:
        text_rep = to_text_representation(vec)
        print(*text_rep, sep='\n')
        print()


if __name__ == '__main__':
    main()
