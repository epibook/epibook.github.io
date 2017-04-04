from binary_tree_prototype import BinaryTreeNode
from binary_tree_utils import generate_postorder


# @include
def postorder_traversal(tree):
    def inverted_preorder_traversal(tree):
        path_stack, result = [tree], []
        while path_stack:
            curr = path_stack.pop()
            if not curr:
                continue
            result.append(curr.data)
            path_stack.extend([curr.left, curr.right])
        return result

    return reversed(inverted_preorder_traversal(tree))
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
    res = postorder_traversal(tree)
    golden_res = generate_postorder(tree)
    assert list(res) == golden_res


if __name__ == '__main__':
    main()
