import sys
from linked_list_prototype import ListNode
from reverse_linked_list_iterative import reverse_linked_list


# @include
def is_linked_list_a_palindrome(L):
    # Finds the second half of L.
    slow = fast = L
    while fast and fast.next:
        fast, slow = fast.next.next, slow.next

    # Compares the first half and the reversed second half lists.
    first_half_iter, second_half_iter = L, reverse_linked_list(slow)
    while second_half_iter and first_half_iter:
        if second_half_iter.data != first_half_iter.data:
            return False
        second_half_iter, first_half_iter = (second_half_iter.next,
                                             first_half_iter.next)
    return True
# @exclude


def main():
    head = None
    if len(sys.argv) > 2:
        # Input the node's value in reverse order.
        for i in sys.argv[1:]:
            curr = ListNode(int(i), head)
            head = curr
        print('Yes' if is_linked_list_a_palindrome(head) else 'No')
    assert is_linked_list_a_palindrome(None)
    assert is_linked_list_a_palindrome(ListNode(1))
    assert is_linked_list_a_palindrome(ListNode(1, ListNode(1)))
    assert is_linked_list_a_palindrome(ListNode(1, ListNode(2))) == False
    assert is_linked_list_a_palindrome(
        ListNode(1, ListNode(3, ListNode(2, ListNode(1))))) == False

    head = None
    # A link list is a palindrome.
    for _ in range(6):
        curr = ListNode(1, head)
        head = curr
    assert is_linked_list_a_palindrome(head)

    # Still a palindrome linked list.
    head = None
    for _ in range(5):
        curr = ListNode(1, head)
        head = curr
    head.next.next.data = 3
    assert is_linked_list_a_palindrome(head)

    # Not a palindrome linked list.
    head = None
    for i in reversed(range(1, 5)):
        curr = ListNode(i, head)
        head = curr
    assert is_linked_list_a_palindrome(head) == False


if __name__ == '__main__':
    main()
