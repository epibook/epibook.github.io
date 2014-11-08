package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class BalancedBinaryTree {
  // @include
  private static class Status {
    public boolean balanced;
    public int height;

    public Status(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }
  }

  public static boolean isBalancedBinaryTree(BinaryTree<Integer> tree) {
    return CheckBalanced(tree).balanced;
  }

  private static Status CheckBalanced(BinaryTree<Integer> tree) {
    if (tree == null) {
      return new Status(true, -1); // Base case.
    }

    Status leftResult = CheckBalanced(tree.getLeft());
    if (leftResult.balanced == false) {
      return leftResult; // Left subtree is not balanced.
    }
    Status rightResult = CheckBalanced(tree.getRight());
    if (rightResult.balanced == false) {
      return rightResult; // Right subtree is not balanced.
    }

    boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
    int height = Math.max(leftResult.height, rightResult.height) + 1;
    return new Status(isBalanced, height);
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
