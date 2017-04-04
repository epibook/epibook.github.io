// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

/*
  @slug reverse-linked-list

  @problem
Write a function that reverses a singly linked list.
The function should use no more than constant storage beyond that needed for the
list itself.

*/

public class ReverseLinkedListIterative {
  // @include
  public static ListNode<Integer> reverseLinkedList(ListNode<Integer> head) {
    ListNode<Integer> prev = null, curr = head;
    while (curr != null) {
      ListNode<Integer> temp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = temp;
    }
    return prev;
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
