import sys
import random
from binary_tree_prototype import BinaryTreeNode


# @include
def build_min_height_bst_from_sorted_array(A):
    def build_min_height_bst_from_sorted_subarray(start, end):
        if start >= end:
            return None
        mid = (start + end) // 2
        return BinaryTreeNode(
            A[mid],
            build_min_height_bst_from_sorted_subarray(start, mid),
            build_min_height_bst_from_sorted_subarray(mid + 1, end))

    return build_min_height_bst_from_sorted_subarray(0, len(A))
# @exclude


def traversal_check(tree, target):
    if tree:
        target = traversal_check(tree.left, target)
        assert target == tree.data
        target += 1
        target = traversal_check(tree.right, target)
    return target


def simple_test():
    A = [1, 2, 3, 4]
    result = build_min_height_bst_from_sorted_array(A)
    assert 3 == result.data
    assert 2 == result.left.data
    assert 1 == result.left.left.data
    assert 4 == result.right.data


def main():
    simple_test()
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
        A = list(range(n))
        tree = build_min_height_bst_from_sorted_array(A)
        target = 0
        traversal_check(tree, target)


if __name__ == '__main__':
    main()
