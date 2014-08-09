package com.epi;

import java.util.Stack;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BSTSortedOrder {
  // @include
  public static void printBSTInSortedOrder(BinarySearchTree<Integer> n) {
    Stack<BinarySearchTree<Integer>> s = new Stack<>();
    BinarySearchTree<Integer> curr = n;

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
    BinarySearchTree<Integer> root = new BinarySearchTree<>(3);
    root.setLeft(new BinarySearchTree<>(2));
    root.getLeft().setLeft(new BinarySearchTree<>(1));
    root.setRight(new BinarySearchTree<>(5));
    root.getRight().setLeft(new BinarySearchTree<>(4));
    root.getRight().setRight(new BinarySearchTree<>(6));
    // should output 1 2 3 4 5 6
    printBSTInSortedOrder(root);
  }
}
