// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

class PalindromeLinkedListTemplate {

  // @include
  public static <T> boolean isLinkedListAPalindrome(Node<T> L) {
    // Find the middle point of L if L is odd length,
    // and right-middle point if L is even length.
    Node<T> slow = L, fast = L;
    while (fast != null) {
      fast = fast.next;
      if (fast != null) {
        fast = fast.next;
        slow = slow.next;
      }
    }

    // Compare the first half and reversed second half lists.
    Node<T> reverse = ReverseLinkedListIterativeTemplate
        .reverseLinkedList(slow);
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
        Node<Integer> curr = new Node<Integer>(Integer.parseInt(element), head);
        head = curr;
      }
      System.out.println(((isLinkedListAPalindrome(head)) ? "Yes" : "No"));
    } else {
      // A link list is a palindrome.
      for (int i = 6; i >= 1; --i) {
        Node<Integer> curr = new Node<Integer>(1, head);
        head = curr;
      }
      assert (isLinkedListAPalindrome(head) == true);

      // Still a palindrome linked list.
      head = null;
      for (int i = 5; i >= 1; --i) {
        Node<Integer> curr = new Node<Integer>(1, head);
        head = curr;
      }
      head.next.next.data = 3;
      assert (isLinkedListAPalindrome(head) == true);
      // Not a palindrome linked list.
      head = null;
      for (int i = 5; i >= 1; --i) {
        Node<Integer> curr = new Node<Integer>(i, head);
        head = curr;
      }
      assert (isLinkedListAPalindrome(head) == false);
    }
  }
}
