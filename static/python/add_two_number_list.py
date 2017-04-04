from linked_list_prototype import ListNode


# @include
def add_two_numbers(L1, L2):
    place_iter = dummy_head = ListNode()
    carry = 0
    while L1 or L2 or carry:
        val = carry + (L1.data if L1 else 0) + (L2.data if L2 else 0)
        L1 = L1.next if L1 else None
        L2 = L2.next if L2 else None
        place_iter.next = ListNode(val % 10)
        carry, place_iter = val // 10, place_iter.next
    return dummy_head.next
# @exclude


def simple_test():
    L = ListNode(2, ListNode(4, ListNode(3)))
    R = ListNode(0)
    S = add_two_numbers(L, R)
    assert S.data == 2 and S.next.data == 4 and S.next.next.data == 3
    L = ListNode(3, ListNode(4, ListNode(2)))
    R = ListNode(7, ListNode(5, ListNode(7)))
    S = add_two_numbers(L, R)
    assert S.data == 0 and S.next.data == 0 and S.next.next.data == 0 and S.next.next.next.data == 1
    L = ListNode(1)
    R = ListNode(1)
    S = add_two_numbers(L, R)
    assert S.data == 2 and not S.next
    L = ListNode(5)
    R = ListNode(5)
    S = add_two_numbers(L, R)
    assert S.data == 0 and S.next.data == 1 and not S.next.next

    L = ListNode(2, ListNode(4, ListNode(3, None)))
    R = ListNode(5, ListNode(6, ListNode(7, None)))
    S = add_two_numbers(L, R)
    assert S.data == 7 and S.next.data == 0 and S.next.next.data == 1 and S.next.next.next.data == 1
    L = ListNode(9, ListNode(9, None))
    R = ListNode(9, None)
    S = add_two_numbers(L, R)
    assert S.data == 8 and S.next.data == 0 and S.next.next.data == 1


def main():
    simple_test()


if __name__ == '__main__':
    main()
