// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

class OverlappingListsTemplate {
  // @include
  public static <T> Node<T> overlappingLists(Node<T> L1, Node<T> L2) {
    // Store the start of cycle if any.
    Node<T> s1 = CheckingCycle.hasCycle(L1), s2 = CheckingCycle.hasCycle(L2);

    if (s1 == null && s2 == null) {
      return OverlappingListsNoCycle.overlappingNoCycleLists(L1, L2);
    } else if (s1 != null && s2 != null) { // both lists have cycles.
      Node<T> temp = s2;
      do {
        temp = temp.next;
      } while (temp != s1 && temp != s2);
      return (temp == s1) ? s1 : null;
    }
    return null; // one list has cycle, and one list has no cycle.
  }

  // @exclude

  public static void main(String[] args) {
    Node<Integer> L1, L2;
    // L1: 1->2->3->null
    L1 = new Node<Integer>(1, new Node<Integer>(2, new Node<Integer>(3, null)));
    L2 = L1.next.next;
    assert (overlappingLists(L1, L2).data == 3);
    // L2: 4->5->null
    L2 = new Node<Integer>(4, new Node<Integer>(5, null));
    assert (overlappingLists(L1, L2) == null);
    L1.next.next.next = L1;
    assert (overlappingLists(L1, L2) == null);
    L2.next.next = L2;
    assert (overlappingLists(L1, L2) == null);
    L2.next.next = L1;
    assert (overlappingLists(L1, L2) != null);
  }
}
