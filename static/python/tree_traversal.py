from binary_tree_prototype import BinaryTreeNode


# @include
def tree_traversal(root):
    if root:
        # Preorder: Processes the root before the traversals of left and right
        # children.
        print('Preorder: %d' % root.data)
        tree_traversal(root.left)
        # Inorder: Processes the root after the traversal of left child and
        # before the traversal of right child.
        print('Inorder: %d' % root.data)
        tree_traversal(root.right)
        # Postorder: Processes the root after the traversals of left and right
        # children.
        print('Postorder: %d' % root.data)
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
    tree_traversal(tree)


if __name__ == '__main__':
    main()
