from binary_tree_prototype import BinaryTreeNode
from binary_tree_utils import generate_inorder


# @include
class Inversion:

    def __init__(self, prev=None, next=None):
        self.prev = prev
        self.next = next


def reconstruct_bst(almost_bst):
    inversion_0 = Inversion()
    inversion_1 = Inversion()
    prev = None
    reconstruct_bst_helper(almost_bst, inversion_0, inversion_1, prev)
    if inversion_1.next:  # Swaps the out of order nodes.
        inversion_0.prev.data, inversion_1.next.data = inversion_1.next.data, inversion_0.prev.data
    else:
        inversion_0.prev.data, inversion_0.next.data = inversion_0.next.data, inversion_0.prev.data


def reconstruct_bst_helper(almost_bst, inversion_0, inversion_1, prev):
    if almost_bst is None:
        return prev

    prev = reconstruct_bst_helper(almost_bst.left, inversion_0, inversion_1,
                                  prev)
    if prev and prev.data > almost_bst.data:
        # Inversion detected.
        if inversion_0.prev is None and inversion_0.next is None:
            inversion_0.prev = prev
            inversion_0.next = almost_bst
        else:
            inversion_1.prev = prev
            inversion_1.next = almost_bst
    prev = almost_bst  # Records the previous node as the current node.
    prev = reconstruct_bst_helper(almost_bst.right, inversion_0, inversion_1,
                                  prev)
    return prev
# @exclude


def main():
    #      3
    #    2   4
    #  1    5 6
    almost_bst = BinaryTreeNode(3)
    almost_bst.left = BinaryTreeNode(2)
    almost_bst.left.left = BinaryTreeNode(1)
    almost_bst.right = BinaryTreeNode(4)
    almost_bst.right.left = BinaryTreeNode(5)
    almost_bst.right.right = BinaryTreeNode(6)
    reconstruct_bst(almost_bst)
    result = generate_inorder(almost_bst)
    print(*result)
    assert all(result[i] <= result[i + 1] for i in range(len(result) - 1))


if __name__ == '__main__':
    main()
