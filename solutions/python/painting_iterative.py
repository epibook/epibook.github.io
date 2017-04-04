import sys
import random
import collections


# @include
def flip_color(x, y, A):
    Coordinate = collections.namedtuple('Coordinate', ('x', 'y'))
    color = A[x][y]
    q = collections.deque([Coordinate(x, y)])
    A[x][y] = 1 - A[x][y]  # Flips.
    while q:
        x, y = q.popleft()
        for d in (0, 1), (0, -1), (1, 0), (-1, 0):
            next_x, next_y = x + d[0], y + d[1]
            if (0 <= next_x < len(A) and 0 <= next_y < len(A[next_x]) and
                    A[next_x][next_y] == color):
                # Flips the color.
                A[next_x][next_y] = 1 - A[next_x][next_y]
                q.append(Coordinate(next_x, next_y))
# @exclude


def main():
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 100)
    A = [[random.randint(0, 1) for _ in range(n)] for _ in range(n)]
    i, j = random.randrange(n), random.randrange(n)
    print('color =', i, j, A[i][j])
    print(*A)
    flip_color(i, j, A)
    print()
    print(*A)


if __name__ == '__main__':
    main()
