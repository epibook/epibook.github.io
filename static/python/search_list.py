from linked_list_prototype import ListNode


# @include
def search_list(L, key):
    while L and L.data != key:
        L = L.next
    # If key was not present in the list, L will have become null.
    return L
# @exclude


def main():
    L = ListNode(2, ListNode(4, ListNode(3, None)))
    assert L is search_list(L, 2)
    assert L.next is search_list(L, 4)
    assert L.next.next is search_list(L, 3)
    assert not search_list(L, 100)


if __name__ == '__main__':
    main()
