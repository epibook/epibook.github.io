import bintrees
import sys
import random
import collections

# @include
DistanceWithFewestEdges = collections.namedtuple('DistanceWithFewestEdges',
                                                 ('distance', 'min_num_edges'))
VertexWithDistance = collections.namedtuple('VertexWithDistance',
                                            ('vertex', 'distance'))


class GraphVertex:
    def __init__(self, id=0):
        self.distance_with_fewest_edges = DistanceWithFewestEdges(
            float('inf'), 0)
        self.edges = []
        self.id = id  # The id of this vertex.
        self.pred = None  # The predecessor in the shortest path.

    def __lt__(self, other):
        if self.distance_with_fewest_edges != other.distance_with_fewest_edges:
            return self.distance_with_fewest_edges < other.distance_with_fewest_edges
        return self.id < other.id
# @exclude

    def __repr__(self):
        return 'id=%d,distance_with_fewest_edges=%s,edge=%s' % (
            self.id, str(self.distance_with_fewest_edges),
            ','.join('%s(%d)' % (x.vertex.id, x.distance) for x in self.edges))


# @include


def dijkstra_shortest_path(s, t):
    # Initialization of the distance of starting point.
    s.distance_with_fewest_edges = DistanceWithFewestEdges(0, 0)
    node_set = bintrees.RBTree([(s, None)])

    while node_set:
        # Extracts the minimum distance vertex from heap.
        u = node_set.pop_min()[0]
        if u.id == t.id:
            break

        # Relax neighboring vertices of u.
        for v in u.edges:
            v_distance = u.distance_with_fewest_edges.distance + v.distance
            v_num_edges = u.distance_with_fewest_edges.min_num_edges + 1
            new_distance = DistanceWithFewestEdges(v_distance, v_num_edges)
            if v.vertex.distance_with_fewest_edges > new_distance:
                node_set.discard(v.vertex)
                v.vertex.pred = u
                v.vertex.distance_with_fewest_edges = new_distance
                node_set.insert(v.vertex, None)

    def output_shortest_path(v):
        if v:
            output_shortest_path(v.pred)
            print(v.id, end=' ')

    # Outputs the shortest path with fewest edges.
    output_shortest_path(t)
# @exclude


# DBH test
def test():
    G = [GraphVertex(i) for i in range(9)]

    # G[0] is the source node that connects to 8 other nodes.
    G[0].edges.append(VertexWithDistance(G[1], 13))  # 0-1
    G[1].edges.append(VertexWithDistance(G[0], 13))  # 1-0

    G[0].edges.append(VertexWithDistance(G[2], 24))  # 0-2
    G[2].edges.append(VertexWithDistance(G[0], 24))  # 2-0

    G[0].edges.append(VertexWithDistance(G[3], 28))  # 0-3
    G[3].edges.append(VertexWithDistance(G[0], 28))  # 3-0

    G[0].edges.append(VertexWithDistance(G[4], 25))  # 0-4
    G[4].edges.append(VertexWithDistance(G[0], 25))  # 4-0

    G[0].edges.append(VertexWithDistance(G[5], 30))  # 0-5
    G[5].edges.append(VertexWithDistance(G[0], 30))  # 5-0

    G[0].edges.append(VertexWithDistance(G[6], 31))  # 0-6
    G[6].edges.append(VertexWithDistance(G[0], 31))  # 6-0

    G[0].edges.append(VertexWithDistance(G[7], 10))  # 0-7
    G[7].edges.append(VertexWithDistance(G[0], 10))  # 7-0

    G[0].edges.append(VertexWithDistance(G[8], 29))  # 0-8
    G[8].edges.append(VertexWithDistance(G[0], 29))  # 8-0

    G[1].edges.append(VertexWithDistance(G[8], 7))  # 1-8
    G[8].edges.append(VertexWithDistance(G[1], 7))  # 8-1

    G[2].edges.append(VertexWithDistance(G[8], 1))  # 2-8
    G[8].edges.append(VertexWithDistance(G[2], 1))  # 8-2

    G[7].edges.append(VertexWithDistance(G[8], 16))  # 7-8
    G[8].edges.append(VertexWithDistance(G[7], 16))  # 8-7

    s = 0  # Source is G[0].
    t = 2  # Destination is G[2].

    # Minimum distance path should be:
    # G[0] => G[1] => G[8] => G[2],
    # distance is: 13 + 7 + 1 = 21.

    dijkstra_shortest_path(G[s], G[t])
    print('\nmin distance:', G[t].distance_with_fewest_edges.distance)
    assert G[t].distance_with_fewest_edges.distance == 21
    print('number of edges:', G[t].distance_with_fewest_edges.min_num_edges)
    assert G[t].distance_with_fewest_edges.min_num_edges == 3


def main():
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
    else:
        n = random.randint(2, 1000)
    G = [GraphVertex(i) for i in range(n)]
    m = random.randint(1, n * (n - 1) // 2)
    is_edge_exist = [[False] * n for i in range(n)]
    # Make the graph as connected.
    for i in range(1, n):
        length = random.randint(1, 100)
        G[i - 1].edges.append(VertexWithDistance(G[i], length))
        G[i].edges.append(VertexWithDistance(G[i - 1], length))
        is_edge_exist[i - 1][i] = is_edge_exist[i][i - 1] = True

    # Generate edges randomly.
    m -= (n - 1)
    while m > 0:
        m -= 1
        while True:
            a = random.randrange(n)
            b = random.randrange(n)
            if a != b and is_edge_exist[a][b] == False:
                break
        is_edge_exist[a][b] = is_edge_exist[b][a] = True
        length = random.randint(1, 100)
        G[a].edges.append(VertexWithDistance(G[b], length))
        G[b].edges.append(VertexWithDistance(G[a], length))
    s = random.randrange(n)
    t = random.randrange(n)
    print('source = %s, terminal = %s' % (s, t))
    dijkstra_shortest_path(G[s], G[t])
    print()
    print(G[t].distance_with_fewest_edges.distance,
          G[t].distance_with_fewest_edges.min_num_edges)
    test()


if __name__ == '__main__':
    main()
