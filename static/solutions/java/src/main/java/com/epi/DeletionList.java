// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

public class DeletionList {
  // @include
  // Assumes nodeToDelete is not tail.
  public static void deletionFromList(ListNode<Integer> nodeToDelete) {
    nodeToDelete.data = nodeToDelete.next.data;
    nodeToDelete.next = nodeToDelete.next.next;
  }
  // @exclude

  public static void main(String[] args) {
    ListNode<Integer> L
        = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null)));
    deletionFromList(L);
    assert(L.data == 2 && L.next.data == 3);
  }
}
