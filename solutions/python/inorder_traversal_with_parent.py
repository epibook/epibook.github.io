from binary_tree_with_parent_prototype import BinaryTreeNode


# @include
def inorder_traversal(tree):
    prev, result = None, []
    while tree:
        if prev is tree.parent:
            # We came down to tree from prev.
            if tree.left:  # Keep going left.
                next = tree.left
            else:
                result.append(tree.data)
                # Done with left, so go right if right is not empty. Otherwise,
                # go up.
                next = tree.right or tree.parent
        elif tree.left is prev:
            # We came up to tree from its left child.
            result.append(tree.data)
            # Done with left, so go right if right is not empty. Otherwise, go
            # up.
            next = tree.right or tree.parent
        else:  # Done with both children, so move up.
            next = tree.parent

        prev, tree = tree, next
    return result
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    root = BinaryTreeNode(3)
    root.parent = None
    assert inorder_traversal(root) == [3]
    root.left = BinaryTreeNode(2)
    root.left.parent = root
    root.left.left = BinaryTreeNode(1)
    root.left.left.parent = root.left
    assert inorder_traversal(root) == [1, 2, 3]

    root.right = BinaryTreeNode(5)
    root.right.parent = root
    root.right.left = BinaryTreeNode(4)
    root.right.left.parent = root.right
    root.right.right = BinaryTreeNode(6)
    root.right.right.parent = root.right

    assert inorder_traversal(root) == [1, 2, 3, 4, 5, 6]


if __name__ == '__main__':
    main()
