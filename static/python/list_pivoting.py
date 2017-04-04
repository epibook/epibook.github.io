from linked_list_prototype import ListNode


# @include
def list_pivoting(L, x):
    less_head = less_iter = ListNode()
    equal_head = equal_iter = ListNode()
    greater_head = greater_iter = ListNode()
    # Populates the three lists.
    while L:
        if L.data < x:
            less_iter.next = L
            less_iter = less_iter.next
        elif L.data == x:
            equal_iter.next = L
            equal_iter = equal_iter.next
        else:  # L.data > x.
            greater_iter.next = L
            greater_iter = greater_iter.next
        L = L.next
    # Combines the three lists.
    greater_iter.next = None
    equal_iter.next = greater_head.next
    less_iter.next = equal_head.next
    return less_head.next
# @exclude


def simple_test():
    L = ListNode(0)
    result = list_pivoting(L, 0)
    assert result is L
    result = list_pivoting(L, 1)
    assert result is L
    result = list_pivoting(L, -1)
    assert result is L

    L = ListNode(2, ListNode(0))
    result = list_pivoting(L, -1)
    assert result is L

    L = ListNode(2, ListNode(0))
    result = list_pivoting(L, 1)
    assert result.data == 0 and result.next.data == 2

    L = ListNode(2, ListNode(0, ListNode(-2)))
    result = list_pivoting(L, 1)
    assert result.data == 0 and result.next.data == -2 and result.next.next.data == 2


def main():
    simple_test()
    L = ListNode(1, ListNode(4, ListNode(3, ListNode(2, ListNode(5, None)))))
    x = 4
    result = list_pivoting(L, x)
    print()
    before_x = True
    while result:
        if result.data >= x:
            before_x = False
        if before_x:
            assert result.data < x
        else:
            assert result.data >= x
        print(result.data)
        result = result.next


if __name__ == '__main__':
    main()
