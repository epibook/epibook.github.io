from linked_list_prototype import ListNode


# @include
# Assumes node_to_delete is not tail.
def deletion_from_list(node_to_delete):
    node_to_delete.data = node_to_delete.next.data
    node_to_delete.next = node_to_delete.next.next
# @exclude


def main():
    L = ListNode(1, ListNode(2, ListNode(3, None)))
    deletion_from_list(L)
    assert L.data == 2 and L.next.data == 3


if __name__ == '__main__':
    main()
