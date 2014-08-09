package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BalancedBinaryTree {
  // @include
  public static boolean isBalancedBinaryTree(BinaryTree<Integer> T) {
    return getHeight(T) != -2;
  }

  private static int getHeight(BinaryTree<Integer> T) {
    if (T == null) {
      return -1; // Base case.
    }

    int lHeight = getHeight(T.getLeft());
    if (lHeight == -2) {
      return -2; // Left subtree is not balanced.
    }
    int rHeight = getHeight(T.getRight());
    if (rHeight == -2) {
      return -2; // Right subtree is not balanced.
    }

    if (Math.abs(lHeight - rHeight) > 1) {
      return -2; // Current node T is not balanced.
    }
    return Math.max(lHeight, rHeight) + 1; // Returns the height.
  }
  // @exclude

  public static void main(String[] args) {
    // balanced binary tree test
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>();
    root.setLeft(new BinaryTree<Integer>());
    root.getLeft().setLeft(new BinaryTree<Integer>());
    root.setRight(new BinaryTree<Integer>());
    root.getRight().setLeft(new BinaryTree<Integer>());
    root.getRight().setRight(new BinaryTree<Integer>());
    assert (isBalancedBinaryTree(root));
    System.out.println(isBalancedBinaryTree(root));
    root = new BinaryTree<>();
    root.setLeft(new BinaryTree<Integer>());
    root.getLeft().setLeft(new BinaryTree<Integer>());
    assert (!isBalancedBinaryTree(root));
    System.out.println(isBalancedBinaryTree(root));
  }
}
