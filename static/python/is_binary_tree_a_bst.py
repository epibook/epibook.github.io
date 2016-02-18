# is_binary_tree_a_BST.cpp dd7adc0dbf96e35e29defe6d31337c155c450a2f
from binary_tree_prototype import BinaryTreeNode


# @include
def is_binary_tree_BST(tree):
    return are_keys_in_range(tree, float('-inf'), float('inf'))

def are_keys_in_range(tree, low_range, high_range):
    if tree is None:
        return True
    elif not low_range < tree.data < high_range:
        return False
    return (are_keys_in_range(tree.left, low_range, tree.data) and
            are_keys_in_range(tree.right, tree.data, high_range))
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
    # should output True.
    assert is_binary_tree_BST(tree) == True
    print(is_binary_tree_BST(tree))
    #      10
    #    2   5
    #  1    4 6
    tree.data = 10
    # should output False.
    assert not is_binary_tree_BST(tree)
    print(is_binary_tree_BST(tree))
    # should output True.
    assert is_binary_tree_BST(None) == True
    print(is_binary_tree_BST(None))


if __name__ == '__main__':
    main()
