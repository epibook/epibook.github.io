from binary_tree_prototype import BinaryTreeNode
from binary_tree_utils import generate_postorder


# @include
# We use stack and previous node pointer to simulate postorder traversal.
def postorder_traversal(tree):
    if not tree:  # Empty tree.
        return []

    path_stack, prev, postorder_sequence = [tree], None, []
    while path_stack:
        curr = path_stack[-1]
        if not prev or prev.left is curr or prev.right is curr:
            # We came down to curr from prev.
            if curr.left:  # Traverse left.
                path_stack.append(curr.left)
            elif curr.right:  # Traverse right.
                path_stack.append(curr.right)
            else:  # Leaf node, visit current node.
                postorder_sequence.append(curr.data)
                path_stack.pop()
        elif curr.left is prev:
            # Done with left, now traverse right.
            if curr.right:
                path_stack.append(curr.right)
            else:  # No right child, so visit curr.
                postorder_sequence.append(curr.data)
                path_stack.pop()
        else:
            # Finished traversing left and right, so visit curr.
            postorder_sequence.append(curr.data)
            path_stack.pop()
        prev = curr
    return postorder_sequence
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
    assert res == golden_res


if __name__ == '__main__':
    main()
