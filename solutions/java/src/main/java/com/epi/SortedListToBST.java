package com.epi;

public class SortedListToBST {
  // @include
  private static DoublyListNode<Integer> head;

  // Returns the root of the corresponding BST. The prev and next fields of the
  // list nodes are used as the BST nodes left and right fields, respectively.
  // n is the length of the list.
  public static DoublyListNode<Integer> buildBSTFromSortedDoublyLinkedList(
      DoublyListNode<Integer> L, int n) {
    head = L;
    return buildSortedDoublyLinkedListHelper(0, n);
  }

  // Builds a BST from the (start + 1)-th to the end-th node, inclusive, in L,
  // and returns the root.
  private static DoublyListNode<Integer> buildSortedDoublyLinkedListHelper(
      int start, int end) {
    if (start >= end) {
      return null;
    }

    int mid = start + ((end - start) / 2);
    DoublyListNode<Integer> left = buildSortedDoublyLinkedListHelper(start, mid);
    // Previous function call sets head to the successor of the maximum node in
    // the tree rooted at left.
    DoublyListNode<Integer> curr = new DoublyListNode<>(head.data, null, null);
    head = head.next;
    curr.prev = left;
    curr.next = buildSortedDoublyLinkedListHelper(mid + 1, end);
    return curr;
  }
  // @exclude

  private static <T extends Comparable<T>> void inOrderTraversal(
      DoublyListNode<T> node, T pre, int depth) {
    if (node != null) {
      inOrderTraversal(node.prev, pre, depth + 1);
      assert(pre.compareTo(node.data) <= 0);
      System.out.println(node.data + " ; depth = " + depth);
      inOrderTraversal(node.next, node.data, depth + 1);
    }
  }

  public static void main(String[] args) {
    DoublyListNode<Integer> temp0 = new DoublyListNode<>(0, null, null);
    DoublyListNode<Integer> temp1 = new DoublyListNode<>(1, null, null);
    DoublyListNode<Integer> temp2 = new DoublyListNode<>(2, null, null);
    DoublyListNode<Integer> temp3 = new DoublyListNode<>(3, null, null);
    temp0.next = temp1;
    temp1.next = temp2;
    temp2.next = temp3;
    temp3.next = null;
    temp0.prev = null;
    temp1.prev = temp0;
    temp2.prev = temp1;
    temp3.prev = temp2;

    DoublyListNode<Integer> L = temp0;
    DoublyListNode<Integer> tree = buildBSTFromSortedDoublyLinkedList(L, 4);
    inOrderTraversal(tree, -1, 0);
  }
}
