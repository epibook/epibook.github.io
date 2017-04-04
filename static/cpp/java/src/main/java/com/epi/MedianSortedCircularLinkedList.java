// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

public class MedianSortedCircularLinkedList {
  // @include
  public static double findMedianSortedCircularLinkedList(
      ListNode<Integer> arbitraryNode) {
    // Checks if all nodes are identical and identifies the first smallest node.
    ListNode<Integer> iter = arbitraryNode, firstSmallestNode = arbitraryNode;
    int n = 0;
    do {
      if (Integer.compare(iter.data, iter.next.data) > 0) {
        firstSmallestNode = iter.next;
      }
      ++n;
      iter = iter.next;
    } while (iter != arbitraryNode);

    // Advances to the middle of the list.
    for (int i = 0; i < ((n - 1) / 2); ++i) {
      firstSmallestNode = firstSmallestNode.next;
    }
    return (n % 2) != 0
        ? firstSmallestNode.data
        : 0.5 * (firstSmallestNode.data + firstSmallestNode.next.data);
  }
  // @exclude

  private static void smallTest() {
    ListNode<Integer> L = new ListNode<>(
        0, new ListNode<>(2, new ListNode<>(2, new ListNode<>(2, null))));
    L.next.next.next.next = L;
    assert(2 == findMedianSortedCircularLinkedList(L.next.next));
  }

  public static void main(String[] args) {
    smallTest();
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
