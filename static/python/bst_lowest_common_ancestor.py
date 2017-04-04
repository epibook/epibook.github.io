from binary_tree_prototype import BinaryTreeNode


# @include
# Input nodes are not nonempty and the key at s is less than or equal to
# that at b.
def find_LCA(tree, s, b):
    while tree.data < s.data or tree.data > b.data:
        # Keep searching since tree is outside of [s, b].
        while tree.data < s.data:
            tree = tree.right  # LCA must be in tree's right child.
        while tree.data > b.data:
            tree = tree.left  # LCA must be in tree's left child.
    # Now, s.data <= tree.data && tree.data <= b.data.
    return tree
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    tree = BinaryTreeNode(3)
    tree.left = BinaryTreeNode(2)
    tree.left.left = BinaryTreeNode(1)
    tree.right = BinaryTreeNode(5)
    tree.right.left = BinaryTreeNode(4)
    tree.right.right = BinaryTreeNode(6)
    assert 3 == find_LCA(tree, tree.left.left, tree.right.left).data
    assert 5 == find_LCA(tree, tree.right.left, tree.right.right).data
    assert 2 == find_LCA(tree, tree.left.left, tree.left).data


if __name__ == '__main__':
    main()
