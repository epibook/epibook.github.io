package com.epi;
/*

@slug
remove-dups-sorted-list

@summary
This problem is concerned with deleting repeated elements from a sorted array.
For example, for the array $\langle 2,3,5,5,7,11,11,11,13\rangle$, then after
deletion, the array is $\langle 2,3,5,7,11,13,0,0,0\rangle$.
After deleting repeated elements, there are $6$ valid entries.
There are no requirements as to the values stored beyond the last valid element.


@problem
Write a program which takes as input a sorted array and updates
it so that all duplicates have been removed and the remaining elements
have been shifted left to fill the emptied indices.  Return the number of valid
elements.
Many languages have library functions for performing this operation---you cannot
use these functions.

*/

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
