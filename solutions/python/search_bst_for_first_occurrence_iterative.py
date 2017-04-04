from binary_tree_prototype import BinaryTreeNode


# @include
def find_first_equal_k(tree, k):
    first_so_far = None
    curr = tree
    while curr:
        if curr.data < k:
            curr = curr.right
        elif curr.data > k:
            curr = curr.left
        else:  # curr.data == k.
            # Record this node, and search for the first node in the left
            # subtree.
            first_so_far = curr
            curr = curr.left
    return first_so_far
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    root = BinaryTreeNode(3)
    root.left = BinaryTreeNode(2)
    root.left.left = BinaryTreeNode(1)
    root.right = BinaryTreeNode(5)
    root.right.left = BinaryTreeNode(4)
    root.right.right = BinaryTreeNode(6)
    assert not find_first_equal_k(root, 7)
    assert find_first_equal_k(root, 6).data == 6

    #      3
    #    3   5
    #  2    5 6
    root = BinaryTreeNode(3)
    root.left = BinaryTreeNode(3)
    root.left.left = BinaryTreeNode(2)
    root.right = BinaryTreeNode(5)
    root.right.left = BinaryTreeNode(5)
    root.right.right = BinaryTreeNode(6)
    assert not find_first_equal_k(root, 7)
    assert find_first_equal_k(root, 3) is root.left
    assert find_first_equal_k(root, 5) is root.right.left
    assert find_first_equal_k(root, 6).data == 6


if __name__ == '__main__':
    main()
