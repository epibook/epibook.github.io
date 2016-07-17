// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class PalindromeLinkedList {
  // @include
  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    if (L == null) {
      return true;
    }

    // Finds the second half of L.
    ListNode<Integer> slow = L, fast = L;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    // Compare the first half and the reversed second half lists.
    ListNode<Integer> firstHalfIter = L;
    ListNode<Integer> secondHalfIter
        = ReverseLinkedListIterative.reverseLinkedList(slow);
    while (secondHalfIter != null && firstHalfIter != null) {
      if (secondHalfIter.data != firstHalfIter.data) {
        return false;
      }
      secondHalfIter = secondHalfIter.next;
      firstHalfIter = firstHalfIter.next;
    }
    return true;
  }
  // @exclude

  public static void main(String[] args) {
    if (args.length >= 1) {
      ListNode<Integer> head = null;
      // Input the node's value in reverse order.
      for (String element : args) {
        ListNode<Integer> curr
            = new ListNode<>(Integer.parseInt(element), head);
        head = curr;
      }
      System.out.println(((isLinkedListAPalindrome(head)) ? "Yes" : "No"));
    }
    assert(isLinkedListAPalindrome(null));
    assert(isLinkedListAPalindrome(new ListNode<>(1, null)));
    assert(isLinkedListAPalindrome(new ListNode<>(1, new ListNode<>(1, null))));
    assert(
        !isLinkedListAPalindrome(new ListNode<>(1, new ListNode<>(2, null))));
    assert(!isLinkedListAPalindrome(new ListNode<>(
        1, new ListNode<>(3, new ListNode<>(2, new ListNode<>(1, null))))));

    ListNode<Integer> head = null;
    // A link list is a palindrome.
    for (int i = 6; i >= 1; --i) {
      ListNode<Integer> curr = new ListNode<>(1, head);
      head = curr;
    }
    assert(isLinkedListAPalindrome(head));

    // Still a palindrome linked list.
    head = null;
    for (int i = 5; i >= 1; --i) {
      ListNode<Integer> curr = new ListNode<>(1, head);
      head = curr;
    }
    head.next.next.data = 3;
    assert(isLinkedListAPalindrome(head));
    // Not a palindrome linked list.
    head = null;
    for (int i = 5; i >= 1; --i) {
      ListNode<Integer> curr = new ListNode<>(i, head);
      head = curr;
    }
    assert(!isLinkedListAPalindrome(head));
  }
}
