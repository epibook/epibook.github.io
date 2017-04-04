from binary_tree_with_parent_prototype import BinaryTreeNode


def find_successor_bst(n):
    if n.right:
        # Find the smallest element in n's right subtree.
        n = n.right
        while n.left:
            n = n.left
        return n

    # Find the first parent which is larger than n.
    while n.parent and n.parent.right is n:
        n = n.parent

    # Return None means n is the largest in this BST.
    return n.parent or None


# @include
def range_query_on_bst(n, L, U):
    def find_first_larger_equal_k(r, k):
        if not r:
            return None
        elif r.data < k:
            # r.data < k so search the right subtree.
            return find_first_larger_equal_k(r.right, k)

        # Recursively search the left subtree for first one >= k.
        n = find_first_larger_equal_k(r.left, k)
        return n or r
    res = []
    it = find_first_larger_equal_k(n, L)
    while it and it.data <= U:
        res.append(it)
        it = find_successor_bst(it)
    return res
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    root = BinaryTreeNode(3)
    root.parent = None
    root.left = BinaryTreeNode(2)
    root.left.parent = root
    root.left.left = BinaryTreeNode(1)
    root.left.left.parent = root.left
    root.right = BinaryTreeNode(5)
    root.right.parent = root
    root.right.left = BinaryTreeNode(4)
    root.right.left.parent = root.right
    root.right.right = BinaryTreeNode(6)
    root.right.right.parent = root.right
    res = range_query_on_bst(root, 2, 5)
    assert len(res) == 4
    assert all(2 <= l.data <= 5 for l in res)
    res = range_query_on_bst(root, -1, 0)
    assert not res
    res = range_query_on_bst(root, 10, 25)
    assert not res
    res = range_query_on_bst(root, -10, 30)
    assert len(res) == 6
    assert all(1 <= l.data <= 6 for l in res)


if __name__ == '__main__':
    main()
