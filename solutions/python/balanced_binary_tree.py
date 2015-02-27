# Balanced_binary_tree.cpp 100b4adabfd008775520062bd407c3323ea646af
from binary_tree_prototype import BinaryTreeNode


# @include
def is_balanced_binary_tree(tree):
    return check_balanced(tree)[0]


# First value of the return value indicates if tree is balanced, if
# balanced the second value of the return value is the height of tree.
def check_balanced(tree):
    if tree is None:
        return (True, -1)  # Base case.

    left_result = check_balanced(tree.left)
    if not left_result[0]:
        return (False, 0)  # Left subtree is not balanced.

    right_result = check_balanced(tree.right)
    if not right_result[0]:
        return (False, 0)  # Right subtree is not balanced.

    is_balanced = abs(left_result[1] - right_result[1]) <= 1
    height = max(left_result[1], right_result[1]) + 1
    return (is_balanced, height)
# @exclude


def main():
    #  balanced binary tree test
    #      3
    #    2   5
    #  1    4 6
    tree = BinaryTreeNode()
    tree.left = BinaryTreeNode()
    tree.left.left = BinaryTreeNode()
    tree.right = BinaryTreeNode()
    tree.right.left = BinaryTreeNode()
    tree.right.right = BinaryTreeNode()
    assert is_balanced_binary_tree(tree) == True
    print(is_balanced_binary_tree(tree))
    # Non-balanced binary tree test.
    tree = BinaryTreeNode()
    tree.left = BinaryTreeNode()
    tree.left.left = BinaryTreeNode()
    assert is_balanced_binary_tree(tree) == False
    print(is_balanced_binary_tree(tree))


if __name__ == '__main__':
    main()
