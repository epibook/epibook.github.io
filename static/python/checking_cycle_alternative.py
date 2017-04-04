from linked_list_prototype import ListNode


# @include
def has_cycle(head):
    fast = slow = head
    while fast and fast.next and fast.next.next:
        slow, fast = slow.next, fast.next.next
        if slow is fast:  # There is a cycle.
            # Tries to find the start of the cycle.
            slow = head
            # Both pointers advance at the same time.
            while slow is not fast:
                slow, fast = slow.next, fast.next
            return slow  # slow is the start of cycle.
    return None  # No cycle.
# @exclude


def main():
    L3 = ListNode(3, None)
    L2 = ListNode(2, L3)
    L1 = ListNode(1, L2)

    # Should output 'L1 does not have cycle.'
    assert has_cycle(L1) is None
    print('L1', 'has' if has_cycle(L1) else 'does not have', 'cycle.')

    # Make it a cycle
    L3.next = L2
    # Should output 'L1 has cycle, at node has value 2'
    assert has_cycle(L1) is not None
    assert has_cycle(L1).data == 2
    temp = has_cycle(L1)
    if temp:
        print('L1 has cycle, at node has value', temp.data)
    else:
        print('L1 does not have cycle')


if __name__ == '__main__':
    main()
