from binary_tree_prototype import BinaryTreeNode
import collections


# @include
def is_balanced_binary_tree(tree):
    BalancedStatusWithHeight = collections.namedtuple(
        'BalancedStatusWithHeight', ('balanced', 'height'))

    # First value of the return value indicates if tree is balanced, and if
    # balanced the second value of the return value is the height of tree.
    def check_balanced(tree):
        if not tree:
            return BalancedStatusWithHeight(True, -1)  # Base case.

        left_result = check_balanced(tree.left)
        if not left_result.balanced:
            # Left subtree is not balanced.
            return BalancedStatusWithHeight(False, 0)

        right_result = check_balanced(tree.right)
        if not right_result.balanced:
            # Right subtree is not balanced.
            return BalancedStatusWithHeight(False, 0)

        is_balanced = abs(left_result.height - right_result.height) <= 1
        height = max(left_result.height, right_result.height) + 1
        return BalancedStatusWithHeight(is_balanced, height)
    return check_balanced(tree).balanced
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
    assert is_balanced_binary_tree(tree)
    print(is_balanced_binary_tree(tree))
    # Non-balanced binary tree test.
    tree = BinaryTreeNode()
    tree.left = BinaryTreeNode()
    tree.left.left = BinaryTreeNode()
    assert not is_balanced_binary_tree(tree)
    print(is_balanced_binary_tree(tree))


if __name__ == '__main__':
    main()
