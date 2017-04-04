import sys
import random
import collections


# @include
class GraphVertex:

    def __init__(self, label):
        self.label = label
        self.edges = []
# @exclude

    def __repr__(self):
        return '%d(%s)' % (self.label, ','.join(
            str(x.label) for x in self.edges))


# @include


def clone_graph(G):
    if not G:
        return None

    q = collections.deque([G])
    vertex_map = {G: GraphVertex(G.label)}
    while q:
        v = q.popleft()
        for e in v.edges:
            # Try to copy vertex e.
            if e not in vertex_map:
                vertex_map[e] = GraphVertex(e.label)
                q.append(e)
            # Copy edge v->e.
            vertex_map[v].edges.append(vertex_map[e])
    return vertex_map[G]
# @exclude


def copy_labels(edges):
    return [e.label for e in edges]


def check_graph(node, G):
    vertex_set = set()
    q = collections.deque()
    q.append(node)
    vertex_set.add(node)
    while q:
        vertex = q.popleft()
        assert vertex.label < len(G)
        label1 = copy_labels(vertex.edges)
        label2 = copy_labels(G[vertex.label].edges)
        label1.sort()
        label2.sort()
        assert label1 == label2
        for e in vertex.edges:
            if e not in vertex_set:
                vertex_set.add(e)
                q.append(e)


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(2, 101)
        G = [GraphVertex(i) for i in range(n)]
        m = random.randint(1, n * (n - 1) // 2)
        is_edge_exist = [[False] * n for i in range(n)]
        # Make the graph become connected.
        for i in range(1, n):
            G[i - 1].edges.append(G[i])
            G[i].edges.append(G[i - 1])
            is_edge_exist[i - 1][i] = is_edge_exist[i][i - 1] = True

        # Generate some edges randomly.
        m -= (n - 1)
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
        res = clone_graph(G[0])
        check_graph(res, G)


if __name__ == '__main__':
    main()
