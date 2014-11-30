// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

class OverlappingListsTemplate {
  // @include
  public static ListNode<Integer> overlappingLists(ListNode<Integer> L1, ListNode<Integer> L2) {
    // Store the start of cycle if any.
    ListNode<Integer> s1 = CheckingCycle.hasCycle(L1), s2 = CheckingCycle.hasCycle(L2);

    if (s1 == null && s2 == null) {
      return OverlappingListsNoCycle.overlappingNoCycleLists(L1, L2);
    } else if (s1 != null && s2 != null) { // both lists have cycles.
      ListNode<Integer> temp = s2;
      do {
        temp = temp.next;
      } while (temp != s1 && temp != s2);
      return (temp == s1) ? s1 : null;
    }
    return null; // one list has cycle, and one list has no cycle.
  }

  // @exclude

  public static void main(String[] args) {
    ListNode<Integer> L1, L2;
    // L1: 1->2->3->null
    L1 = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
    L2 = L1.next.next;
    assert (overlappingLists(L1, L2).data == 3);
    // L2: 4->5->null
    L2 = new ListNode<Integer>(4, new ListNode<Integer>(5, null));
    assert (overlappingLists(L1, L2) == null);
    L1.next.next.next = L1;
    assert (overlappingLists(L1, L2) == null);
    L2.next.next = L2;
    assert (overlappingLists(L1, L2) == null);
    L2.next.next = L1;
    assert (overlappingLists(L1, L2) != null);
  }
}
