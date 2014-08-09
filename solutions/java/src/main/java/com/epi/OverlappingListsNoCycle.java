// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

class OverlappingListsNoCycle {
  // @include
  public static Node<Integer> overlappingNoCycleLists(Node<Integer> L1,
                                                      Node<Integer> L2) {
    // Count the lengths of L1 and L2.
    int l1Length = countLength(L1);
    int l2Length = countLength(L2);

    // Advances the longer list.
    int count = Math.abs(l1Length - l2Length);
    if (l1Length > l2Length) {
      while (count-- > 0) {
        L1 = L1.next;
      }
    } else {
      while (count-- > 0) {
        L2 = L2.next;
      }
    }

    while (L1 != null && L2 != null && L1 != L2) {
      L1 = L1.next;
      L2 = L2.next;
    }
    return L1; // null means no overlap between L1 and L2.
  }

  // Counts the list length till end.
  private static int countLength(Node<Integer> L) {
    int len = 0;
    while (L != null) {
      ++len;
      L = L.next;
    }
    return len;
  }
  // @exclude

  public static void main(String[] args) {
    Node<Integer> l1 = null;
    Node<Integer> l2 = null;
    // l1: 1->2->3->null
    l1 = new Node<>(1, new Node<>(2, new Node<>(3, null)));
    l2 = l1.next.next;
    assert (overlappingNoCycleLists(l1, l2).data == 3);
    // l2: 4->5->null
    l2 = new Node<>(4, new Node<>(5, null));
    assert (overlappingNoCycleLists(l1, l2) == null);
  }
}
