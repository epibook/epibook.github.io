// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

public class MergeSortedLists {
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    // Creates a placeholder for the result.
    ListNode<Integer> dummyHead = new ListNode<>(0, null);
    ListNode<Integer> current = dummyHead;
    ListNode<Integer> p1 = L1, p2 = L2;

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

    // Appends the remaining nodes of p1 or p2.
    current.next = p1 != null ? p1 : p2;
    return dummyHead.next;
  }
  // @exclude

  private static void simpleTest() {
    ListNode<Integer> L1 = null;
    ListNode<Integer> L2 = null;
    ListNode<Integer> result = mergeTwoSortedLists(L1, L2);
    assert(result == null);

    L1 = new ListNode(123, null);
    result = mergeTwoSortedLists(L1, L2);
    assert(result.data == 123);

    L2 = L1;
    L1 = null;
    result = mergeTwoSortedLists(L1, L2);
    assert(result.data == 123);

    L1 = new ListNode(-123, null);
    L2 = new ListNode(123, null);
    result = mergeTwoSortedLists(L1, L2);
    assert(result.data == -123 && result.next.data == 123
           && result.next.next == null);
  }

  public static void main(String[] args) {
    simpleTest();
    Random rnd = new Random();
    for (int times = 0; times < 10000; ++times) {
      ListNode<Integer> L1 = null;
      ListNode<Integer> L2 = null;
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
        temp.next = L1;
        L1 = temp;
      }
      for (int j = m; j > 0; --j) {
        ListNode<Integer> temp = new ListNode<>(j, null);
        temp.next = L2;
        L2 = temp;
      }

      ListNode<Integer> sortedHead = mergeTwoSortedLists(L1, L2);
      int count = 0;
      int pre = Integer.MIN_VALUE;
      while (sortedHead != null) {
        assert(pre <= sortedHead.data);
        pre = sortedHead.data;
        sortedHead = sortedHead.next;
        ++count;
      }
      // Make sure the merged list have the same number of nodes as L1 and L2.
      assert(count == n + m);
    }
  }
}
