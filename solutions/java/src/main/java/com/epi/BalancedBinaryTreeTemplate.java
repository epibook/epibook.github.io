package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BalancedBinaryTreeTemplate {
  // @include
  private static <T> int getHeight(BinaryTree<T> n) {
    if (n == null) {
      return -1;
    }

    int lHeight = getHeight(n.getLeft());
    if (lHeight == -2) {
      return -2; // left subtree is not balanced.
    }

    int rHeight = getHeight(n.getRight());
    if (rHeight == -2) {
      return -2; // right subtree is not balanced.
    }

    if (Math.abs(lHeight - rHeight) > 1) {
      return -2; // current node n is not balanced.
    }
    return Math.max(lHeight, rHeight) + 1;
  }

  public static <T> boolean isBalancedBinaryTree(BinaryTree<T> n) {
    return getHeight(n) != -2;
  }

  // @exclude

  public static void main(String[] args) {
    // balanced binary tree test
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>();
    root.setLeft(new BinaryTree<Integer>());
    root.getLeft().setLeft(new BinaryTree<Integer>());
    root.setRight(new BinaryTree<Integer>());
    root.getRight().setLeft(new BinaryTree<Integer>());
    root.getRight().setRight(new BinaryTree<Integer>());
    assert(isBalancedBinaryTree(root));
    System.out.println(isBalancedBinaryTree(root));
    root = new BinaryTree<Integer>();
    root.setLeft(new BinaryTree<Integer>());
    root.getLeft().setLeft(new BinaryTree<Integer>());
    assert(!isBalancedBinaryTree(root));
    System.out.println(isBalancedBinaryTree(root));
  }
}
