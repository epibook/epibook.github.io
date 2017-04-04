import sys
import random
from linked_list_prototype import ListNode


# @include
def merge_two_sorted_lists(L1, L2):
    # Creates a placeholder for the result.
    dummy_head = tail = ListNode()

    while L1 and L2:
        if L1.data < L2.data:
            tail.next, L1 = L1, L1.next
        else:
            tail.next, L2 = L2, L2.next
        tail = tail.next

    # Appends the remaining nodes of L1 or L2
    tail.next = L1 or L2
    return dummy_head.next
# @exclude


def simple_test():
    L1, L2 = None, None
    assert merge_two_sorted_lists(L1, L2) is None

    L1 = ListNode(123)
    result = merge_two_sorted_lists(L1, L2)
    assert result.data == 123 and result.next is None

    L2 = ListNode(123)
    L1 = None
    result = merge_two_sorted_lists(L1, L2)
    assert result.data == 123 and result.next is None

    L1 = ListNode(-123)
    L2 = ListNode(123)
    result = merge_two_sorted_lists(L1, L2)
    assert result.data == -123 and result.next.data == 123 and result.next.next is None


def main():
    simple_test()
    for _ in range(10000):
        F = None
        L = None
        if len(sys.argv) == 3:
            n = int(sys.argv[1])
            m = int(sys.argv[2])
        elif len(sys.argv) == 2:
            n = int(sys.argv[1])
            m = int(sys.argv[1])
        else:
            n = random.randint(0, 99)
            m = random.randint(0, 99)
        for i in reversed(range(1, n + 1)):
            temp = ListNode(i, None)
            temp.next = F
            F = temp
        for j in reversed(range(1, m + 1)):
            temp = ListNode(j, None)
            temp.next = L
            L = temp

        sorted_head = merge_two_sorted_lists(F, L)
        count = 0
        pre = float('-inf')
        while sorted_head:
            assert pre <= sorted_head.data
            pre = sorted_head.data
            sorted_head = sorted_head.next
            count += 1
        # Make sure the merged list have the same number of nodes as F and L.
        assert count == n + m


if __name__ == '__main__':
    main()
