from binary_tree_prototype import BinaryTreeNode


# @include
def find_first_greater_than_k(tree, k):
    subtree, first_so_far = tree, None
    while subtree:
        if subtree.data > k:
            first_so_far, subtree = subtree, subtree.left
        else:  # Root and all keys in left subtree are <= k, so skip them.
            subtree = subtree.right
    return first_so_far
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 7
    root = BinaryTreeNode(3)
    root.left = BinaryTreeNode(2)
    root.left.left = BinaryTreeNode(1)
    root.right = BinaryTreeNode(5)
    root.right.left = BinaryTreeNode(4)
    root.right.right = BinaryTreeNode(7)
    assert find_first_greater_than_k(root, 1) is root.left
    assert find_first_greater_than_k(root, 5) is root.right.right
    assert find_first_greater_than_k(root, 6) is root.right.right
    assert not find_first_greater_than_k(root, 7)


if __name__ == '__main__':
    main()
