package com.epi;

import com.epi.DoublyLinkedListPrototypeTemplate.NodeT;
import com.epi.utils.Ref;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SortedListToBST {
  // @include
  // Build a BST from the (s + 1)-th to the e-th node in L.
  // Node numbering is from 1 to n.
  private static <T> NodeT<T> fromSortedDoublyLinkedListHelper(Ref<NodeT<T>> L,
      int s, int e) {
    NodeT<T> curr = null;
    if (s < e) {
      int m = s + ((e - s) >> 1);
      NodeT<T> tempLeft = fromSortedDoublyLinkedListHelper(L, s, m);
      curr = new NodeT<T>(L.value.getData()); // the last function call sets L
                                              // to the successor of the
      // maximum node in the tree rooted at tempLeft.
      L.value = L.value.getNext();
      curr.setPrev(tempLeft);
      curr.setNext(fromSortedDoublyLinkedListHelper(L, m + 1, e));
    }
    return curr;
  }

  public static <T> NodeT<T> buildBSTFromSortedDoublyLinkedList(NodeT<T> l,
      int n) {
    return fromSortedDoublyLinkedListHelper(new Ref<NodeT<T>>(l), 0, n);
  }

  // @exclude

  private static <T extends Comparable<T>> void inOrderTraversal(NodeT<T> node,
      T pre, int depth) {
    if (node != null) {
      inOrderTraversal(node.getPrev(), pre, depth + 1);
      assert (pre.compareTo(node.getData()) <= 0);
      System.out.println(node.getData() + " ; depth = " + depth);
      inOrderTraversal(node.getNext(), node.getData(), depth + 1);
    }
  }

  public static void main(String[] args) {
    NodeT<Integer> temp0 = new NodeT<Integer>(0);
    NodeT<Integer> temp1 = new NodeT<Integer>(1);
    NodeT<Integer> temp2 = new NodeT<Integer>(2);
    NodeT<Integer> temp3 = new NodeT<Integer>(3);
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
