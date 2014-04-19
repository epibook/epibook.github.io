// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.Random;

import com.epi.utils.Ref;

class ZippingListTemplate {
  // @include
  public static <T> void connectANextToBAdvanceA(Ref<Node<T>> a, Node<T> b) {
    Node<T> temp = a.value.next;
    a.value.next = b;
    a.value = temp;
  }

  public static <T> Node<T> zippingLinkedList(Node<T> L) {
    Node<T> slow = L, fast = L, preSlow = null;

    // Find the middle point of L.
    while (fast != null) {
      fast = fast.next;
      if (fast != null) {
        preSlow = slow;
        fast = fast.next;
        slow = slow.next;
      }
    }

    if (preSlow == null) {
      return L; // only contains one node in the list.
    }
    preSlow.next = null; // splits the list into two lists.
    Node<T> reverse = ReverseLinkedListIterativeTemplate
        .reverseLinkedList(slow);
    Node<T> curr = L;

    // Zipping the list.
    while (curr != null && reverse != null) {
      Node<T> temp = curr.next;
      curr.next = reverse;
      curr = temp;
      // Connect curr->next to reverse, and advance curr.
      // connectANextToBAdvanceA(ref_curr, reverse);
      if (curr != null) {
        // Connect reverse->next to curr, and advance reverse.
        Node<T> temp2 = reverse.next;
        reverse.next = curr;
        reverse = temp2;
        // connectANextToBAdvanceA(ref_reverse, curr);
      }
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
        Node<Integer> curr = new Node<Integer>(Integer.parseInt(element), null);
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
        Node<Integer> curr = new Node<Integer>(i, null);
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
