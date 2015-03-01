// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

import java.util.Random;

public class MergeSortedLists {
  //@include
  public static ListNode<Integer> mergeTwoSortedLinkedLists(
      ListNode<Integer> F, ListNode<Integer> L) {
    // Creates a placeholder for the result.
    ListNode<Integer> dummyHead = new ListNode<>(0, null);
    ListNode<Integer> current = dummyHead;
    ListNode<Integer> p1 = F, p2 = L;

    while (p1 != null && p2 != null) {
      if (p1.data <= p2.data) {
        current.next = p1;
        p1 = p1.next;
      } else {
        current.next = p2;
        p2 = p2.next;
      }
      current = current.next;
    }

    if (p1 != null) {
      // Appends the remaining nodes of p1.
      current.next = p1;
    }
    if (p2 != null) {
      // Appends the remaining nodes of p2.
      current.next = p2;
    }
    return dummyHead.next;
  }
  // @exclude

  public static void main(String[] args) {
    Random rnd = new Random();
    for (int times = 0; times < 10000; ++times) {
      ListNode<Integer> F = null;
      ListNode<Integer> L = null;
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(100);
        m = rnd.nextInt(100);
      }
      for (int i = n; i > 0; --i) {
        ListNode<Integer> temp = new ListNode<>(i, null);
        temp.next = F;
        F = temp;
      }
      for (int j = m; j > 0; --j) {
        ListNode<Integer> temp = new ListNode<>(j, null);
        temp.next = L;
        L = temp;
      }

      ListNode<Integer> sortedHead = mergeTwoSortedLinkedLists(F, L);
      int count = 0;
      int pre = Integer.MIN_VALUE;
      while (sortedHead != null) {
        assert (pre <= sortedHead.data);
        pre = sortedHead.data;
        sortedHead = sortedHead.next;
        ++count;
      }
      // Make sure the merged list have the same number of nodes as F and L.
      assert (count == n + m);
    }
  }
}
