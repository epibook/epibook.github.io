package com.epi;

import com.epi.DoublyLinkedListPrototypeTemplate.NodeT;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SortedListToBST {
  // @include
  private static NodeT<Integer> head;

  // Returns the root of the corresponding BST. The prev and next
  // fields of the list nodes are used as the BST nodes left and right fields.
  public static NodeT<Integer> buildBSTFromSortedDoublyLinkedList(
      NodeT<Integer> L, int n) {
    head = L;
    return buildSortedDoublyLinkedListHelper(0, n);
  }

  // Builds a BST from the (s + 1)-th to the e-th node in L, and returns the
  // root. Node numbering is from 1 to n.
  private static NodeT<Integer> buildSortedDoublyLinkedListHelper(int s,
                                                                  int e) {
    if (s >= e) {
      return null;
    }

    int m = s + ((e - s) / 2);
    NodeT<Integer> left = buildSortedDoublyLinkedListHelper(s, m);
    // The last function call sets L to the successor of the maximum node in
    // the tree rooted at left.
    NodeT<Integer> curr = new NodeT<>(head.getData());
    head = head.getNext();
    curr.setPrev(left);
    curr.setNext(buildSortedDoublyLinkedListHelper(m + 1, e));
    return curr;
  }
  // @exclude

  private static <T extends Comparable<T>>
  void inOrderTraversal(NodeT<T> node,
                        T pre, int depth) {
    if (node != null) {
      inOrderTraversal(node.getPrev(), pre, depth + 1);
      assert (pre.compareTo(node.getData()) <= 0);
      System.out.println(node.getData() + " ; depth = " + depth);
      inOrderTraversal(node.getNext(), node.getData(), depth + 1);
    }
  }

  public static void main(String[] args) {
    NodeT<Integer> temp0 = new NodeT<>(0);
    NodeT<Integer> temp1 = new NodeT<>(1);
    NodeT<Integer> temp2 = new NodeT<>(2);
    NodeT<Integer> temp3 = new NodeT<>(3);
    temp0.setNext(temp1);
    temp1.setNext(temp2);
    temp2.setNext(temp3);
    temp3.setNext(null);
    temp0.setPrev(null);
    temp1.setPrev(temp0);
    temp2.setPrev(temp1);
    temp3.setPrev(temp2);

    NodeT<Integer> L = temp0;
    NodeT<Integer> root = buildBSTFromSortedDoublyLinkedList(L, 4);
    inOrderTraversal(root, -1, 0);
  }
}
