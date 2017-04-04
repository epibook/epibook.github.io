// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import static com.epi.OverlappingListsNoCycle.advanceListByK;
import static com.epi.OverlappingListsNoCycle.overlappingNoCycleLists;

public class OverlappingLists {
  // @include
  public static ListNode<Integer> overlappingLists(ListNode<Integer> L1,
                                                   ListNode<Integer> L2) {
    // Store the start of cycle if any.
    ListNode<Integer> root1 = CheckingCycle.hasCycle(L1);
    ListNode<Integer> root2 = CheckingCycle.hasCycle(L2);

    if (root1 == null && root2 == null) {
      // Both lists don't have cycles.
      return overlappingNoCycleLists(L1, L2);
    } else if ((root1 != null && root2 == null)
               || (root1 == null && root2 != null)) {
      // One list has cycle, and one list has no cycle.
      return null;
    }
    // Both lists have cycles.
    ListNode<Integer> temp = root2;
    do {
      temp = temp.next;
    } while (temp != root1 && temp != root2);

    // L1 and L2 do not end in the same cycle.
    if (temp != root1) {
      return null; // Cycles are disjoint.
    }

    // L1 and L2 end in the same cycle, locate the overlapping node if they
    // first overlap before cycle starts.
    int stem1Length = distance(L1, root1), stem2Length = distance(L2, root2);
    int count = Math.abs(stem1Length - stem2Length);
    if (stem1Length > stem2Length) {
      L1 = advanceListByK(stem1Length - stem2Length, L1);
    } else {
      L2 = advanceListByK(stem2Length - stem1Length, L2);
    }
    while (L1 != L2 && L1 != root1 && L2 != root2) {
      L1 = L1.next;
      L2 = L2.next;
    }

    // If L1 == L2 before reaching root1, it means the overlap first occurs
    // before the cycle starts; otherwise, the first overlapping node is not
    // unique, so we can return any node on the cycle.
    return L1 == L2 ? L1 : root1;
  }

  // Calculates the distance between a and b.
  private static int distance(ListNode<Integer> a, ListNode<Integer> b) {
    int dis = 0;
    while (a != b) {
      a = a.next;
      ++dis;
    }
    return dis;
  }
  // @exclude

  private static void smallTest() {
    ListNode<Integer> l1 = null;
    ListNode<Integer> l2 = null;
    // L1: 1->2->3->4->5->6-
    //              ^  ^    |
    //              |  |____|
    // L2: 7->8-----
    l1 = new ListNode<>(
        1, new ListNode<>(
               2, new ListNode<>(
                      3, new ListNode<>(
                             4, new ListNode<>(5, new ListNode<>(6, null))))));
    l1.next.next.next.next.next.next = l1.next.next.next.next;

    l2 = new ListNode<>(7, new ListNode<>(8, null));
    l2.next.next = l1.next.next.next;
    assert(overlappingLists(l1, l2).data == 4);

    // L1: 1->2->3->4->5->6-
    //           ^     ^    |
    //           |     |____|
    // L2: 7->8---
    l2.next.next = l1.next.next;
    assert(overlappingLists(l1, l2).data == 3);
  }

  public static void main(String[] args) {
    smallTest();
    ListNode<Integer> L1, L2;
    // L1: 1->2->3->null
    L1 = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null)));
    L2 = L1.next.next;
    assert(overlappingLists(L1, L2).data == 3);
    // L2: 4->5->null
    L2 = new ListNode<>(4, new ListNode<>(5, null));
    assert(overlappingLists(L1, L2) == null);
    L1.next.next.next = L1;
    assert(overlappingLists(L1, L2) == null);
    L2.next.next = L2;
    assert(overlappingLists(L1, L2) == null);
    L2.next.next = L1;
    assert(overlappingLists(L1, L2) != null);
  }
}
