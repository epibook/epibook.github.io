import sys
import random
from binary_tree_prototype import BinaryTreeNode


# @include
def generate_all_binary_trees(num_nodes):
    if num_nodes == 0:  # Empty tree, add as a None.
        return [None]

    result = []
    for num_left_tree_nodes in range(num_nodes):
        num_right_tree_nodes = num_nodes - 1 - num_left_tree_nodes
        left_subtrees = generate_all_binary_trees(num_left_tree_nodes)
        right_subtrees = generate_all_binary_trees(num_right_tree_nodes)
        # Generates all combinations of left_subtrees and right_subtrees.
        result += [
            BinaryTreeNode(0, left, right)
            for left in left_subtrees for right in right_subtrees
        ]
    return result
# @exclude


def generate_all_binary_trees_pythonic(num_nodes):
    return [
        BinaryTreeNode(0, left, right)
        for num_left_tree_nodes in range(num_nodes)
        for left in generate_all_binary_trees(num_left_tree_nodes) for right in
        generate_all_binary_trees(num_nodes - 1 - num_left_tree_nodes)
    ] or [None]


def small_test():
    assert len(generate_all_binary_trees(1)) == len(
        generate_all_binary_trees_pythonic(1)) == 1
    assert len(generate_all_binary_trees(2)) == len(
        generate_all_binary_trees_pythonic(2)) == 2
    assert len(generate_all_binary_trees(3)) == len(
        generate_all_binary_trees_pythonic(3)) == 5
    assert len(generate_all_binary_trees(4)) == len(
        generate_all_binary_trees_pythonic(4)) == 14
    assert len(generate_all_binary_trees(5)) == len(
        generate_all_binary_trees_pythonic(5)) == 42
    assert len(generate_all_binary_trees(10)) == len(
        generate_all_binary_trees_pythonic(10)) == 16796


def main():
    small_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10)
    print('n =', n)
    generate_all_binary_trees(n)


if __name__ == '__main__':
    main()
