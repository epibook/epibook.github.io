// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class ListPivoting {
  // @include
  public static ListNode<Integer> listPivoting(ListNode<Integer> L, int x) {
    ListNode<Integer> lessHead = new ListNode<>(0, null),
                      equalHead = new ListNode<>(0, null),
                      greaterHead = new ListNode<>(0, null);
    ListNode<Integer> lessIter = lessHead, equalIter = equalHead, greaterIter =
                                                                      greaterHead;
    // Populates the three lists.
    ListNode<Integer> iter = L;
    while (iter != null) {
      if (iter.data < x) {
        lessIter.next = iter;
        lessIter = iter;
      } else if (iter.data == x) {
        equalIter.next = iter;
        equalIter = iter;
      } else { // iter.data > x.
        greaterIter.next = iter;
        greaterIter = iter;
      }
      iter = iter.next;
    }
    lessIter.next = equalIter.next = greaterIter.next = null;

    // Combines the three lists.
    if (greaterHead.next != null) {
      equalIter.next = greaterHead.next;
    }
    if (equalHead.next != null) {
      lessIter.next = equalHead.next;
    }
    return lessHead.next;
  }
  // @exclude

  private static void simpleTest() {
    ListNode<Integer> L;
    L = new ListNode<>(0, null);
    ListNode<Integer> result = listPivoting(L, 0);
    assert(result == L);
    result = listPivoting(L, 1);
    assert(result == L);
    result = listPivoting(L, -1);
    assert(result == L);

    L = new ListNode<Integer>(2, new ListNode<>(0, null));
    result = listPivoting(L, -1);
    assert(result == L);

    L = new ListNode<Integer>(2, new ListNode<>(0, null));
    result = listPivoting(L, 1);
    assert(result.data == 0 && result.next.data == 2);

    L = new ListNode<Integer>(2, new ListNode<>(0, new ListNode<>(-2, null)));
    result = listPivoting(L, 1);
    assert(result.data == 0 && result.next.data == -2 &&
           result.next.next.data == 2);
  }

  public static void main(String[] args) {
    simpleTest();
    ListNode<Integer> L;
    L = new ListNode<>(
        1, new ListNode<>(
               4, new ListNode<>(3, new ListNode<>(2, new ListNode<>(5, null)))));
    int x = 4;
    ListNode<Integer> result = listPivoting(L, x);
    boolean beforeX = true;
    while (result != null) {
      if (result.data >= x) {
        beforeX = false;
      }
      if (beforeX) {
        assert(result.data < x);
      } else {
        assert(result.data >= x);
      }
      System.out.println(result.data);
      result = result.next;
    }
  }
}
