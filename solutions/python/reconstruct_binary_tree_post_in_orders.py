# Reconstruct_binary_tree_post_in_orders.cpp
# 07f410e6c098209e8b1ba1cc7cdd1ec09f45e485
import sys
import random
from binary_tree_prototype import BinaryTreeNode
from binary_tree_utils import generate_rand_binary_tree, generate_postorder, generate_inorder, is_two_binary_trees_equal


# @include
def reconstruct_post_in_orders(post_order, in_order):
    in_entry_idx_map = {data: i for i, data in enumerate(in_order)}
    return reconstruct_post_in_orders_helper(post_order, 0,
                                             len(post_order), 0,
                                             len(in_order), in_entry_idx_map)


def reconstruct_post_in_orders_helper(post_order, post_s, post_e, in_s, in_e,
                                      in_entry_idx_map):
    if post_e > post_s and in_e > in_s:
        idx = in_entry_idx_map[post_order[post_e - 1]]
        left_tree_size = idx - in_s

        return BinaryTreeNode(
            post_order[post_e - 1],
            # Recursively builds the left subtree.
            reconstruct_post_in_orders_helper(post_order, post_s,
                                              post_s + left_tree_size, in_s,
                                              idx, in_entry_idx_map),
            # Recursively builds the right subtree.
            reconstruct_post_in_orders_helper(
                post_order, post_s + left_tree_size, post_e - 1, idx + 1, in_e,
                in_entry_idx_map))
    return None


# @exclude


def main():
    for times in range(1000):
        print(times)
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 10000)
        root = generate_rand_binary_tree(n, True)
        post_order = generate_postorder(root)
        in_order = generate_inorder(root)
        res = reconstruct_post_in_orders(post_order, in_order)
        assert is_two_binary_trees_equal(root, res)


if __name__ == '__main__':
    main()
