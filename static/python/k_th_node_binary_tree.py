class BinaryTreeNode:

    def __init__(self, data=None, left=None, right=None, size=None):
        self.data = data
        self.left = left
        self.right = right
        self.size = size


# @include
def find_kth_node_binary_tree(tree, k):
    while tree:
        left_size = tree.left.size if tree.left else 0
        if left_size + 1 < k:  # k-th node must be in right subtree of tree.
            k -= left_size + 1
            tree = tree.right
        elif left_size == k - 1:  # k-th is iter itself.
            return tree
        else:  # k-th node must be in left subtree of iter.
            tree = tree.left
    return None  # If k is between 1 and the tree size, this is unreachable.
# @exclude


def main():
    #  size field
    #      6
    #    2   3
    #  1    1 1
    #
    #  data field
    #      3
    #    2   5
    #  1    4 6
    root = BinaryTreeNode()
    root.size = 6
    root.data = 3
    root.left = BinaryTreeNode()
    root.left.size = 2
    root.left.data = 2
    root.left.left = BinaryTreeNode()
    root.left.left.size = 1
    root.left.left.data = 1
    root.right = BinaryTreeNode()
    root.right.size = 3
    root.right.data = 5
    root.right.left = BinaryTreeNode()
    root.right.left.size = 1
    root.right.left.data = 4
    root.right.right = BinaryTreeNode()
    root.right.right.size = 1
    root.right.right.data = 6
    assert None is find_kth_node_binary_tree(root, 0)
    # should output 1
    assert find_kth_node_binary_tree(root, 1).data == 1
    print((find_kth_node_binary_tree(root, 1)).data)
    # should output 2
    assert find_kth_node_binary_tree(root, 2).data == 2
    print((find_kth_node_binary_tree(root, 2)).data)
    # should output 3
    assert find_kth_node_binary_tree(root, 3).data == 3
    print((find_kth_node_binary_tree(root, 3)).data)
    # should output 4
    assert find_kth_node_binary_tree(root, 4).data == 4
    print((find_kth_node_binary_tree(root, 4)).data)
    # should output 5
    assert find_kth_node_binary_tree(root, 5).data == 5
    print((find_kth_node_binary_tree(root, 5)).data)
    # should output 6
    assert find_kth_node_binary_tree(root, 6).data == 6
    print((find_kth_node_binary_tree(root, 6)).data)
    assert None is find_kth_node_binary_tree(root, 7)


if __name__ == '__main__':
    main()
