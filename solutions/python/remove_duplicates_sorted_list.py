from linked_list_prototype import ListNode


# @include
def remove_duplicates(L):
    it = L
    while it:
        # Uses next_distinct to find the next distinct value.
        next_distinct = it.next
        while next_distinct and next_distinct.data == it.data:
            next_distinct = next_distinct.next
        it.next = next_distinct
        it = next_distinct
    return L
# @exclude


def simple_test():
    L = None
    assert not remove_duplicates(L)
    L = ListNode(123)
    result = remove_duplicates(L)
    assert result is L
    L.next = ListNode(123)
    result = remove_duplicates(L)
    assert not result.next

    # Creating an invalid input, 123 -> 124 -> 123, algo will not detect dups!
    L.next = ListNode(124, ListNode(123))
    result = remove_duplicates(L)
    assert L.data == 123 and L.next.data == 124 and L.next.next.data == 123


def main():
    simple_test()
    L = ListNode(2, ListNode(2, ListNode(2, ListNode(2, ListNode(2, None)))))
    pre = None
    result = remove_duplicates(L)
    count = 0
    while result:
        count += 1
        if pre:
            assert pre.data != result.data
        print(result.data)
        pre = result
        result = result.next
    assert count == 1


if __name__ == '__main__':
    main()
