from binary_tree_prototype import BinaryTreeNode


# Build a BST from the (s + 1)-th to the e-th node in L.
def build_bst_from_sorted_doubly_list_helper(L, s, e):
    curr = None
    if s < e:
        m = s + ((e - s) // 2)
        temp, L = build_bst_from_sorted_doubly_list_helper(L, s, m)
        curr = L
        curr.left = temp
        L = L.right
        curr.right, L = build_bst_from_sorted_doubly_list_helper(L, m + 1, e)
    return curr, L


def build_bst_from_sorted_doubly_list(L, n):
    return build_bst_from_sorted_doubly_list_helper(L, 0, n)[0]


# Transform a BST into a circular sorted doubly linked list in-place,
# return the head of the list.
def bst_to_doubly_list_helper(n):
    # Empty subtree.
    if not n:
        return None

    # Recursively build the list from left and right subtrees.
    l_head = bst_to_doubly_list_helper(n.left)
    r_head = bst_to_doubly_list_helper(n.right)

    # Append n to the list from left subtree.
    if l_head:
        l_tail = l_head.left
        l_tail.right = n
        n.left = l_tail
        l_tail = n
    else:
        l_head = l_tail = n

    # Append the list from right subtree to n.
    if r_head:
        r_tail = r_head.left
        l_tail.right = r_head
        r_head.left = l_tail
    else:
        r_tail = l_tail
    r_tail.right = l_head
    l_head.left = r_tail

    return l_head


def bst_to_doubly_list(n):
    res = bst_to_doubly_list_helper(n)
    res.left.right = None  # breaks the link from tail to head.
    res.left = None  # breaks the link from head to tail.
    return res


# Count the list length till end.
def count_length(L):
    length = 0
    while L:
        length += 1
        L = L.right
    return length


# @include
def merge_two_bsts(A, B):
    A, B = bst_to_doubly_list(A), bst_to_doubly_list(B)
    A_length, B_length = count_length(A), count_length(B)
    return build_bst_from_sorted_doubly_list(
        merge_two_sorted_lists(A, B), A_length + B_length)
# @exclude


# Merges two sorted doubly linked lists, returns the head of merged list.
def merge_two_sorted_lists(A, B):
    sorted_head = BinaryTreeNode()
    tail = sorted_head

    AB = [A, B]
    while all(AB):
        A_or_B = 0 if AB[0].data < AB[1].data else 1
        tail.right = AB[A_or_B]
        tail = tail.right  # Resets tail to the last node.
        AB[A_or_B] = tail.right

    if AB[0]:  # Appends the remaining of A.
        tail.right = AB[0]
    elif AB[1]:  # Appends the remaining of B.
        tail.right = AB[1]
    return sorted_head.right


def print_bst_inorder(n, pre):
    if n:
        print_bst_inorder(n.left, pre)
        assert pre <= n.data
        print(n.data, end=' ')
        print_bst_inorder(n.right, n.data)


def main():
    #      3
    #    2   5
    #  1    4 6
    L = BinaryTreeNode(3)
    L.left = BinaryTreeNode(2)
    L.left.left = BinaryTreeNode(1)
    L.right = BinaryTreeNode(5)
    L.right.left = BinaryTreeNode(4)
    L.right.right = BinaryTreeNode(6)
    #      7
    #    2   8
    #  0
    R = BinaryTreeNode(7)
    R.left = BinaryTreeNode(2)
    R.left.left = BinaryTreeNode(0)
    R.right = BinaryTreeNode(8)
    root = merge_two_bsts(L, R)
    # should output 0 1 2 2 3 4 5 6 7 8
    print_bst_inorder(root, float('-inf'))


if __name__ == '__main__':
    main()
