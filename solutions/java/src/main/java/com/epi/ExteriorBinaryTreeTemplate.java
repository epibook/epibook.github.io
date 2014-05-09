package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ExteriorBinaryTreeTemplate {
  // @include
  private static <T> void leftBoundaryBTree(BinaryTree<T> n, boolean isBoundary) {
    if (n != null) {
      if (isBoundary || (n.getLeft() == null && n.getRight() == null)) {
        System.out.print(n.getData() + " ");
      }
      leftBoundaryBTree(n.getLeft(), isBoundary);
      leftBoundaryBTree(n.getRight(), isBoundary && n.getLeft() == null);
    }
  }

  private static <T> void rightBoundaryBTree(
      BinaryTree<T> n, boolean isBoundary) {
    if (n != null) {
      rightBoundaryBTree(n.getLeft(), isBoundary && n.getRight() == null);
      rightBoundaryBTree(n.getRight(), isBoundary);
      if (isBoundary || (n.getLeft() == null && n.getRight() == null)) {
        System.out.print(n.getData() + " ");
      }
    }
  }

  public static <T> void exteriorBinaryTree(BinaryTree<T> root) {
    if (root != null) {
      System.out.print(root.getData() + " ");
      leftBoundaryBTree(root.getLeft(), true);
      rightBoundaryBTree(root.getRight(), true);
    }
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 0 4 6
    // -1 -2
    BinaryTree<Integer> root = new BinaryTree<Integer>(3, null, null);
    root.setLeft(new BinaryTree<Integer>(2, null, null));
    root.getLeft().setRight(new BinaryTree<Integer>(0, null, null));
    root.getLeft().getRight().setLeft(new BinaryTree<Integer>(-1, null, null));
    root.getLeft().getRight().setRight(new BinaryTree<Integer>(-2, null, null));
    root.getLeft().setLeft(new BinaryTree<Integer>(1, null, null));
    root.setRight(new BinaryTree<Integer>(5, null, null));
    root.getRight().setLeft(new BinaryTree<Integer>(4, null, null));
    root.getRight().setRight(new BinaryTree<Integer>(6, null, null));
    // should output 3 2 1 -1 -2 4 6 5
    exteriorBinaryTree(root);
  }
}
