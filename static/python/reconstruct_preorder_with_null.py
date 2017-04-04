import sys
import random
from binary_tree_prototype import BinaryTreeNode
from binary_tree_utils import generate_rand_binary_tree, is_two_binary_trees_equal


# @include
def reconstruct_preorder(preorder):
    def reconstruct_preorder_helper(preorder_iter):
        subtree_key = next(preorder_iter)
        if subtree_key is None:
            return None

        # Note that reconstruct_preorder_helper updates preorder_iter. So the
        # order of following two calls are critical.
        left_subtree = reconstruct_preorder_helper(preorder_iter)
        right_subtree = reconstruct_preorder_helper(preorder_iter)
        return BinaryTreeNode(subtree_key, left_subtree, right_subtree)

    return reconstruct_preorder_helper(iter(preorder))
# @exclude


def gen_preorder_with_null(n, p):
    if not n:
        p.append(None)
        return

    p.append(n.data)
    gen_preorder_with_null(n.left, p)
    gen_preorder_with_null(n.right, p)


def simple_test():
    preorder = [1, None, None]
    result = reconstruct_preorder(preorder)
    assert result.data == 1 and not result.left and not result.right

    preorder = [1, None, 2, None, None]
    result = reconstruct_preorder(preorder)
    assert result.data == 1 and not result.left and result.right.data == 2

    preorder = [1, None, 2, 3, None, None, None]
    result = reconstruct_preorder(preorder)
    assert result.data == 1 and not result.left and result.right.data == 2 and result.right.left.data == 3 and not result.right.right


def main():
    simple_test()
    for times in range(1000):
        print(times)
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 10000)
        root = generate_rand_binary_tree(n)
        p = []
        gen_preorder_with_null(root, p)
        x = reconstruct_preorder(p)
        assert is_two_binary_trees_equal(root, x)


if __name__ == '__main__':
    main()
