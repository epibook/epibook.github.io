# Binary_tree_level_order.cc 4de4eafe35c9113838acae26df7caa75aab3c8eb
import collections
from binary_tree_prototype import BinaryTreeNode


# @include
def print_binary_tree_depth_order(tree):
    processing_nodes = collections.deque()
    processing_nodes.append(tree)
    num_nodes_current_level = len(processing_nodes)
    result = []
    one_level = []
    while processing_nodes:
        curr = processing_nodes.popleft()
        num_nodes_current_level -= 1
        if curr:
            one_level.append(curr.data)

            # Defer the null checks to the null test above.
            processing_nodes.append(curr.left)
            processing_nodes.append(curr.right)
        # Done with the nodes at the current depth.
        if num_nodes_current_level == 0:
            num_nodes_current_level = len(processing_nodes)
            result.append(one_level.copy())
            one_level.clear()
    return result
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    #10
    # 13
    tree = BinaryTreeNode(3)
    tree.left = BinaryTreeNode(2)
    tree.left.left = BinaryTreeNode(1)
    tree.left.left.left = BinaryTreeNode(10)
    tree.left.left.left.right = BinaryTreeNode(13)
    tree.right = BinaryTreeNode(5)
    tree.right.left = BinaryTreeNode(4)
    tree.right.right = BinaryTreeNode(6)
    # should output 3
    #               2 5
    #               1 4 6
    #               10
    #               13
    result = print_binary_tree_depth_order(tree)
    assert result == [[3], [2, 5], [1, 4, 6], [10], [13], []]


if __name__ == '__main__':
    main()
