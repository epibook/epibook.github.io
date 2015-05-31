package com.epi;

public class RemoveDuplicatesSortedList {
  // @include
  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    ListNode<Integer> iter = L;
    while (iter != null) {
      // Uses next_distinct to find the next distinct value.
      ListNode<Integer> nextDistinct = iter.next;
      while (nextDistinct != null && nextDistinct.data == iter.data) {
        nextDistinct = nextDistinct.next;
      }
      iter.next = nextDistinct;
      iter = nextDistinct;
    }
    return L;
  }
  // @exclude

  public static void main(String[] args) {
    ListNode<Integer> L;
    L = new ListNode<>(
        2, new ListNode<>(
               2, new ListNode<>(2, new ListNode<>(2, new ListNode<>(2, null)))));
    ListNode<Integer> pre = null;
    ListNode<Integer> result = removeDuplicates(L);
    int count = 0;
    while (result != null) {
      ++count;
      if (pre != null) {
        assert(!pre.data.equals(result.data));
      }
      System.out.println(result.data);
      pre = result;
      result = result.next;
    }
    assert(count == 1);
  }
}
