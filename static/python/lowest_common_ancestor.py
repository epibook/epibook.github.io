from binary_tree_with_parent_prototype import BinaryTreeNode


# @include
def lca(node_0, node_1):
    def get_depth(node):
        depth = 0
        while node:
            depth += 1
            node = node.parent
        return depth

    depth_0, depth_1 = get_depth(node_0), get_depth(node_1)
    # Makes node_0 as the deeper node in order to simplify the code.
    if depth_1 > depth_0:
        node_0, node_1 = node_1, node_0

    # Ascends from the deeper node.
    depth_diff = abs(depth_0 - depth_1)
    while depth_diff:
        node_0 = node_0.parent
        depth_diff -= 1

    # Now ascends both nodes until we reach the LCA.
    while node_0 is not node_1:
        node_0, node_1 = node_0.parent, node_1.parent
    return node_0
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    root = BinaryTreeNode(3, None, None, None)
    root.left = BinaryTreeNode(2, None, None, root)
    root.left.left = BinaryTreeNode(1, None, None, root.left)
    root.right = BinaryTreeNode(5, None, None, root)
    root.right.left = BinaryTreeNode(4, None, None, root.right)
    root.right.right = BinaryTreeNode(6, None, None, root.right)

    # should output 3
    assert lca(root.left, root.right).data == 3
    print(lca(root.left, root.right).data)
    # should output 5
    assert lca(root.right.left, root.right.right).data == 5
    print(lca(root.right.left, root.right.right).data)
    # should output 3
    assert lca(root.left, root.right.left).data == 3
    print(lca(root.left, root.right.left).data)
    # should output 2
    assert lca(root.left, root.left.left).data == 2
    print(lca(root.left, root.left.left).data)


if __name__ == '__main__':
    main()
