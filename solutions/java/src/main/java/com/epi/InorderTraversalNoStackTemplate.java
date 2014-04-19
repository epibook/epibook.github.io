package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class InorderTraversalNoStackTemplate {
  // @include
  public static <T> void inOrderTraversal(BinaryTree<T> n) {
    while (n != null) {
      if (n.getLeft() != null) {
        // Find the predecessor of n.
        BinaryTree<T> pre = n.getLeft();
        while (pre.getRight() != null && pre.getRight() != n) {
          pre = pre.getRight();
        }

        // Process the successor link.
        if (pre.getRight() != null) { // pre.getRight() == n
          // Revert the successor link if predecessor's successor is n.
          pre.setRight(null);
          System.out.println(n.getData());
          n = n.getRight();
        } else { // if predecessor's successor is not n.
          pre.setRight(n);
          n = n.getLeft();
        }
      } else {
        System.out.println(n.getData());
        n = n.getRight();
      }
    }
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setLeft(new BinaryTree<Integer>(1));
    root.setRight(new BinaryTree<Integer>(5));
    root.getRight().setLeft(new BinaryTree<Integer>(4));
    root.getRight().setRight(new BinaryTree<Integer>(6));
    // should output 1 2 3 4 5 6
    inOrderTraversal(root);
  }
}
