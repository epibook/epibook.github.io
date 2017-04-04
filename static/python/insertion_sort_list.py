from linked_list_prototype import ListNode


# @include
def insertion_sort(L):
    dummy_head = ListNode(0, L)
    # The sublist consisting of nodes up to and including iter is sorted in
    # increasing order. We need to ensure that after we move to L.next this
    # property continues to hold. We do this by swapping L.next with its
    # predecessors in the list till it's in the right place.
    while L and L.next:
        if L.data > L.next.data:
            target, pre = L.next, dummy_head
            while pre.next.data < target.data:
                pre = pre.next
            temp, pre.next, L.next = pre.next, target, target.next
            target.next = temp
        else:
            L = L.next
    return dummy_head.next
# @exclude


def main():
    L = ListNode(1, ListNode(4, ListNode(3, ListNode(2, ListNode(5, None)))))
    result = insertion_sort(L)
    pre = None
    while result:
        assert not pre or pre.data <= result.data
        pre = result
        print(result.data)
        result = result.next


if __name__ == '__main__':
    main()
