package com.epi;

public class RemoveDuplicatesSortedList {
  // @include
  public static NodeT<Integer> removeDuplicates(NodeT<Integer> L) {
    NodeT<Integer> pre = null, now = L;
    while (now != null) {
      if (pre != null && pre.data.equals(now.data)) {
        pre.next = now.next;
        now = now.next;
      } else {
        pre = now;
        now = now.next;
      }
    }
    return L;
  }

  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<Integer>(2, new NodeT<Integer>(2, new NodeT<Integer>(2,
        new NodeT<Integer>(2, new NodeT<Integer>(2, null)))));
    NodeT<Integer> pre = null;
    NodeT<Integer> res = removeDuplicates(L);
    while (res != null) {
      if (pre != null) {
        assert (!pre.data.equals(res.data));
      }
      System.out.println(res.data);
      pre = res;
      res = res.next;
    }
  }
}
