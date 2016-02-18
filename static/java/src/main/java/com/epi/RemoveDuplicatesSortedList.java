package com.epi;

public class RemoveDuplicatesSortedList {
  // @include
  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    ListNode<Integer> iter = L;
    while (iter != null) {
      // Uses nextDistinct to find the next distinct value.
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

  private static void simpleTest() {
    ListNode<Integer> L = null;
    ListNode<Integer> result = removeDuplicates(L);
    assert(result == null);
    L = new ListNode<Integer>(123, null);
    result = removeDuplicates(L);
    assert(result == L);
    L.next = new ListNode<Integer>(123, null);
    result = removeDuplicates(L);
    assert(result.next == null);

    // Creating an invalid input, 123 -> 124 -> 123, algo will not detect dups!
    L.next = new ListNode<Integer>(124, null);
    L.next.next = new ListNode<Integer>(123, null);
    result = removeDuplicates(L);
    assert(result.data == 123);
    assert(result.next.data == 124);
    assert(result.next.next.data == 123);
  }

  public static void main(String[] args) {
    simpleTest();
    ListNode<Integer> L;
    L = new ListNode<>(
        2,
        new ListNode<>(
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
