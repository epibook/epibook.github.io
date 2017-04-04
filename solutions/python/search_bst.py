from bst_prototype import BSTNode


# @include
def search_bst(tree, key):
    return (tree if not tree or tree.data == key else search_bst(tree.left, key)
            if key < tree.data else search_bst(tree.right, key))
# @exclude


def main():
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
    assert tree is search_bst(tree, 43)
    assert tree.left is search_bst(tree, 23)
    assert tree.right is search_bst(tree, 47)
    assert tree.right.right is search_bst(tree, 53)
    assert tree.left.right.left is search_bst(tree, 29)
    assert search_bst(tree, 1000) is None


if __name__ == '__main__':
    main()
