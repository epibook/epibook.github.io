from binary_tree_prototype import BinaryTreeNode


# @include
def find_first_equal_k(tree, k):
    if not tree:
        return None  # No match.
    elif tree.data == k:
        # Recursively search the left subtree for first node containing k.
        node = find_first_equal_k(tree.left, k)
        return node or tree
    # Search the left or right subtree based on relative values of tree.data
    # and k.
    return find_first_equal_k(tree.right if tree.data < k else tree.left, k)
# @exclude


def main():
    #      3
    #    2   6
    #  1    4 6
    root = BinaryTreeNode(3)
    root.left = BinaryTreeNode(2)
    root.left.left = BinaryTreeNode(1)
    root.right = BinaryTreeNode(6)
    root.right.left = BinaryTreeNode(4)
    root.right.right = BinaryTreeNode(6)
    assert not find_first_equal_k(root, 7)
    assert (find_first_equal_k(root, 6).data == 6 and
            find_first_equal_k(root, 6).right.data == 6)

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
