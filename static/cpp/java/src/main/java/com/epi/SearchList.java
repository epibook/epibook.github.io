package com.epi;

import java.util.Random;

public class SearchList {
  // @include
  public static ListNode<Integer> search(ListNode<Integer> L, int key) {
    while (L != null && L.data != key) {
      L = L.next;
    }
    // If key was not present in the list, L will have become null.
    return L;
  }
  // @exclude

  public static void main(String[] args) {
    ListNode<Integer> L;
    L = new ListNode<>(2, new ListNode<>(4, new ListNode<>(3, null)));
    assert(L == search(L, 2));
    assert(L.next == search(L, 4));
    assert(L.next.next == search(L, 3));
    assert(null == search(L, 100));
  }
}
