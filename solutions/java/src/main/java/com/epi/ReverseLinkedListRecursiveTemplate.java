// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

class ReverseLinkedListRecursiveTemplate {

  // @include
  public static <T> Node<T> reverseLinkedList(Node<T> head) {
    if (head == null || head.next == null) {
      return head;
    }

    Node<T> newHead = reverseLinkedList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }

  // @exclude

  public static <T> void print(Node<T> head) {
    if (head != null) {
      System.out.println("(" + head.data + ")");
      print(head.next);
    }
  }

  public static void main(String[] args) {
    Node<Integer> l1 = new Node<Integer>(1, null);
    Node<Integer> l2 = new Node<Integer>(2, null);
    l1.next = l2;
    Node<Integer> l3 = new Node<Integer>(3, null);
    l2.next = l3;

    System.out.println("before reverse");
    print(l1);
    Node<Integer> newhead = reverseLinkedList(l1);
    System.out.println("\nafter reverse");
    print(newhead);
    newhead = reverseLinkedList(newhead);
    System.out.println("\nafter another reverse");
    print(newhead);
  }
}
