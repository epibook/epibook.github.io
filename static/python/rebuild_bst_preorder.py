from binary_tree_prototype import BinaryTreeNode


# @include
def rebuild_bst_from_preorder(preorder_sequence):
    if not preorder_sequence:
        return None

    transition_point = next(
        (i for i, a in enumerate(preorder_sequence)
         if a > preorder_sequence[0]), len(preorder_sequence))
    return BinaryTreeNode(
        preorder_sequence[0],
        rebuild_bst_from_preorder(preorder_sequence[1:transition_point]),
        rebuild_bst_from_preorder(preorder_sequence[transition_point:]))
# @exclude


def check_ans(n, pre):
    if n:
        check_ans(n.left, pre)
        assert pre <= n.data
        print(n.data)
        check_ans(n.right, n.data)


def main():
    #      3
    #    2   5
    #  1    4 6
    # should output 1, 2, 3, 4, 5, 6
    # preorder [3, 2, 1, 5, 4, 6]
    preorder = [3, 2, 1, 5, 4, 6]
    tree = rebuild_bst_from_preorder(preorder)
    check_ans(tree, float('-inf'))
    assert 3 == tree.data
    assert 2 == tree.left.data
    assert 1 == tree.left.left.data
    assert 5 == tree.right.data
    assert 4 == tree.right.left.data
    assert 6 == tree.right.right.data


if __name__ == '__main__':
    main()
