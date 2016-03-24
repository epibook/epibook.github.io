// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class RemoveKthLastList {
  // @include
  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> dummyHead = new ListNode<>(0, L);
    ListNode<Integer> first = dummyHead.next;
    while (k-- > 0) {
      first = first.next;
    }

    ListNode<Integer> second = dummyHead;
    while (first != null) {
      second = second.next;
      first = first.next;
    }
    // second points to the (k + 1)-th last node, deletes its successor.
    second.next = second.next.next;
    return dummyHead.next;
  }
  // @exclude

  public static void main(String[] args) {
    ListNode<Integer> L
        = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null)));
    L = removeKthLast(L, 2);
    assert(L.data == 1 && L.next.data == 3);
    L = removeKthLast(L, 2);
    assert(L.data == 3 && L.next == null);
    L = removeKthLast(L, 1);
    assert(L == null);
  }
}
