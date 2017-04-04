from linked_list_prototype import ListNode
from checking_cycle import has_cycle
from overlapping_lists_no_cycle import overlapping_no_cycle_lists


# @include
def overlapping_lists(L1, L2):
    # Store the start of cycle if any.
    root1, root2 = has_cycle(L1), has_cycle(L2)

    if not root1 and not root2:
        # Both lists don't have cycles.
        return overlapping_no_cycle_lists(L1, L2)
    elif (root1 and not root2) or (not root1 and root2):
        # One list has cycle, one list has no cycle.
        return None
    # Both lists have cycles.
    temp = root2
    while True:
        temp = temp.next
        if temp is root1 or temp is root2:
            break

    # L1 and L2 do not end in the same cycle.
    if temp is not root1:
        return None  # Cycles are disjoint.

    # Calculates the distance between a and b.
    def distance(a, b):
        dis = 0
        while a is not b:
            a = a.next
            dis += 1
        return dis
    # L1 and L2 end in the same cycle, locate the overlapping node if they
    # first overlap before cycle starts.
    stem1_length, stem2_length = distance(L1, root1), distance(L2, root2)
    if stem1_length > stem2_length:
        L2, L1 = L1, L2
        root1, root2 = root2, root1
    for _ in range(abs(stem1_length - stem2_length)):
        L2 = L2.next
    while L1 is not L2 and L1 is not root1 and L2 is not root2:
        L1, L2 = L1.next, L2.next

    # If L1 == L2 before reaching root1, it means the overlap first occurs
    # before the cycle starts; otherwise, the first overlapping node is not
    # unique, we can return any node on the cycle.
    return L1 if L1 is L2 else root1
# @exclude


def small_test():
    # L1: 1->2->3->4->5->6-
    #              ^  ^    |
    #              |  |____|
    # L2: 7->8-----
    L1 = ListNode(1,
                  ListNode(2,
                           ListNode(3,
                                    ListNode(4, ListNode(5, ListNode(6,
                                                                     None))))))
    L1.next.next.next.next.next.next = L1.next.next.next.next

    L2 = ListNode(7, ListNode(8, None))
    L2.next.next = L1.next.next.next
    assert overlapping_lists(L1, L2).data == 4

    # L1: 1->2->3->4->5->6-
    #           ^     ^    |
    #           |     |____|
    # L2: 7->8---
    L2.next.next = L1.next.next
    assert overlapping_lists(L1, L2).data == 3


def main():
    small_test()
    # L1: 1->2->3->null
    L1 = ListNode(1, ListNode(2, ListNode(3, None)))
    L2 = L1.next.next
    assert overlapping_lists(L1, L2).data == 3
    # L2: 4->5->null
    L2 = ListNode(4, ListNode(5, None))
    assert not overlapping_lists(L1, L2)
    L1.next.next.next = L1
    assert not overlapping_lists(L1, L2)
    L2.next.next = L2
    assert not overlapping_lists(L1, L2)
    L2.next.next = L1
    assert overlapping_lists(L1, L2)


if __name__ == '__main__':
    main()
