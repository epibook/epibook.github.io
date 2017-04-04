import sys
import random
import collections

# @include
WHITE, BLACK = range(2)

Coordinate = collections.namedtuple('Coordinate', ('x', 'y'))


def search_maze(maze, s, e):
    # Perform DFS to find a feasible path.
    def search_maze_helper(cur):
        # Checks cur is within maze and is a white pixel.
        if not (0 <= cur.x < len(maze) and 0 <= cur.y < len(maze[cur.x]) and
                maze[cur.x][cur.y] != WHITE):
            return False
        path.append(cur)
        maze[cur.x][cur.y] = BLACK
        if cur == e:
            return True

        if any(
                map(search_maze_helper, (Coordinate(cur.x - 1, y), Coordinate(
                    cur.x + 1, y), Coordinate(cur.x, y - 1), Coordinate(cur.x, y
                                                                        + 1)))):
            return True
        del path[-1]
        return False

    path = []
    if not search_maze_helper(s):
        return []  # No path between s and e.
    return path
# @exclude


def main():
    for _ in range(1000):
        if len(sys.argv) == 3:
            n = int(sys.argv[1])
            m = int(sys.argv[2])
        else:
            n = random.randint(1, 30)
            m = random.randint(1, 30)
        maze = [[random.randrange(2) for _ in range(m)] for _ in range(n)]
        white = []
        for i in range(n):
            for j in range(m):
                if maze[i][j] == WHITE:
                    white.append(Coordinate(i, j))
            print(*maze[i])
        print()
        if white:
            start = random.randrange(len(white))
            end = random.randrange(len(white))
            print(white[start].x, white[start].y)
            print(white[end].x, white[end].y)
            path = search_maze(maze, white[start], white[end])
            if path:
                assert white[start] == path[0] and white[end] == path[-1]
            for i in range(len(path)):
                print('(%d,%d)' % path[i])
                if i > 0:
                    assert abs(path[i - 1].x - path[i].x) + abs(path[i - 1].y -
                                                                path[i].y) == 1


if __name__ == '__main__':
    main()
