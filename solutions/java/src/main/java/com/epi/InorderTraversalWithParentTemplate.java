package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class InorderTraversalWithParentTemplate {
  // @include
  public static <T> void inOrderTraversal(BinaryTree<T> r) {
    // Empty tree.
    if (r == null) {
      return;
    }

    BinaryTree<T> prev = null, curr = r, next;
    while (curr != null) {
      if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
        if (curr.getLeft() != null) {
          next = curr.getLeft();
        } else {
          System.out.println(curr.getData());
          next = (curr.getRight() != null ? curr.getRight() : curr.getParent());
        }
      } else if (curr.getLeft() == prev) {
        System.out.println(curr.getData());
        next = (curr.getRight() != null ? curr.getRight() : curr.getParent());
      } else {
        next = curr.getParent();
      }

      prev = curr;
      curr = next;
    }
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3, null, null);
    root.setLeft(new BinaryTree<Integer>(2, null, null));
    root.getLeft().setParent(root);
    root.getLeft().setLeft(new BinaryTree<Integer>(1, null, null));
    root.getLeft().getLeft().setParent(root.getLeft());
    root.setRight(new BinaryTree<Integer>(5, null, null));
    root.getRight().setParent(root);
    root.getRight().setLeft(new BinaryTree<Integer>(4, null, null));
    root.getRight().getLeft().setParent(root.getRight());
    root.getRight().setRight(new BinaryTree<Integer>(6, null, null));
    root.getRight().getRight().setParent(root.getRight());

    // Should output 1 2 3 4 5 6.
    inOrderTraversal(root);
  }
}
