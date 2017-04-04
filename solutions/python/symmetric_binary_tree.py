from binary_tree_prototype import BinaryTreeNode


# @include
def is_symmetric(tree):
    def check_symmetric(subtree_0, subtree_1):
        if not subtree_0 and not subtree_1:
            return True
        elif subtree_0 and subtree_1:
            return (subtree_0.data == subtree_1.data and check_symmetric(
                subtree_0.left, subtree_1.right) and check_symmetric(
                    subtree_0.right, subtree_1.left))
        # One subtree is empty, and the other is not.
        return False

    return not tree or check_symmetric(tree.left, tree.right)
# @exclude


def simple_test():
    symm_tree = BinaryTreeNode()
    assert is_symmetric(symm_tree)
    symm_tree.left = BinaryTreeNode()
    assert not is_symmetric(symm_tree)
    symm_tree.right = BinaryTreeNode()
    assert is_symmetric(symm_tree)
    symm_tree.right.right = BinaryTreeNode()
    assert not is_symmetric(symm_tree)


def main():
    simple_test()
    # Non symmetric tree test.
    #      3
    #    2   5
    #  1    4 6
    non_symm_tree = BinaryTreeNode()
    non_symm_tree.left = BinaryTreeNode()
    non_symm_tree.left.left = BinaryTreeNode()
    non_symm_tree.right = BinaryTreeNode()
    non_symm_tree.right.left = BinaryTreeNode()
    non_symm_tree.right.right = BinaryTreeNode()
    assert not is_symmetric(non_symm_tree)
    print(is_symmetric(non_symm_tree))
    # Symmetric tree test.
    symm_tree = BinaryTreeNode()
    symm_tree.left = BinaryTreeNode()
    symm_tree.right = BinaryTreeNode()
    assert is_symmetric(symm_tree)
    print(is_symmetric(symm_tree))
    # Empty tree test.
    symm_tree = None
    assert is_symmetric(symm_tree)
    print(is_symmetric(symm_tree))


if __name__ == '__main__':
    main()
