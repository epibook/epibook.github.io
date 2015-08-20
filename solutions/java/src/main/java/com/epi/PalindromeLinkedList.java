// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import static com.epi.ReverseLinkedListIterative.reverseLinkedList;

class PalindromeLinkedList {
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
    ListNode<Integer> firstHalfIter = L, secondHalfIter =
                                             reverseLinkedList(slow.next);
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

  public static <T> void printList(ListNode<T> L) {
    while (L != null) {
      System.out.print(L.data + " ");
      L = L.next;
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    ListNode<Integer> head = null;

    if (args.length > 1) {
      // Input the node's value in reverse order.
      for (String element : args) {
        ListNode<Integer> curr = new ListNode<>(Integer.parseInt(element), head);
        head = curr;
      }
      System.out.println(((isLinkedListAPalindrome(head)) ? "Yes" : "No"));
    } else {
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
}
