import sys
import random
from binary_tree_prototype import BinaryTreeNode


def generate_rand_binary_tree(n, is_unique=False):
    if not n:
        return None
    l = []
    temp_n = n
    root = BinaryTreeNode(n if is_unique else random.randrange(temp_n))
    n -= 1
    l.append((root, 'left'))
    l.append((root, 'right'))
    while n:
        x = random.randrange(len(l))
        node = BinaryTreeNode(n if is_unique else random.randrange(temp_n))
        setattr(l[x][0], l[x][1], node)
        l.append((node, 'left'))
        l.append((node, 'right'))
        del l[x]
        n -= 1
    return root


def is_two_binary_trees_equal(r1, r2):
    if r1 and r2:
        return (is_two_binary_trees_equal(r1.left, r2.left) and
                is_two_binary_trees_equal(r1.right, r2.right) and
                r1.data == r2.data)
    elif not r1 and not r2:
        return True
    else:
        return False


def generate_preorder_helper(r, ret):
    if r:
        ret.append(r.data)
        generate_preorder_helper(r.left, ret)
        generate_preorder_helper(r.right, ret)


def generate_preorder(r):
    ret = []
    generate_preorder_helper(r, ret)
    return ret


def generate_inorder_helper(r, ret):
    if r:
        generate_inorder_helper(r.left, ret)
        ret.append(r.data)
        generate_inorder_helper(r.right, ret)


def generate_inorder(r):
    ret = []
    generate_inorder_helper(r, ret)
    return ret


def generate_postorder_helper(r, ret):
    if r:
        generate_postorder_helper(r.left, ret)
        generate_postorder_helper(r.right, ret)
        ret.append(r.data)


def generate_postorder(r):
    ret = []
    generate_postorder_helper(r, ret)
    return ret
