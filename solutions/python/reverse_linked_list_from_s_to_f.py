from linked_list_prototype import ListNode


# @include
def reverse_sublist(L, start, finish):
    dummy_head = sublist_head = ListNode(0, L)
    for _ in range(1, start):
        sublist_head = sublist_head.next

    # Reverses sublist.
    sublist_iter = sublist_head.next
    for _ in range(finish - start):
        temp = sublist_iter.next
        sublist_iter.next, temp.next, sublist_head.next = (
            temp.next, sublist_head.next, temp)
    return dummy_head.next
# @exclude


def simple_test():
    L = None
    result = reverse_sublist(L, 0, 0)
    assert result is None

    L = ListNode(1, None)
    result = reverse_sublist(L, 0, 0)
    assert result is L and result.next is None

    L = ListNode(1, ListNode(2, ListNode(3, None)))
    result = reverse_sublist(L, 0, 1)
    assert result.data == 2 and result.next.data == 1 and result.next.next.data == 3 and result.next.next.next is None

    L = ListNode(1, ListNode(2, ListNode(3, None)))
    result = reverse_sublist(L, 0, 2)
    assert result.data == 3 and result.next.data == 2 and result.next.next.data == 1 and result.next.next.next is None


def main():
    simple_test()
    L = ListNode(1, ListNode(2, ListNode(3, None)))
    result = reverse_sublist(L, 3, 3)
    assert (result.data == 1 and result.next.data == 2 and
            result.next.next.data == 3 and not result.next.next.next)
    while result:
        print(result.data)
        result = result.next

    result = reverse_sublist(L, 2, 3)
    assert (result.data == 1 and result.next.data == 3 and
            result.next.next.data == 2 and not result.next.next.next)
    while result:
        print(result.data)
        result = result.next

    L = ListNode(3, ListNode(5, None))
    result = reverse_sublist(L, 1, 2)
    assert result.data == 5 and result.next.data == 3 and not result.next.next
    while result:
        print(result.data)
        result = result.next


if __name__ == '__main__':
    main()
