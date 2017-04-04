import sys
import random
from postings_list_prototype import ListNode


# @include
def copy_postings_list(L):
    if not L:
        return None

    # Stage 1: Makes a copy of the original list without assigning the jump
    #          field, and creates the mapping for each node in the original
    #          list to the copied list.
    it = L
    while it:
        new_node = ListNode(it.order, it.next, None)
        it.next = new_node
        it = new_node.next

    # Stage 2: Assigns the jump field in the copied list.
    it = L
    while it:
        if it.jump:
            it.next.jump = it.jump.next
        it = it.next.next

    # Stage 3: Reverts the original list, and assigns the next field of
    #          the copied list.
    it = L
    new_list_head = it.next
    while it.next:
        it.next, it = it.next.next, it.next
    return new_list_head
# @exclude


def check_postings_list_equal(a, b):
    while a and b:
        print(a.order, end=' ')
        assert a.order == b.order
        assert ((a.jump is None and b.jump is None) or
                (a.jump and b.jump and a.jump.order == b.jump.order))
        if a.jump:
            print(a.jump.order, end='')
        print()
        a = a.next
        b = b.next
    assert a is None and b is None


def main():
    for _ in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 1000)
        L = curr = None
        for i in range(n):
            temp = ListNode(i, None)
            if L:
                curr.next = temp
                curr = temp
            else:
                curr = L = temp
            # Randomly assigned a jump node.
            jump_num = random.randint(0, i + 1)
            jump = L
            while jump_num:
                jump_num -= 1
                jump = jump.next
            temp.jump = jump

        copied = copy_postings_list(L)
        check_postings_list_equal(L, copied)


if __name__ == '__main__':
    main()
