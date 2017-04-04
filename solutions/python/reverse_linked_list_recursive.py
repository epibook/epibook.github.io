# Reverse_linked_list_recursive.cpp 69dab7578339012270872ba200cfff02f59ce894
from linked_list_prototype import ListNode


# @include
def reverse_linked_list(head):
    if not head or not head.next:
        return head
    new_head = reverse_linked_list(head.next)
    head.next.next = head
    head.next = None
    return new_head


# @exclude


def print_list(head):
    if head:
        print('(', head.data, ')', sep='')
        print_list(head.next)


def main():
    L1 = ListNode(1, ListNode(2, ListNode(3, None)))

    print('before reverse')
    print_list(L1)
    newhead = reverse_linked_list(L1)
    print('\nafter reverse')
    assert newhead.data == 3 and newhead.next.data == 2 and newhead.next.next.data == 1
    print_list(newhead)
    newhead = reverse_linked_list(newhead)
    assert newhead.data == 1 and newhead.next.data == 2 and newhead.next.next.data == 3
    print('\nafter another reverse')
    print_list(newhead)


if __name__ == '__main__':
    main()
