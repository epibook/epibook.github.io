import itertools
import sys
import random
import collections

# @include
HighwaySection = collections.namedtuple('HighwaySection',
                                        ('x', 'y', 'distance'))


def find_best_proposals(H, P, n):
    # G stores the shortest path distances between all pairs of vertices.
    G = [[float('inf')] * (i - 1) + [0] + [float('inf')] * (n - i)
         for i in range(n)]
    # Builds an undirected graph G based on existing highway sections H.
    for h in H:
        G[h.x][h.y] = G[h.y][h.x] = h.distance

    def floyd_warshall(G):
        for k, i, j in itertools.product(range(len(G)), repeat=3):
            if G[i][k] != float('inf') and G[k][j] != float('inf'):
                G[i][j] = min(G[i][j], G[i][k] + G[k][j])

    # Performs floyd warshall to build the shortest path between vertices.
    floyd_warshall(G)

    # Examines each proposal for shorter distance for all pairs.
    best_distance_saving = float('-inf')
    best_proposal = HighwaySection(-1, -1, 0.0)  # Default.
    for p in P:
        proposal_saving = 0.0
        for a, b in itertools.product(range(n), repeat=2):
            saving = G[a][b] - (G[a][p.x] + p.distance + G[p.y][b])
            proposal_saving += max(saving, 0.0)
        if proposal_saving > best_distance_saving:
            best_distance_saving = proposal_saving
            best_proposal = p
    return best_proposal
# @exclude


def simple_test():
    H = (HighwaySection(0, 1, 10), HighwaySection(1, 2, 10),
         HighwaySection(2, 3, 10))
    P = (HighwaySection(0, 3, 1), HighwaySection(0, 2, 2),
         HighwaySection(0, 1, 3))
    t = find_best_proposals(H, P, 4)
    assert t.x == 0 and t.y == 3 and t.distance == 1.0


def main():
    simple_test()
    for times in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
            m = random.randint(1, n * ((n - 1) // 2) - 1)
            k = random.randint(1, n * ((n - 1) // 2) - m)
        elif len(sys.argv) == 3:
            n = int(sys.argv[1])
            m = int(sys.argv[2])
            k = random.randint(1, n * ((n - 1) // 2) - m)
        else:
            n = random.randint(5, 100)
            m = random.randint(1, n * ((n - 1) // 2) - 1)
            k = random.randint(1, n * ((n - 1) // 2) - m)
        print('n = %d, m = %d, k = %d' % (n, m, k))

        have_edges = [[False] * n for _ in range(n)]
        H = []  # Existing highway sections
        for i in range(m):
            while True:
                a = random.randrange(n)
                b = random.randrange(n)
                if a != b and have_edges[a][b] == False:
                    break
            have_edges[a][b] = have_edges[b][a] = True
            H.append(HighwaySection(a, b, random.uniform(1.0, 10000.0)))
        for h in H:
            print('H[i] =', h.x, h.y, h.distance)

        P = []  # Proposals
        for i in range(k):
            while True:
                a, b = random.randrange(n), random.randrange(n)
                if a != b and have_edges[a][b] == False:
                    break
            have_edges[a][b] = have_edges[b][a] = True
            P.append(HighwaySection(a, b, random.uniform(1.0, 50.0)))
        for p in P:
            print('P[i] =', p.x, p.y, p.distance)

        t = find_best_proposals(H, P, n)
        print(t.x, t.y, t.distance)


if __name__ == '__main__':
    main()
