import collections
from binary_tree_prototype import BinaryTreeNode


# @include
def is_binary_tree_bst(tree):
    QueueEntry = collections.namedtuple('QueueEntry',
                                        ('node', 'lower', 'upper'))
    bfs_queue = collections.deque(
        [QueueEntry(tree, float('-inf'), float('inf'))])

    while bfs_queue:
        front = bfs_queue.popleft()
        if front.node:
            if not front.lower <= front.node.data <= front.upper:
                return False
            bfs_queue += [
                QueueEntry(front.node.left, front.lower, front.node.data),
                QueueEntry(front.node.right, front.node.data, front.upper)
            ]
    return True
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    tree = BinaryTreeNode(3)
    tree.left = BinaryTreeNode(2)
    tree.left.left = BinaryTreeNode(1)
    tree.right = BinaryTreeNode(5)
    tree.right.left = BinaryTreeNode(4)
    tree.right.right = BinaryTreeNode(6)
    # should output True.
    assert is_binary_tree_bst(tree)
    print(is_binary_tree_bst(tree))
    #      10
    #    2   5
    #  1    4 6
    tree.data = 10
    # should output False.
    assert not is_binary_tree_bst(tree)
    print(is_binary_tree_bst(tree))
    # should output True.
    assert is_binary_tree_bst(None)
    print(is_binary_tree_bst(None))


if __name__ == '__main__':
    main()
