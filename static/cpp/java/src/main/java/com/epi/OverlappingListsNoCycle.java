// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class OverlappingListsNoCycle {
  // @include
  public static ListNode<Integer> overlappingNoCycleLists(
      ListNode<Integer> L1, ListNode<Integer> L2) {
    int L1Length = length(L1), L2Length = length(L2);

    // Advances the longer list to get equal length lists.
    if (L1Length > L2Length) {
      L1 = advanceListByK(L1Length - L2Length, L1);
    } else {
      L2 = advanceListByK(L2Length - L1Length, L2);
    }

    while (L1 != null && L2 != null && L1 != L2) {
      L1 = L1.next;
      L2 = L2.next;
    }
    return L1; // nullptr implies there is no overlap between L1 and L2.
  }

  public static ListNode<Integer> advanceListByK(int k, ListNode<Integer> L) {
    while (k-- > 0) {
      L = L.next;
    }
    return L;
  }

  private static int length(ListNode<Integer> L) {
    int len = 0;
    while (L != null) {
      ++len;
      L = L.next;
    }
    return len;
  }
  // @exclude

  public static void main(String[] args) {
    ListNode<Integer> L1 = null;
    ListNode<Integer> L2 = null;
    // L1: 1->2->3->null
    L1 = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null)));
    L2 = L1.next.next;
    assert(overlappingNoCycleLists(L1, L2).data == 3);
    // L2: 4->5->null
    L2 = new ListNode<>(4, new ListNode<>(5, null));
    assert(overlappingNoCycleLists(L1, L2) == null);
  }
}
