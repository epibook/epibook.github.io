# is_binary_tree_a_BST_BFS.cpp 100b4adabfd008775520062bd407c3323ea646af
import collections
from binary_tree_prototype import BinaryTreeNode


# @include
QNode = collections.namedtuple("QNode", ("node", "lower", "upper"))

def is_binary_tree_BST(tree):
    BFS_queue = collections.deque()
    BFS_queue.append(QNode(tree, float("-inf"), float("inf")))

    while BFS_queue:
        front = BFS_queue.popleft()
        if front.node:
            if not front.lower < front.node.data < front.upper:
                return False

            BFS_queue.append(QNode(front.node.left, front.lower, front.node.data))
            BFS_queue.append(QNode(front.node.right, front.node.data, front.upper))

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
    assert is_binary_tree_BST(tree) == True
    print(is_binary_tree_BST(tree))
    #      10
    #    2   5
    #  1    4 6
    tree.data = 10
    # should output False.
    assert not is_binary_tree_BST(tree)
    print(is_binary_tree_BST(tree))
    # should output True.
    assert is_binary_tree_BST(None) == True
    print(is_binary_tree_BST(None))


if __name__ == '__main__':
    main()
