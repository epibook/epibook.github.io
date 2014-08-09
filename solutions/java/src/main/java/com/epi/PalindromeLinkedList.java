// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

class PalindromeLinkedList {

  // @include
  public static boolean isLinkedListAPalindrome(Node<Integer> L) {
    Node<Integer> slow = L, fast = L;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    // Compare the first half and the reversed second half lists.
    Node<Integer> reverse =
        ReverseLinkedListIterative.reverseLinkedList(slow.next);
    while (reverse != null && L != null) {
      if (reverse.data != L.data) {
        return false;
      }
      reverse = reverse.next;
      L = L.next;
    }
    return true;
  }
  // @exclude

  public static <T> void printList(Node<T> L) {
    while (L != null) {
      System.out.print(L.data + " ");
      L = L.next;
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    Node<Integer> head = null;

    if (args.length > 1) {
      // Input the node's value in reverse order.
      for (String element : args) {
        Node<Integer> curr = new Node<>(Integer.parseInt(element), head);
        head = curr;
      }
      System.out.println(((isLinkedListAPalindrome(head)) ? "Yes" : "No"));
    } else {
      // A link list is a palindrome.
      for (int i = 6; i >= 1; --i) {
        Node<Integer> curr = new Node<>(1, head);
        head = curr;
      }
      assert (isLinkedListAPalindrome(head));

      // Still a palindrome linked list.
      head = null;
      for (int i = 5; i >= 1; --i) {
        Node<Integer> curr = new Node<>(1, head);
        head = curr;
      }
      head.next.next.data = 3;
      assert (isLinkedListAPalindrome(head));
      // Not a palindrome linked list.
      head = null;
      for (int i = 5; i >= 1; --i) {
        Node<Integer> curr = new Node<>(i, head);
        head = curr;
      }
      assert (!isLinkedListAPalindrome(head));
    }
  }
}
