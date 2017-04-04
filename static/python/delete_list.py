from linked_list_prototype import ListNode


# @include
# Delete the node past this one. Assume node is not a tail.
def delete_after(node):
    node.next = node.next.next
# @exclude


def check_answer(L, vals):
    for val in vals:
        assert L.data == val
        L = L.next
    assert not L


def main():
    L = ListNode(2, ListNode(4, ListNode(3, None)))
    delete_after(L)
    check_answer(L, [2, 3])
    delete_after(L)
    check_answer(L, [2])


if __name__ == '__main__':
    main()
