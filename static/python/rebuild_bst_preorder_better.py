from binary_tree_prototype import BinaryTreeNode


# @include
def rebuild_bst_from_preorder(preorder_sequence):
    def rebuild_bst_from_preorder_on_value_range(lower_bound, upper_bound):
        if root_idx[0] == len(preorder_sequence):
            return None

        root = preorder_sequence[root_idx[0]]
        if not lower_bound <= root <= upper_bound:
            return None
        root_idx[0] += 1
        # Note that rebuild_bst_from_preorder_on_value_range updates root_idx[0].
        # So the order of following two calls are critical.
        left_subtree = rebuild_bst_from_preorder_on_value_range(lower_bound,
                                                                root)
        right_subtree = rebuild_bst_from_preorder_on_value_range(root,
                                                                 upper_bound)
        return BinaryTreeNode(root, left_subtree, right_subtree)

    root_idx = [0]  # Tracks current subtree.
    return rebuild_bst_from_preorder_on_value_range(float('-inf'), float('inf'))
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
