from linked_list_prototype import ListNode


# @include
# Insert new_node after node.
def insert_after(node, new_node):
    new_node.next = node.next
    node.next = new_node
# @exclude


def check_answer(L, vals):
    for val in vals:
        assert L.data == val
        L = L.next
    assert not L


def main():
    L = ListNode(2, ListNode(4, ListNode(3, None)))
    insert_after(L, ListNode(1, None))
    check_answer(L, [2, 1, 4, 3])
    insert_after(L.next.next, ListNode(10, None))
    check_answer(L, [2, 1, 4, 10, 3])
    insert_after(L.next.next.next.next, ListNode(-1, None))
    check_answer(L, [2, 1, 4, 10, 3, -1])


if __name__ == '__main__':
    main()
