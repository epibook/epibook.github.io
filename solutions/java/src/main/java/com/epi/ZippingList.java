// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.Random;

class ZippingList {
  // @include
  public static Node<Integer> zippingLinkedList(Node<Integer> L) {
    if (L == null || L.next == null) {
      return L;
    }

    Node<Integer> slow = L, fast = L;
    // Find the middle point of L.
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    Node<Integer> reverseHead = slow.next;
    slow.next = null; // Splits the list into two lists.
    reverseHead = ReverseLinkedListIterative.reverseLinkedList(reverseHead);

    // Zipping the list.
    Node<Integer> curr = L;
    while (curr != null && reverseHead != null) {
      Node<Integer> temp = reverseHead.next;
      reverseHead.next = curr.next;
      curr.next = reverseHead;
      curr = curr.next.next;
      reverseHead = temp;
    }
    return L;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    Node<Integer> head = null;
    int n = 0;
    if (args.length > 1) {
      for (String element : args) {
        Node<Integer> curr = new Node<>(Integer.parseInt(element), null);
        curr.next = head;
        head = curr;
      }
    } else {
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }
      for (int i = n; i >= 0; --i) {
        Node<Integer> curr = new Node<>(i, null);
        curr.next = head;
        head = curr;
      }
    }

    Node<Integer> curr = zippingLinkedList(head);
    int idx = 0, pre = 0;
    while (curr != null) {
      if (args.length <= 1) {
        if ((idx & 1) != 0) {
          assert (pre + curr.data == n);
        }
      }
      ++idx;
      System.out.println(curr.data);
      pre = curr.data;
      curr = curr.next;
    }
  }
}
