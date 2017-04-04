import sys
import random
import collections


# @include
class GraphVertex:

    def __init__(self):
        self.d = -1
        self.edges = []
# @exclude

    def __repr__(self):
        return '(%d)%d(%s)' % (self.d, id(self), ','.join(
            str(id(x)) for x in self.edges))


# @include


def is_any_placement_feasible(G):
    def bfs(s):
        s.d = 0
        q = collections.deque([s])

        while q:
            for t in q[0].edges:
                if t.d == -1:  # Unvisited vertex.
                    t.d = q[0].d + 1
                    q.append(t)
                elif t.d == q[0].d:
                    return False
            del q[0]
        return True

    return all(bfs(v) for v in G if v.d == -1)
# @exclude


def is_two_colorable(G):
    for v in G:
        v.d = -1

    def dfs(s):
        for t in s.edges:
            if t.d == -1:
                t.d = int(not s.d)
                if not dfs(t):
                    return False
            elif t.d == s.d:
                return False
        return True

    for v in G:
        if v.d == -1:
            v.d = 0
            if not dfs(v):
                return False
    return True


def main():
    for times in range(9000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(2, 101)
        G = [GraphVertex() for i in range(n)]
        m = random.randint(1, n * (n - 1) // 2)
        print(times, n, m)
        is_edge_exist = [[False] * n for i in range(n)]
        while m > 0:
            m -= 1
            while True:
                a = random.randrange(n)
                b = random.randrange(n)
                if a != b and is_edge_exist[a][b] == False:
                    break
            is_edge_exist[a][b] = is_edge_exist[b][a] = True
            G[a].edges.append(G[b])
            G[b].edges.append(G[a])

        res = is_any_placement_feasible(G)
        print(res)
        assert res == is_two_colorable(G)


if __name__ == '__main__':
    main()
