// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

import com.epi.utils.Ref;

class RemoveKthLastListTemplate {
  // @include
  public static <T> void removeKthLast(Ref<Node<T>> L, int k) {
    // Advances k steps first.
    Node<T> ahead = L.value;
    int num = k;
    while (ahead != null && num != 0) {
      ahead = ahead.next;
      --num;
    }

    if (num != 0) {
      throw new IllegalArgumentException("not enough nodes in the list");
    }

    Node<T> pre = null, curr = L.value;
    // Finds the k-th last node.
    while (ahead != null) {
      pre = curr;
      curr = curr.next;
      ahead = ahead.next;
    }
    if (pre != null) {
      pre.next = curr.next;
    } else {
      L.value = curr.next; // special case: delete L.
    }
  }

  // @exclude

  public static void main(String[] args) {
    Ref<Node<Integer>> L = new Ref<Node<Integer>>(new Node<Integer>(1,
        new Node<Integer>(2, new Node<Integer>(3, null))));
    try {
      removeKthLast(L, 4);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    removeKthLast(L, 2);
    assert (L.value.data == 1 && L.value.next.data == 3);
    removeKthLast(L, 2);
    assert (L.value.data == 3 && L.value.next == null);
  }
}
