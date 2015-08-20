// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.Random;

class MedianSortedCircularLinkedList {
  // @include
  public static double findMedianSortedCircularLinkedList(
      ListNode<Integer> arbitraryNode) {
    // Checks if all nodes are identical and identifies the first smallest node.
    ListNode<Integer> iter = arbitraryNode, firstSmallestNode = arbitraryNode;
    int n = 0;
    do {
      ++n;
      iter = iter.next;
      if (firstSmallestNode.data.compareTo(iter.data) <= 0) {
        // Now firstSmallestNode points to the largest element.
        firstSmallestNode = iter;
      }
    } while (iter != arbitraryNode);
    // firstSmallestNode's next is the first smallest node.
    firstSmallestNode = firstSmallestNode.next;

    // Advances to the middle of the list.
    for (int i = 0; i < ((n - 1) / 2); ++i) {
      firstSmallestNode = firstSmallestNode.next;
    }
    return (n % 2) != 0
        ? firstSmallestNode.data
        : 0.5 * (firstSmallestNode.data + firstSmallestNode.next.data);
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
      ListNode<Integer> head = null;
      for (int i = n; i >= 0; --i) {
        ListNode<Integer> curr = new ListNode<>(i, null);
        curr.next = head;
        head = curr;
      }
      ListNode<Integer> curr = head;
      if (curr != null) {
        while (curr.next != null) {
          curr = curr.next;
        }
        curr.next = head; // make the list as a circular list.
      }
      double res = findMedianSortedCircularLinkedList(head.next);
      System.out.println(res);
      assert(res == 0.5 * n);
    }

    // Test identical list.
    ListNode<Integer> head = null;
    for (int i = 0; i < 10; ++i) {
      ListNode<Integer> curr = new ListNode<>(5, null);
      curr.next = head;
      head = curr;
    }
    ListNode<Integer> curr = head;
    if (curr != null) {
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = head; // make the list as a circular list.
    }
    assert(5 == findMedianSortedCircularLinkedList(head));
  }
}
