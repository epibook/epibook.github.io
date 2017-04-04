from binary_tree_prototype import BinaryTreeNode

result = []


# @include
def inorder_traversal(tree):
    it = tree
    while it:
        pre = it.left
        if pre:
            # Finds the predecessor of it.
            while pre.right and pre.right is not it:
                pre = it.left = pre.right

            # Processes the successor link.
            if pre.right:  # pre.right is it.
                # Reverts the successor link if predecessor's successor is it.
                pre.right = None
                print(it.data)
                # @exclude
                result.append(it.data)
                # @include
                it = it.right
            else:  # If predecessor's successor is not it.
                pre.right = it
                it = it.left

        else:
            print(it.data)
            # @exclude
            result.append(it.data)
            # @include
            it = it.right
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
    # should output 1 2 3 4 5 6
    inorder_traversal(tree)
    golden_res = [1, 2, 3, 4, 5, 6]
    assert result == golden_res


if __name__ == '__main__':
    main()
