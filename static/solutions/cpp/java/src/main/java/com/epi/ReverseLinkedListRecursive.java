// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class ReverseLinkedListRecursive {
  // @include
  public static ListNode<Integer> reverseLinkedList(ListNode<Integer> head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode<Integer> newHead = reverseLinkedList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }
  // @exclude

  private static <T> void print(ListNode<T> head) {
    if (head != null) {
      System.out.println("(" + head.data + ")");
      print(head.next);
    }
  }

  public static void main(String[] args) {
    ListNode<Integer> l
        = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, null)));
    System.out.println("before reverse");
    print(l);
    ListNode<Integer> newhead = reverseLinkedList(l);
    System.out.println("\nafter reverse");
    print(newhead);
    newhead = reverseLinkedList(newhead);
    System.out.println("\nafter another reverse");
    print(newhead);
  }
}
