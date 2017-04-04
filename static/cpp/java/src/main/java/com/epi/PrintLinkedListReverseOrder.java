// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Deque;
import java.util.LinkedList;

public class PrintLinkedListReverseOrder {
  // @include
  public static void printLinkedListInReverse(ListNode<Integer> head) {
    Deque<Integer> nodes = new LinkedList<>();
    while (head != null) {
      nodes.addFirst(head.data);
      head = head.next;
    }
    while (!nodes.isEmpty()) {
      System.out.println(nodes.poll());
    }
  }
  // @exclude

  public static void main(String[] args) {
    printLinkedListInReverse(
        new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null))));
  }
}
