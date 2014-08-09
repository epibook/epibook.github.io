package com.epi;

public class InsertionSortList {
  // @include
  public static NodeT<Integer> insertionSort(final NodeT<Integer> L) {
    if (L == null) {
      return L;
    }

    NodeT<Integer> dummyHead = new NodeT<>(0, L);
    NodeT<Integer> now = L;
    while (now != null && now.next != null) {
      if (now.data > now.next.data) {
        NodeT<Integer> target = now.next;
        NodeT<Integer> pre = dummyHead;
        while (pre.next.data < target.data) {
          pre = pre.next;
        }
        NodeT<Integer> temp = pre.next;
        pre.next = target;
        now.next = target.next;
        target.next = temp;
      } else {
        now = now.next;
      }
    }
    return dummyHead.next;
  }
  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<>(1, new NodeT<>(4, new NodeT<>(3,
        new NodeT<>(2, new NodeT<>(5, null)))));
    NodeT<Integer> result = insertionSort(L);
    NodeT<Integer> pre = null;
    while (result != null) {
      assert (pre == null || pre.data <= result.data);
      pre = result;
      System.out.println(result.data);
      result = result.next;
    }
  }
}
