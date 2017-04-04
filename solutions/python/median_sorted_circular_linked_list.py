import sys
import random
from linked_list_prototype import ListNode


# @include
def find_median_sorted_circular_linked_list(arbitrary_node):
    # Checks if all nodes are identical and identifies the first smallest node.
    it = arbitrary_node
    first_smallest_node = arbitrary_node
    n = 0
    while True:
        n += 1
        it = it.next
        if first_smallest_node.data <= it.data:
            # Now first_smallest_node points to the largest element.
            first_smallest_node = it
        if it is arbitrary_node:
            break
    # first_smallest_node's next is the first smallest node.
    first_smallest_node = first_smallest_node.next

    # Advances to the middle of the list.
    for i in range((n - 1) // 2):
        first_smallest_node = first_smallest_node.next

    if n % 2:
        return first_smallest_node.data
    else:
        return 0.5 * (first_smallest_node.data + first_smallest_node.next.data)
# @exclude


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
        head = None
        for i in reversed(range(n + 1)):
            curr = ListNode(i, head)
            head = curr
        curr = head
        if curr:
            while curr.next:
                curr = curr.next
            curr.next = head  # make the list as a circular list.
        res = find_median_sorted_circular_linked_list(head.next)
        print(res)
        assert res == 0.5 * n

    # Test identical list.
    head = None
    for i in range(10):
        curr = ListNode(5, head)
        head = curr
    curr = head
    if curr:
        while curr.next:
            curr = curr.next
        curr.next = head  # make the list as a circular list.
    assert 5 == find_median_sorted_circular_linked_list(head)


if __name__ == '__main__':
    main()
