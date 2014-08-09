// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

class RemoveKthLastList {
  // @include
  public static Node<Integer> removeKthLast(Node<Integer> L, int k) {
    Node<Integer> dummyHead = new Node<>(0, L);
    // Advances k steps first.
    Node<Integer> ahead = dummyHead;
    while (k-- > 0) {
      ahead = ahead.next;
    }

    Node<Integer> pre = dummyHead;
    while (ahead != null && ahead.next != null) {
      pre = pre.next;
      ahead = ahead.next;
    }
    pre.next = pre.next.next;
    return dummyHead.next;
  }
  // @exclude

  public static void main(String[] args) {
    Node<Integer> L = new Node<>(1, new Node<>(2, new Node<>(3, null)));
    L = removeKthLast(L, 2);
    assert (L.data == 1 && L.next.data == 3);
    L = removeKthLast(L, 2);
    assert (L.data == 3 && L.next == null);
  }
}
