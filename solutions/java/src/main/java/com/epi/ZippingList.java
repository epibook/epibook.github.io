// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

import static com.epi.ReverseLinkedListIterative.reverseLinkedList;

public class ZippingList {
  // @include
  public static ListNode<Integer> zippingLinkedList(ListNode<Integer> L) {
    if (L == null || L.next == null) {
      return L;
    }

    // Find the second half of L.
    ListNode<Integer> slow = L, fast = L;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    ListNode<Integer> firstHalfHead = L, secondHalfHead = slow.next;
    slow.next = null; // Splits the list into two lists.
    secondHalfHead = reverseLinkedList(secondHalfHead);

    // Interleave the first half and the reversed of the second half.
    ListNode<Integer> firstHalfIter = firstHalfHead;
    ListNode<Integer> secondHalfIter = secondHalfHead;
    while (secondHalfIter != null) {
      ListNode<Integer> temp = secondHalfIter.next;
      secondHalfIter.next = firstHalfIter.next;
      firstHalfIter.next = secondHalfIter;
      firstHalfIter = firstHalfIter.next.next;
      secondHalfIter = temp;
    }
    return L;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    ListNode<Integer> head = null;
    int n = 0;
    if (args.length > 1) {
      for (String element : args) {
        ListNode<Integer> curr
            = new ListNode<>(Integer.parseInt(element), null);
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
        ListNode<Integer> curr = new ListNode<>(i, null);
        curr.next = head;
        head = curr;
      }
    }

    ListNode<Integer> curr = zippingLinkedList(head);
    int idx = 0, pre = 0;
    while (curr != null) {
      if (args.length <= 1) {
        if ((idx % 2) != 0) {
          assert(pre + curr.data == n);
        }
      }
      ++idx;
      System.out.println(curr.data);
      pre = curr.data;
      curr = curr.next;
    }
  }
}
