from binary_tree_prototype import BinaryTreeNode
from binary_tree_utils import generate_preorder


# @include
def preorder_traversal(tree):
    path, result = [tree], []
    while path:
        curr = path.pop()
        if curr:
            result.append(curr.data)
            path += [curr.right, curr.left]
    return result
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
    res = preorder_traversal(tree)
    golden_res = generate_preorder(tree)
    assert res == golden_res


if __name__ == '__main__':
    main()
