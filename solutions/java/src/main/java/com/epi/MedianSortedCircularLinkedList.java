// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.Random;

class MedianSortedCircularLinkedList {
  // @include
  public static double findMedianSortedCircularLinkedList(Node<Integer> rNode) {
    // Checks all nodes are identical or not and identify the start of list.
    Node<Integer> curr = rNode;
    Node<Integer> start = rNode;
    int count = 0;
    do {
      ++count;
      curr = curr.next;
      // start will point to the largest element in the list.
      if (start.data.compareTo(curr.data) <= 0) {
        start = curr;
      }
    } while (curr != rNode);
    // start's next is the begin of the list.
    start = start.next;

    // Traverses to the middle of the list and returns the median.
    for (int i = 0; i < ((count - 1) / 2); ++i) {
      start = start.next;
    }
    return (count & 1) != 0 ? start.data : 0.5 * (start.data + start.next.data);
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }
      Node<Integer> head = null;
      for (int i = n; i >= 0; --i) {
        Node<Integer> curr = new Node<>(i, null);
        curr.next = head;
        head = curr;
      }
      Node<Integer> curr = head;
      if (curr != null) {
        while (curr.next != null) {
          curr = curr.next;
        }
        curr.next = head; // make the list as a circular list.
      }
      double res = findMedianSortedCircularLinkedList(head.next);
      System.out.println(res);
      assert (res == 0.5 * n);
    }

    // Test identical list.
    Node<Integer> head = null;
    for (int i = 0; i < 10; ++i) {
      Node<Integer> curr = new Node<>(5, null);
      curr.next = head;
      head = curr;
    }
    Node<Integer> curr = head;
    if (curr != null) {
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = head; // make the list as a circular list.
    }
    assert (5 == findMedianSortedCircularLinkedList(head));
  }
}
