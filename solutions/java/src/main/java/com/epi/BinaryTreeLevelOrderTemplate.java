package com.epi;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinaryTreeLevelOrderTemplate {
  // @include
  public static <T> void printBinaryTreeLevelOrder(BinarySearchTree<T> n) {
    // Prevent empty tree
    if (n == null) {
      return;
    }

    LinkedList<BinarySearchTree<T>> q = new LinkedList<BinarySearchTree<T>>();
    q.push(n);
    int count = q.size();
    while (!q.isEmpty()) {
      BinarySearchTree<T> front = q.pollLast();
      System.out.print(front.getData() + " ");
      if (front.getLeft() != null) {
        q.push(front.getLeft());
      }
      if (front.getRight() != null) {
        q.push(front.getRight());
      }
      if (--count == 0) {
        System.out.println();
        count = q.size();
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
    // should output 3
    // 2 5
    // 1 4 6
    printBinaryTreeLevelOrder(root);
  }
}
