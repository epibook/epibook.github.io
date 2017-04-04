from binary_tree_prototype import BinaryTreeNode
from reconstruct_preorder_with_null import reconstruct_preorder


# @include
def exterior_binary_tree(tree):
    def is_leaf(node):
        return not node.left and not node.right

    # Computes the nodes from the root to the leftmost leaf followed by all
    # the leaves in subtree.
    def left_boundary_and_leaves(subtree, is_boundary):
        if not subtree:
            return []
        return (([subtree] if is_boundary or is_leaf(subtree) else []) +
                left_boundary_and_leaves(subtree.left, is_boundary) +
                left_boundary_and_leaves(subtree.right, is_boundary and
                                         not subtree.left))

    # Computes the leaves in left-to-right order followed by the rightmost
    # leaf to the root path in subtree.
    def right_boundary_and_leaves(subtree, is_boundary):
        if not subtree:
            return []
        return (right_boundary_and_leaves(subtree.left, is_boundary and
                                          not subtree.right) +
                right_boundary_and_leaves(subtree.right, is_boundary) +
                ([subtree] if is_boundary or is_leaf(subtree) else []))

    return ([tree] + left_boundary_and_leaves(tree.left, True) +
            right_boundary_and_leaves(tree.right, True) if tree else [])
# @exclude


def create_output_list(L):
    return [l.data for l in L]


def simple_test():
    # The example in the book.
    A = [314, 6, 271, 28, 0, 561, 3, 17, 6, 2, 1, 401, 641, 257, 271, 28]
    tree = reconstruct_preorder([
        A[0], A[1], A[2], A[3], None, None, A[4], None, None, A[5], None, A[6],
        A[7], None, None, None, A[8], A[9], None, A[10], A[11], None, A[12],
        None, None, A[13], None, None, A[14], None, A[15], None, None
    ])
    assert create_output_list(exterior_binary_tree(
        tree)) == [314, 6, 271, 28, 0, 17, 641, 257, 28, 271, 6]

    tree.left.left = None
    assert create_output_list(exterior_binary_tree(
        tree)) == [314, 6, 561, 3, 17, 641, 257, 28, 271, 6]

    tree.right.right = None
    assert create_output_list(
        exterior_binary_tree(tree)) == [314, 6, 561, 3, 17, 641, 257, 1, 2, 6]

    tree.right = None
    assert create_output_list(
        exterior_binary_tree(tree)) == [314, 6, 561, 3, 17]


def main():
    simple_test()
    #        3
    #    2      5
    #  1  0    4 6
    #   -1 -2
    tree = BinaryTreeNode(3)
    assert create_output_list(exterior_binary_tree(tree)) == [3]

    tree.left = BinaryTreeNode(2)
    assert create_output_list(exterior_binary_tree(tree)) == [3, 2]

    tree.left.right = BinaryTreeNode(0)
    tree.left.right.left = BinaryTreeNode(-1)
    tree.left.right.right = BinaryTreeNode(-2)
    tree.left.left = BinaryTreeNode(1)
    tree.right = BinaryTreeNode(5)
    tree.right.left = BinaryTreeNode(4)
    tree.right.right = BinaryTreeNode(6)
    assert create_output_list(
        exterior_binary_tree(tree)) == [3, 2, 1, -1, -2, 4, 6, 5]


if __name__ == '__main__':
    main()
