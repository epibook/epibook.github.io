import sys
from linked_list_prototype import ListNode
from reverse_linked_list_iterative import reverse_linked_list


# @include
def reverse_k(L, k):
    dummy_head = ListNode(0, L)
    sublist_predecessor = dummy_head
    sublist_head = dummy_head.next
    sublist_successor = dummy_head
    sublist_tail = dummy_head.next
    while sublist_head:
        # Identify the tail of sublist of k nodes to be reversed.
        num_remaining = k
        while num_remaining:
            sublist_successor = sublist_tail
            sublist_tail = sublist_tail.next
            num_remaining -= 1
            if not sublist_tail:
                break
        if num_remaining > 0:
            # Specification says not to reverse.
            return dummy_head.next

        sublist_successor.next = None
        reverse_linked_list(sublist_head)

        # Splice the reversed sublist.
        sublist_predecessor.next = sublist_successor
        # Go on to the head of next sublist.
        sublist_predecessor = sublist_head
        sublist_head.next = sublist_tail
        sublist_head = sublist_tail
        sublist_successor = None
    return dummy_head.next
# @exclude


def main():
    L = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5, None)))))

    if len(sys.argv) == 2:
        k = int(sys.argv[1])
    else:
        k = 2

    result = reverse_k(L, k)
    assert (result.data == 2 and result.next.data == 1 and
            result.next.next.data == 4 and result.next.next.next.data == 3 and
            result.next.next.next.next.data == 5 and
            result.next.next.next.next.next is None)
    while result:
        print(result.data)
        result = result.next


if __name__ == '__main__':
    main()
