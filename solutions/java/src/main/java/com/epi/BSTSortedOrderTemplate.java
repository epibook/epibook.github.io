package com.epi;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BSTSortedOrderTemplate {
  // @include
  public static <T> void printBSTInSortedOrder(BinarySearchTree<T> n) {
    LinkedList<BinarySearchTree<T>> s = new LinkedList<BinarySearchTree<T>>();
    BinarySearchTree<T> curr = n;

    while (!s.isEmpty() || curr != null) {
      if (curr != null) {
        s.push(curr);
        curr = curr.getLeft();
      } else {
        curr = s.pop();
        System.out.println(curr.getData());
        curr = curr.getRight();
      }
    }
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinarySearchTree<Integer> root = new BinarySearchTree<Integer>(3);
    root.setLeft(new BinarySearchTree<Integer>(2));
    root.getLeft().setLeft(new BinarySearchTree<Integer>(1));
    root.setRight(new BinarySearchTree<Integer>(5));
    root.getRight().setLeft(new BinarySearchTree<Integer>(4));
    root.getRight().setRight(new BinarySearchTree<Integer>(6));
    // should output 1 2 3 4 5 6
    printBSTInSortedOrder(root);
  }
}
