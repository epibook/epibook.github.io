import itertools
from postings_list_prototype import ListNode


# @include
def search_postings_list(L):
    s, order = [L], 0
    while s:
        curr = s.pop()
        if curr and curr.order == -1:
            curr.order = order
            order += 1
            # Stack is last-in, first-out, and we want to process the jump node
            # first, so push next, then push jump.
            s += [curr.next, curr.jump]
# @exclude


def main():
    L = curr = None
    # Build a linked list L->1->2->3->4->5->None.
    for i in range(5):
        temp = ListNode(-1, None, None)
        if curr:
            curr.next = temp
            curr = temp
        else:
            curr = L = temp

    L.jump = None  # no jump from 1
    L.next.jump = L.next.next.next  # 2's jump points to 4
    L.next.next.jump = L  # 3's jump points to 1
    L.next.next.next.jump = None  # no jump from 4
    L.next.next.next.next.jump = L.next.next.next.next  # 5's jump points to 5
    temp = L
    search_postings_list(L)
    # output the jump-first order, should be 0, 1, 4, 2, 3
    assert temp.order == 0
    temp = temp.next
    assert temp.order == 1
    temp = temp.next
    assert temp.order == 4
    temp = temp.next
    assert temp.order == 2
    temp = temp.next
    assert temp.order == 3


if __name__ == '__main__':
    main()
