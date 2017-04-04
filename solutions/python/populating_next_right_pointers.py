class BinaryTreeNode:

    def __init__(self, data=None, left=None, right=None, nxt=None):
        self.data = data
        self.left = left
        self.right = right
        self.next = nxt  # Populates this field.


# @include
def construct_right_sibling(tree):
    def populate_children_next_field(start_node):
        while start_node and start_node.left:
            # Populate left child's next field.
            start_node.left.next = start_node.right
            # Populate right child's next field if iter is not the last node of
            # level.
            start_node.right.next = start_node.next and start_node.next.left
            start_node = start_node.next

    while tree and tree.left:
        populate_children_next_field(tree)
        tree = tree.left
# @exclude


def simple_test():
    #      3
    #    2   5
    root = BinaryTreeNode(3, BinaryTreeNode(2), BinaryTreeNode(5))
    construct_right_sibling(root)
    assert not root.next
    assert root.left.next is root.right
    assert not root.right.next


def main():
    simple_test()
    #      3
    #    2   5
    #  1  7 4 6
    root = BinaryTreeNode(3)
    root.left = BinaryTreeNode(2)
    root.left.right = BinaryTreeNode(7)
    root.left.left = BinaryTreeNode(1)
    root.right = BinaryTreeNode(5)
    root.right.left = BinaryTreeNode(4)
    root.right.right = BinaryTreeNode(6)
    construct_right_sibling(root)
    assert root.next is None
    assert root.left.next is root.right
    assert root.left.left.next is root.left.right
    assert root.left.right.next is root.right.left
    assert root.right.left.next is root.right.right


if __name__ == '__main__':
    main()
