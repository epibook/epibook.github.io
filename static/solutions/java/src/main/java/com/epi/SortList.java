package com.epi;

import java.util.Random;

public class SortList {
  // @include
  public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
    // Base cases: L is empty or a single node, nothing to do.
    if (L == null || L.next == null) {
      return L;
    }

    // Find the midpoint of L using a slow and a fast pointer.
    ListNode<Integer> preSlow = null, slow = L, fast = L;
    while (fast != null && fast.next != null) {
      preSlow = slow;
      fast = fast.next.next;
      slow = slow.next;
    }

    preSlow.next = null; // Splits the list into two equal-sized lists.

    return MergeSortedLists.mergeTwoSortedLists(stableSortList(L),
                                                stableSortList(slow));
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      ListNode<Integer> L = null;
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(100);
      }
      for (int i = n; i > 0; --i) {
        ListNode<Integer> temp = new ListNode<>(r.nextInt(100), null);
        temp.next = L;
        L = temp;
      }

      ListNode<Integer> sortedHead = stableSortList(L);
      int count = 0;
      int pre = Integer.MIN_VALUE;
      while (sortedHead != null) {
        assert(pre <= sortedHead.data);
        pre = sortedHead.data;
        sortedHead = sortedHead.next;
        ++count;
      }
      assert(count == n);
    }
  }
}
