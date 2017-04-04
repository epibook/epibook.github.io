from linked_list_prototype import ListNode


# @include
# Assumes L has at least k nodes, deletes the k-th last node in L.
def remove_kth_last(L, k):
    dummy_head = ListNode(0, L)
    first = dummy_head.next
    for _ in range(k):
        first = first.next

    second = dummy_head
    while first:
        first, second = first.next, second.next
    # second points to the (k + 1)-th last node, deletes its successor.
    second.next = second.next.next
    return dummy_head.next
# @exclude


def main():
    L = ListNode(1, ListNode(2, ListNode(3, None)))
    L = remove_kth_last(L, 2)
    assert L.data == 1 and L.next.data == 3
    L = remove_kth_last(L, 2)
    assert L.data == 3 and L.next is None
    L = remove_kth_last(L, 1)
    assert not L


if __name__ == '__main__':
    main()
