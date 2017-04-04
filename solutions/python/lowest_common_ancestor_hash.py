from binary_tree_with_parent_prototype import BinaryTreeNode


# @include
def lca(node_0, node_1):
    iter_0, iter_1 = node_0, node_1
    nodes_on_path_to_root = set()
    while iter_0 or iter_1:
        # Ascend tree in tandem for these two nodes.
        if iter_0:
            if iter_0 in nodes_on_path_to_root:
                return iter_0
            nodes_on_path_to_root.add(iter_0)
            iter_0 = iter_0.parent
        if iter_1:
            if iter_1 in nodes_on_path_to_root:
                return iter_1
            nodes_on_path_to_root.add(iter_1)
            iter_1 = iter_1.parent
    raise ValueError('node_0 and node_1 are not in the same tree')
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    root = BinaryTreeNode(3)
    root.parent = None
    root.left = BinaryTreeNode(2)
    root.left.parent = root
    root.left.left = BinaryTreeNode(1)
    root.left.left.parent = root.left
    root.right = BinaryTreeNode(5)
    root.right.parent = root
    root.right.left = BinaryTreeNode(4)
    root.right.left.parent = root.right
    root.right.right = BinaryTreeNode(6)
    root.right.right.parent = root.right

    # should output 3
    assert lca(root.left, root.right).data == 3
    print(lca(root.left, root.right).data)
    # should output 5
    assert lca(root.right.left, root.right.right).data == 5
    print(lca(root.right.left, root.right.right).data)


if __name__ == '__main__':
    main()
