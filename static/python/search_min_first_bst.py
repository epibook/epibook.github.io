from binary_tree_prototype import BinaryTreeNode


# @include
def search_min_first_bst(min_first_bst, k):
    # First handle the base cases.
    if not min_first_bst or min_first_bst.data > k:
        return False
    elif min_first_bst.data == k:
        return True

    # Recursively search just the right subtree if the smallest key in the
    # right subtree is greater than or equal to k.
    if min_first_bst.right and k >= min_first_bst.right.data:
        return search_min_first_bst(min_first_bst.right, k)
    return search_min_first_bst(min_first_bst.left, k)
# @exclude


def main():
    # A min-first BST
    #      1
    #    2   4
    #  3    5 7
    tree = BinaryTreeNode(1)
    tree.left = BinaryTreeNode(2)
    tree.left.left = BinaryTreeNode(3)
    tree.right = BinaryTreeNode(4)
    tree.right.left = BinaryTreeNode(5)
    tree.right.right = BinaryTreeNode(7)
    assert search_min_first_bst(tree, 1)
    assert search_min_first_bst(tree, 3)
    assert search_min_first_bst(tree, 5)
    assert not search_min_first_bst(tree, 6)


if __name__ == '__main__':
    main()
