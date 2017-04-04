import sys
import random


# @include
class GraphVertex:

    white, gray, black = range(3)

    def __init__(self):
        self.color = GraphVertex.white
        self.edges = []
# @exclude

    def __repr__(self):
        return '(%d)%d(%s)' % (self.color, id(self), ','.join(
            str(id(x)) for x in self.edges))


# @include


def is_deadlocked(G):
    def has_cycle(cur):
        # Visiting a gray vertex means a cycle.
        if cur.color == GraphVertex.gray:
            return True

        cur.color = GraphVertex.gray  # Marks current vertex as a gray one.
        # Traverse the neighbor vertices.
        if any(next.color != GraphVertex.black and has_cycle(next)
               for next in cur.edges):
            return True
        cur.color = GraphVertex.black  # Marks current vertex as black.
        return False

    return any(vertex.color == GraphVertex.white and has_cycle(vertex)
               for vertex in G)
# @exclude


def has_cycle_exclusion(cur):
    if cur.color == GraphVertex.black:
        return True
    cur.color = GraphVertex.black
    return any(has_cycle_exclusion(next) for next in cur.edges)


# O(n^2) check answer.
def check_answer(G):
    # marks all vertices as white.
    for n in G:
        n.color = GraphVertex.white

    for g in G:
        if has_cycle_exclusion(g):
            return True
        # Reset color to white.
        for n in G:
            n.color = GraphVertex.white
    return False


def test_two_nodes_cycle():
    G = [GraphVertex() for _ in range(2)]
    G[0].edges.append(G[1])
    G[1].edges.append(G[0])
    result = is_deadlocked(G)
    print(result)
    assert check_answer(G) == result
    assert result


def test_directed_cycle():
    G = [GraphVertex() for _ in range(3)]
    G[0].edges.append(G[1])
    G[1].edges.append(G[2])
    G[2].edges.append(G[0])
    result = is_deadlocked(G)
    print(result)
    assert check_answer(G) == result
    assert result


def test_directed_star_tree():
    G = [GraphVertex() for _ in range(4)]
    G[0].edges.append(G[1])
    G[0].edges.append(G[2])
    G[0].edges.append(G[3])
    result = is_deadlocked(G)
    print(result)
    assert check_answer(G) == result
    assert not result


def test_directed_line_tree():
    G = [GraphVertex() for _ in range(4)]
    G[0].edges.append(G[1])
    G[1].edges.append(G[2])
    G[2].edges.append(G[3])
    result = is_deadlocked(G)
    print(result)
    assert check_answer(G) == result
    assert not result
    G[3].edges.append(G[1])
    result = is_deadlocked(G)
    assert result


def test_directed_binary_tree():
    G = [GraphVertex() for _ in range(7)]
    G[0].edges.append(G[1])
    G[0].edges.append(G[2])
    G[1].edges.append(G[3])
    G[1].edges.append(G[4])
    G[2].edges.append(G[5])
    G[2].edges.append(G[6])
    result = is_deadlocked(G)
    print(result)
    assert check_answer(G) == result
    assert not result
    G[4].edges.append(G[6])
    G[6].edges.append(G[1])
    result = is_deadlocked(G)
    assert result


def test_directed_two_separate_cycles():
    G = [GraphVertex() for _ in range(6)]
    G[0].edges.append(G[1])
    G[1].edges.append(G[2])
    G[2].edges.append(G[0])
    G[3].edges.append(G[4])
    G[4].edges.append(G[5])
    G[5].edges.append(G[3])
    result = is_deadlocked(G)
    assert result


def main():
    test_two_nodes_cycle()
    test_directed_cycle()
    test_directed_star_tree()
    test_directed_line_tree()
    test_directed_binary_tree()
    test_directed_two_separate_cycles()
    for _ in range(100):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(2, 2000)
        G = [GraphVertex() for _ in range(n)]
        m = random.randint(1, n * (n - 1) // 2)
        is_edge_exist = [[False] * n for _ in range(n)]
        # Make the graph become connected.
        for i in range(1, n):
            G[i - 1].edges.append(G[i])
            G[i].edges.append(G[i - 1])
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
            G[a].edges.append(G[b])
            G[b].edges.append(G[a])

        result = is_deadlocked(G)
        print(result)
        assert check_answer(G) == result


if __name__ == '__main__':
    main()
