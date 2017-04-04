import sys
import random


# @include
def is_pattern_contained_in_grid(grid, S):
    def is_pattern_suffix_contained_starting_at_xy(x, y, offset):
        if len(S) == offset:
            # Nothing left to complete.
            return True

        # Check if (x, y) lies outside the grid.
        if (0 <= x < len(grid) and 0 <= y < len(grid[x]) and
                grid[x][y] == S[offset] and
            (x, y, offset) not in previous_attempts and any(
                is_pattern_suffix_contained_starting_at_xy(x + a, y + b,
                                                           offset + 1)
                for a, b in ((-1, 0), (1, 0), (0, -1), (0, 1)))):
            return True
        previous_attempts.add((x, y, offset))
        return False

    # Each entry in previous_attempts is a point in the grid and suffix of
    # pattern (identified by its offset). Presence in previous_attempts
    # indicates the suffix is not contained in the grid starting from that
    # point.
    previous_attempts = set()
    return any(
        is_pattern_suffix_contained_starting_at_xy(i, j, 0)
        for i in range(len(grid)) for j in range(len(grid[i])))
# @exclude


def main():
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(2, 10)
    A = [[random.randrange(n) for _ in range(n)] for _ in range(n)]
    for a in A:
        print(*a)
    print('S = ', end='')
    S = [random.randrange(n) for i in range(1 + random.randint(1, n * n // 2))]
    print(*S)
    print(is_pattern_contained_in_grid(A, S))


if __name__ == '__main__':
    main()
