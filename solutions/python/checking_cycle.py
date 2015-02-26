# Checking_cycle.h b4b3a70d8ab942579f85b4416f980d05831af969
# Checking_cycle.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
from linked_list_prototype import ListNode


# @include
def has_cycle(head):
    fast = slow = head

    while fast and fast.next and fast.next.next:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            # There is a cycle, now let's calculate the cycle length.
            cycle_len = 0
            while True:
                cycle_len += 1
                fast = fast.next
                if slow is fast:
                    break

            # Finds the start of the cycle.
            cycle_len_advanced_iter = head
            while cycle_len:
                cycle_len -= 1
                cycle_len_advanced_iter = cycle_len_advanced_iter.next

            it = head
            # Both iterators advance in tandem.
            while it is not cycle_len_advanced_iter:
                it = it.next
                cycle_len_advanced_iter = cycle_len_advanced_iter.next

            return it  # iter is the start of cycle.

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
