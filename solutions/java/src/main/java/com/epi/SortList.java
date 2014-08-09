package com.epi;

import java.util.Random;

public class SortList {
  // @include
  public static Node<Integer> sortList(Node<Integer> L) {
    // Base case. L has 0 or 1 node.
    if (L == null || L.next == null) {
      return L;
    }

    // Finds the middle point of L.
    Node<Integer> preSlow = null, slow = L, fast = L;
    while (fast != null && fast.next != null) {
      preSlow = slow;
      fast = fast.next.next;
      slow = slow.next;
    }

    preSlow.next = null; // Splits the list into two lists.
    return MergeSortedLists.mergeTwoSortedLinkedLists(sortList(L),
                                                      sortList(slow));
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      Node<Integer> L = null;
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(100);
      }
      for (int i = n; i > 0; --i) {
        Node<Integer> temp = new Node<>(r.nextInt(100), null);
        temp.next = L;
        L = temp;
      }

      Node<Integer> sortedHead = sortList(L);
      int count = 0;
      int pre = Integer.MIN_VALUE;
      while (sortedHead != null) {
        assert (pre <= sortedHead.data);
        pre = sortedHead.data;
        sortedHead = sortedHead.next;
        ++count;
      }
      assert (count == n);
    }
  }
}
