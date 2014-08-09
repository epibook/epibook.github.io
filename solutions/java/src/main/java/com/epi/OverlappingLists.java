// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

class OverlappingLists {
  // @include
  public static Node<Integer> overlappingLists(Node<Integer> L1,
                                               Node<Integer> L2) {
    // Store the start of cycle if any.
    Node<Integer> s1 = CheckingCycle.hasCycle(L1),
                  s2 = CheckingCycle.hasCycle(L2);

    if (s1 == null && s2 == null) {
      return OverlappingListsNoCycle.overlappingNoCycleLists(L1, L2);
    } else if (s1 != null && s2 != null) { // Both lists have cycles.
      Node<Integer> temp = s2;
      do {
        temp = temp.next;
      } while (temp != s1 && temp != s2);

      // L1 and L2 do not end in the same cycle.
      if (temp != s1) {
        return null;
      }

      // Since L1 and L2 are end in the same cycle, find the overlapping node
      // if it happens before cycle starts.
      int l1S1Dis = distanceAB(L1, s1), l2S2Dis = distanceAB(L2, s2);
      int count = Math.abs(l1S1Dis - l2S2Dis);
      if (l1S1Dis > l2S2Dis) {
        while (count-- > 0) {
          L1 = L1.next;
        }
      } else {
        while (count-- > 0) {
          L2 = L2.next;
        }
      }
      while (L1 != L2 && L1 != s1 && L2 != s2) {
        L1 = L1.next;
        L2 = L2.next;
      }

      // If L1 == L2 before reaching s1, it means overlapping node happens
      // before cycle starts; otherwise, the node cycle starts is one of the
      // overlapping nodes.
      return L1 == L2 ? L1 : s1;
    }
    return null; // One list has cycle, and one list has no cycle.
  }

  // Calculates the distance between a and b.
  private static int distanceAB(Node<Integer> a, Node<Integer> b) {
    int dis = 0;
    while (a != b) {
      a = a.next;
      ++dis;
    }
    return dis;
  }
  // @exclude


  private static void smallTest() {
    Node<Integer> l1 = null;
    Node<Integer> l2 = null;
    // L1: 1->2->3->4->5->6-
    //              ^  ^    |
    //              |  |____|
    // L2: 7->8-----
    l1 = new Node<>(1, new Node<>(2, new Node<>(3, new Node<>(4, new Node<>(5, new Node<>(6, null))))));
    l1.next.next.next.next.next.next = l1.next.next.next.next;

    l2 = new Node<>(7, new Node<>(8, null));
    l2.next.next = l1.next.next.next;
    assert (overlappingLists(l1, l2).data == 4);

    // L1: 1->2->3->4->5->6-
    //           ^     ^    |
    //           |     |____|
    // L2: 7->8---
    l2.next.next = l1.next.next;
    assert (overlappingLists(l1, l2).data == 3);
  }

  public static void main(String[] args) {
    smallTest();
    Node<Integer> L1, L2;
    // L1: 1->2->3->null
    L1 = new Node<>(1, new Node<>(2, new Node<>(3, null)));
    L2 = L1.next.next;
    assert (overlappingLists(L1, L2).data == 3);
    // L2: 4->5->null
    L2 = new Node<>(4, new Node<>(5, null));
    assert (overlappingLists(L1, L2) == null);
    L1.next.next.next = L1;
    assert (overlappingLists(L1, L2) == null);
    L2.next.next = L2;
    assert (overlappingLists(L1, L2) == null);
    L2.next.next = L1;
    assert (overlappingLists(L1, L2) != null);
  }
}
