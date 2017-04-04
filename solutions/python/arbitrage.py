import sys
import math
import random


# @include
def is_arbitrage_exist(G):
    def bellman_ford(G, source):
        dis_to_source = ([float('inf')] * (source - 1) + [0] + [float('inf')] *
                         (len(G) - source))

        for _ in range(1, len(G)):
            have_update = False
            for i, row in enumerate(G):
                for j, g in enumerate(row):
                    if (dis_to_source[i] != float('inf') and
                            dis_to_source[j] > dis_to_source[i] + g):
                        have_update = True
                        dis_to_source[j] = dis_to_source[i] + g

            # No update in this iteration means no negative cycle.
            if not have_update:
                return False

        # Detects cycle if there is any further update.
        return any(dis_to_source[i] != float('inf') and
                   dis_to_source[j] > dis_to_source[i] + g
                   for i, row in enumerate(G) for j, g in enumerate(row))

    # Uses Bellman-ford to find negative weight cycle.
    return bellman_ford(
        [[-math.log10(edge) for edge in edge_list] for edge_list in G], 0)
# @exclude


def main():
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 100)
    G = [[0.0] * n for _ in range(n)]
    # Assume the input is a complete graph.
    for i in range(len(G)):
        G[i][i] = 1
        for j in range(i + 1, len(G)):
            G[i][j] = random.random()
            G[j][i] = 1.0 / G[i][j]
    res = is_arbitrage_exist(G)
    print(res)
    g = [[1.0, 2.0, 1.0], [0.5, 1.0, 4.0], [1.0, 0.25, 1.0]]
    res = is_arbitrage_exist(g)
    assert res
    print(is_arbitrage_exist(g))


if __name__ == '__main__':
    main()
