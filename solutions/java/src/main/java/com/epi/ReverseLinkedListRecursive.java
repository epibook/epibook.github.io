// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

class ReverseLinkedListRecursive {
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
    ListNode<Integer> l1 = new ListNode<>(1, null);
    ListNode<Integer> l2 = new ListNode<>(2, null);
    l1.next = l2;
    ListNode<Integer> l3 = new ListNode<>(3, null);
    l2.next = l3;

    System.out.println("before reverse");
    print(l1);
    ListNode<Integer> newhead = reverseLinkedList(l1);
    System.out.println("\nafter reverse");
    print(newhead);
    newhead = reverseLinkedList(newhead);
    System.out.println("\nafter another reverse");
    print(newhead);
  }
}
