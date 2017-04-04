import collections


# @include
class TreeNode:
    def __init__(self):
        self.edges = []


Edge = collections.namedtuple('Edge', ('root', 'length'))


def compute_diameter(T):
    HeightAndDiameter = collections.namedtuple('HeightAndDiameter',
                                               ('height', 'diameter'))

    def compute_height_and_diameter(r):
        diameter = float('-inf')
        heights = [0.0, 0.0]  # Stores the max two heights.
        for e in r.edges:
            h_d = compute_height_and_diameter(e.root)
            if h_d.height + e.length > heights[0]:
                heights = [h_d.height + e.length, heights[0]]
            elif h_d.height + e.length > heights[1]:
                heights[1] = h_d.height + e.length
            diameter = max(diameter, h_d.diameter)
        return HeightAndDiameter(heights[0],
                                 max(diameter, heights[0] + heights[1]))

    return compute_height_and_diameter(T).diameter if T else 0.0


# @exclude


def main():
    r = None
    assert 0.0 == compute_diameter(r)
    r = TreeNode()
    r.edges.append(Edge(TreeNode(), 10))
    r.edges[0].root.edges.append(Edge(TreeNode(), 50))
    r.edges.append(Edge(TreeNode(), 20))
    assert 80 == compute_diameter(r)
    print(compute_diameter(r))
    r.edges[0].root.edges.append(Edge(TreeNode(), 100))
    assert 150 == compute_diameter(r)
    print(compute_diameter(r))


if __name__ == '__main__':
    main()
