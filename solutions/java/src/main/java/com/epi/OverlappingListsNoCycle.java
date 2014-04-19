// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

import com.epi.utils.Ref;

// @include
// Counts the list length till end.
class OverlappingListsNoCycle {
  public static <T> int countLen(Node<T> L) {
    int len = 0;
    while (L != null) {
      ++len;
      L = L.next;
    }
    return len;
  }

  public static <T> void advanceListByK(Ref<Node<T>> L, int k) {
    while (k-- > 0) {
      L.value = L.value.next;
    }
  }

  public static <T> Node<T> overlappingNoCycleLists(Node<T> L1, Node<T> L2) {
    // Count the lengths of L1 and L2.
    int l1Len = countLen(L1);
    int l2Len = countLen(L2);

    Ref<Node<T>> rl1 = new Ref<Node<T>>(L1);
    Ref<Node<T>> rl2 = new Ref<Node<T>>(L2);

    // Advance the longer list.
    advanceListByK(l1Len > l2Len ? rl1 : rl2, Math.abs(l1Len - l2Len));

    while (rl1.value != null && rl2.value != null && rl1.value != rl2.value) {
      rl1.value = rl1.value.next;
      rl2.value = rl2.value.next;
    }
    return rl1.value; // nullptr means no overlap between L1 and L2.
  }

  public static void main(String[] args) {
    Node<Integer> l1 = null;
    Node<Integer> l2 = null;
    // l1: 1->2->3->null
    l1 = new Node<Integer>(1, new Node<Integer>(2, new Node<Integer>(3, null)));
    l2 = l1.next.next;
    assert (overlappingNoCycleLists(l1, l2).data == 3);
    // l2: 4->5->null
    l2 = new Node<Integer>(4, new Node<Integer>(5, null));
    assert (overlappingNoCycleLists(l1, l2) == null);
  }
}
// @exclude

