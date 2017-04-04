# k-balanced_binary_tree.cpp b4b3a70d8ab942579f85b4416f980d05831af969
from binary_tree_prototype import BinaryTreeNode


# @include
def find_k_unbalanced_node(tree, k):
    return find_k_unbalanced_node_helper(tree, k)[0]


# If there is any k-unbalanced node in tree, the first value of the return
# value is a k-unbalanced node; otherwise, null.  If the first is not null,
# the second value of the return value is the number of nodes in tree.
def find_k_unbalanced_node_helper(tree, k):
    if not tree:
        return (None, 0)  # Base case.

    # Early return if left subtree is not k-balanced.
    left_result = find_k_unbalanced_node_helper(tree.left, k)
    if left_result[0]:
        return (left_result[0], 0)
    # Early return if right subtree is not k-balanced.
    right_result = find_k_unbalanced_node_helper(tree.right, k)
    if right_result[0]:
        return (right_result[0], 0)

    node_num = left_result[1] + right_result[1] + 1
    if abs(left_result[1] - right_result[1]) > k:
        # tree is not k-balanced but its children are k-balanced.
        return (tree, node_num)
    return (None, node_num)


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
    k = 0
    ans = find_k_unbalanced_node(root, k)
    assert ans.data == 2
    if ans:
        print(ans.data)


if __name__ == '__main__':
    main()
