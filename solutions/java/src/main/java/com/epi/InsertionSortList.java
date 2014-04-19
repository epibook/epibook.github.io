package com.epi;

public class InsertionSortList {
  // @include
  public static NodeT<Integer> insertionSort(final NodeT<Integer> L) {
    NodeT<Integer> head = null;
    NodeT<Integer> tail = null;
    NodeT<Integer> now = L;
    while (now != null) {
      NodeT<Integer> next = now.next;
      now.next = null;
      if (head != null) {
        if (now.data < head.data) {
          now.next = head;
          head = now;
        } else {
          NodeT<Integer> preIt = null;
          NodeT<Integer> it = head;
          while (it != null && now.data >= it.data) {
            preIt = it;
            it = it.next;
          }
          preIt.next = now;
          now.next = it;
          if (it == null) {
            tail = now;
          }
        }
      } else {
        head = tail = now;
      }
      now = next;
    }
    return head;
  }

  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<Integer>(1, new NodeT<Integer>(4, new NodeT<Integer>(3,
        new NodeT<Integer>(2, new NodeT<Integer>(5, null)))));
    NodeT<Integer> res = insertionSort(L);
    NodeT<Integer> pre = null;
    while (res != null) {
      assert (pre == null || pre.data <= res.data);
      pre = res;
      System.out.println(res.data);
      res = res.next;
    }
  }
}
