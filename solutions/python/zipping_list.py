import sys
import random
from linked_list_prototype import ListNode
from reverse_linked_list_iterative import reverse_linked_list


# @include
def zipping_linked_list(L):
    if not L or not L.next:
        return L

    # Finds the second half of L.
    slow = fast = L
    while fast and fast.next:
        slow, fast = slow.next, fast.next.next

    first_half_head = L
    second_half_head = slow.next
    slow.next = None  # Splits the list into two lists.

    second_half_head = reverse_linked_list(second_half_head)

    # Interleave the first half and the reversed of the second half.
    first_half_iter, second_half_iter = first_half_head, second_half_head
    while second_half_iter:
        second_half_iter.next, first_half_iter.next, second_half_iter = (
            first_half_iter.next, second_half_iter, second_half_iter.next)
        first_half_iter = first_half_iter.next.next
    return first_half_head
# @exclude


def main():
    head = None
    if len(sys.argv) > 2:
        for i in sys.argv[1:]:
            curr = ListNode(int(i), head)
            head = curr
    else:
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
        for i in reversed(range(n + 1)):
            curr = ListNode(i, head)
            head = curr
    curr = zipping_linked_list(head)
    idx = 0
    while curr:
        if len(sys.argv) <= 2:
            if idx & 1:
                assert pre + curr.data == n
        idx += 1
        print(curr.data)
        pre = curr.data
        curr = curr.next


if __name__ == '__main__':
    main()
