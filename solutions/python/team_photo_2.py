# @include
class GraphVertex:
    def __init__(self):
        self.edges = []
        self.max_distance = 0
# @exclude

    def __repr__(self):
        return ('*' if self.max_distance else '') + '(%d)%d(%s)' % (
            self.max_distance, id(self), ','.join(
                str(id(x)) for x in self.edges))


# @include


def find_largest_number_teams(G):
    def build_topological_ordering():
        def dfs(cur):
            cur.max_distance = 1
            for next in cur.edges:
                if not next.max_distance:
                    dfs(next)
            vertex_order.append(cur)

        vertex_order = []
        for g in G:
            if not g.max_distance:
                dfs(g)
        return vertex_order

    def find_longest_path(vertex_order):
        max_distance = 0
        while vertex_order:
            u = vertex_order.pop()
            max_distance = max(max_distance, u.max_distance)
            for v in u.edges:
                v.max_distance = max(v.max_distance, u.max_distance + 1)
        return max_distance

    return find_longest_path(build_topological_ordering())


# @exclude


def main():
    G = [GraphVertex() for _ in range(3)]
    G[0].edges.append(G[2])
    G[1].edges.append(G[2])
    assert 2 == find_largest_number_teams(G)


if __name__ == '__main__':
    main()
