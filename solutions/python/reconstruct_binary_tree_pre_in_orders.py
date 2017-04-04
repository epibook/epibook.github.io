import sys
import random
from binary_tree_prototype import BinaryTreeNode
from binary_tree_utils import generate_rand_binary_tree, generate_preorder, generate_inorder, is_two_binary_trees_equal


# @include
def binary_tree_from_preorder_inorder(preorder, inorder):
    node_to_inorder_idx = {data: i for i, data in enumerate(inorder)}

    # Builds the subtree with preorder[preorder_start:preorder_end] and
    # inorder[inorder_start:inorder_end].
    def binary_tree_from_preorder_inorder_helper(preorder_start, preorder_end,
                                                 inorder_start, inorder_end):
        if preorder_end <= preorder_start or inorder_end <= inorder_start:
            return None

        root_inorder_idx = node_to_inorder_idx[preorder[preorder_start]]
        left_subtree_size = root_inorder_idx - inorder_start
        return BinaryTreeNode(
            preorder[preorder_start],
            # Recursively builds the left subtree.
            binary_tree_from_preorder_inorder_helper(
                preorder_start + 1, preorder_start + 1 + left_subtree_size,
                inorder_start, root_inorder_idx),
            # Recursively builds the right subtree.
            binary_tree_from_preorder_inorder_helper(
                preorder_start + 1 + left_subtree_size, preorder_end,
                root_inorder_idx + 1, inorder_end))
    return binary_tree_from_preorder_inorder_helper(
        0, len(preorder), 0, len(inorder))
# @exclude


def simple_test():
    res = binary_tree_from_preorder_inorder([1], [1])
    assert res.data == 1

    res = binary_tree_from_preorder_inorder([2, 1], [1, 2])
    assert res.data == 2 and res.left.data == 1 and not res.right

    N = 100
    inorder, preorder = [], []
    for i in range(N):
        inorder.append(i)
        preorder.append((N - 1) - i)

    res = binary_tree_from_preorder_inorder(preorder, inorder)
    assert res.data == N - 1 and res.left.data == N - 2 and not res.right


def main():
    simple_test()
    for times in range(1000):
        print(times)
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        root = generate_rand_binary_tree(n, True)
        pre_order = generate_preorder(root)
        in_order = generate_inorder(root)
        res = binary_tree_from_preorder_inorder(pre_order, in_order)
        assert is_two_binary_trees_equal(root, res)


if __name__ == '__main__':
    main()
