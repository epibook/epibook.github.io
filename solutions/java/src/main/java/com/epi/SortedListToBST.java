package com.epi;

public class SortedListToBST {
  // @include
  private static DoublyListNode<Integer> head;

  // Returns the root of the corresponding BST. The prev and next fields of 
  // the list nodes are used as the BST nodes left and right fields.
  public static DoublyListNode<Integer> buildBSTFromSortedDoublyLinkedList(
      DoublyListNode<Integer> L, int n) {
    head = L;
    return buildSortedDoublyLinkedListHelper(0, n);
  }

  // Builds a BST from the (s + 1)-th to the e-th node in L, and returns the
  // root. Node numbering is from 1 to n.
  private static DoublyListNode<Integer> buildSortedDoublyLinkedListHelper(
      int s, int e) {
    if (s >= e) {
      return null;
    }

    int m = s + ((e - s) / 2);
    DoublyListNode<Integer> left = buildSortedDoublyLinkedListHelper(s, m);
    // The last function call sets L to the successor of the maximum node in
    // the tree rooted at left.
    DoublyListNode<Integer> curr = new DoublyListNode<>(head.data, null, null);
    head = head.next;
    curr.prev = left;
    curr.next = buildSortedDoublyLinkedListHelper(m + 1, e);
    return curr;
  }
  // @exclude

  private static <T extends Comparable<T>>
  void inOrderTraversal(DoublyListNode<T> node,
                        T pre, int depth) {
    if (node != null) {
      inOrderTraversal(node.prev, pre, depth + 1);
      assert (pre.compareTo(node.data) <= 0);
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
    DoublyListNode<Integer> root = buildBSTFromSortedDoublyLinkedList(L, 4);
    inOrderTraversal(root, -1, 0);
  }
}
