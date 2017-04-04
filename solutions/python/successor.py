from binary_tree_with_parent_prototype import BinaryTreeNode


# @include
def find_successor(node):
    if node.right:
        # Successor is the leftmost element in node's right subtree.
        node = node.right
        while node.left:
            node = node.left
        return node

    # Find the closest ancestor whose left subtree contains node.
    while node.parent and node.parent.right is node:
        node = node.parent

    # A return value of None means node does not have successor, i.e., node is
    # the rightmost node in the tree.
    return node.parent
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    root = BinaryTreeNode(3)
    root.parent = None
    assert not find_successor(root)
    root.left = BinaryTreeNode(2)
    root.left.parent = root
    assert find_successor(root.left).data == 3

    root.left.left = BinaryTreeNode(1)
    root.left.left.parent = root.left
    assert find_successor(root.left).data == 3
    assert find_successor(root.left.left).data == 2

    root.right = BinaryTreeNode(5)
    root.right.parent = root
    root.right.left = BinaryTreeNode(4)
    root.right.left.parent = root.right
    root.right.right = BinaryTreeNode(6)
    root.right.right.parent = root.right
    # should output 6
    node = find_successor(root.right)
    assert 6 == node.data
    if node:
        print(node.data)
    else:
        print('null')
    # should output 'null'
    node = find_successor(root.right.right)
    assert not node
    if node:
        print(node.data)
    else:
        print('null')


if __name__ == '__main__':
    main()
