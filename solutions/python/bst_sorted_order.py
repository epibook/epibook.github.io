from bst_prototype import BSTNode


# @include
def bst_in_sorted_order(tree):
    s, result = [], []

    while s or tree:
        if tree:
            s.append(tree)
            # Going left.
            tree = tree.left
        else:
            # Going up.
            tree = s.pop()
            result.append(tree.data)
            # Going right.
            tree = tree.right
    return result
# @exclude


def simple_test():
    tree = BSTNode(43)
    assert bst_in_sorted_order(tree) == [43]
    tree.left = BSTNode(23)
    assert bst_in_sorted_order(tree) == [23, 43]


def main():
    simple_test()
    #        43
    #    23     47
    #      37      53
    #    29  41
    #     31
    tree = BSTNode(43)
    tree.left = BSTNode(23)
    tree.left.right = BSTNode(37)
    tree.left.right.left = BSTNode(29)
    tree.left.right.left.right = BSTNode(31)
    tree.left.right.right = BSTNode(41)
    tree.right = BSTNode(47)
    tree.right.right = BSTNode(53)
    assert bst_in_sorted_order(tree) == [23, 29, 31, 37, 41, 43, 47, 53]


if __name__ == '__main__':
    main()
