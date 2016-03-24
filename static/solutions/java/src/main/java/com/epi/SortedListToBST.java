package com.epi;

public class SortedListToBST {
  // @include
  private static DoublyLinkedListNode<Integer> head;

  // Returns the root of the corresponding BST. The prev and next fields of the
  // list nodes are used as the BST nodes left and right fields, respectively.
  // The length of the list is given.
  public static DoublyLinkedListNode<Integer> buildBSTFromSortedList(
      DoublyLinkedListNode<Integer> L, int length) {
    head = L;
    return buildSortedListHelper(0, length);
  }

  // Builds a BST from the (start + 1)-th to the end-th node, inclusive, in L,
  // and returns the root.
  private static DoublyLinkedListNode<Integer> buildSortedListHelper(int start,
                                                                     int end) {
    if (start >= end) {
      return null;
    }

    int mid = start + ((end - start) / 2);
    DoublyLinkedListNode<Integer> left = buildSortedListHelper(start, mid);
    // Previous function call sets head to the successor of the maximum node in
    // the tree rooted at left.
    DoublyLinkedListNode<Integer> curr
        = new DoublyLinkedListNode<>(head.data, left, null);
    head = head.next;
    curr.next = buildSortedListHelper(mid + 1, end);
    return curr;
  }
  // @exclude

  private static void inorderTraversal(DoublyLinkedListNode<Integer> node,
                                       Integer pre, int depth) {
    if (node != null) {
      inorderTraversal(node.prev, pre, depth + 1);
      assert(Integer.compare(pre, node.data) <= 0);
      System.out.println(node.data + " ; depth = " + depth);
      inorderTraversal(node.next, node.data, depth + 1);
    }
  }

  public static void main(String[] args) {
    DoublyLinkedListNode<Integer> temp0
        = new DoublyLinkedListNode<>(0, null, null);
    DoublyLinkedListNode<Integer> temp1
        = new DoublyLinkedListNode<>(1, null, null);
    DoublyLinkedListNode<Integer> temp2
        = new DoublyLinkedListNode<>(2, null, null);
    DoublyLinkedListNode<Integer> temp3
        = new DoublyLinkedListNode<>(3, null, null);
    temp0.next = temp1;
    temp1.next = temp2;
    temp2.next = temp3;
    temp3.next = null;
    temp0.prev = null;
    temp1.prev = temp0;
    temp2.prev = temp1;
    temp3.prev = temp2;

    DoublyLinkedListNode<Integer> L = temp0;
    DoublyLinkedListNode<Integer> tree = buildBSTFromSortedList(L, 4);
    inorderTraversal(tree, -1, 0);
  }
}
