import sys
import random
from linked_list_prototype import ListNode


def even_odd_merge_original(L):
    if not L:
        return L

    even_list_head = L
    even_list_iter, predecessor_even_list_iter = even_list_head, None
    odd_list_iter = odd_list_head = L.next

    while even_list_iter and odd_list_iter:
        even_list_iter.next = odd_list_iter.next
        predecessor_even_list_iter = even_list_iter
        even_list_iter = even_list_iter.next
        if even_list_iter:
            odd_list_iter.next = even_list_iter.next
            odd_list_iter = odd_list_iter.next

    # Appends odd list to the tail of even list.
    if even_list_iter:
        even_list_iter.next = odd_list_head
    else:
        predecessor_even_list_iter.next = odd_list_head
    return even_list_head


# @include
def even_odd_merge(L):
    if not L:
        return L

    even_dummy_head, odd_dummy_head = ListNode(0), ListNode(0)
    tails, turn = [even_dummy_head, odd_dummy_head], 0
    while L:
        tails[turn].next = L
        L = L.next
        tails[turn] = tails[turn].next
        turn ^= 1  # Alternate between even and odd.
    tails[1].next = None
    tails[0].next = odd_dummy_head.next
    return even_dummy_head.next
# @exclude


def simple_test():
    L = ListNode(0)
    result = even_odd_merge(L)
    assert result.data == 0
    assert not result.next

    L.next = ListNode(1)
    result = even_odd_merge(L)
    assert result.data == 0
    assert result.next.data == 1
    assert not result.next.next

    L.next.next = ListNode(2)
    result = even_odd_merge(L)
    assert result.data == 0
    assert result.next.data == 2
    assert result.next.next.data == 1
    assert not result.next.next.next


def create_list(n):
    head = None
    for i in reversed(range(n)):
        curr = ListNode(i, None)
        curr.next = head
        head = curr
    return head


def check_answer(L, n):
    x = count = 0
    it = L
    while it:
        count += 1
        assert x == it.data
        x += 2
        if x >= n:
            x = 1
        print(it.data)
        it = it.next
    assert count == n


def main():
    simple_test()
    head = None
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
    else:
        n = random.randint(1, 1000)
    head = create_list(n)
    check_answer(even_odd_merge(head), n)
    another_head = create_list(n)
    check_answer(even_odd_merge_original(another_head), n)


if __name__ == '__main__':
    main()
