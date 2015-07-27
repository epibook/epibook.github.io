package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class BalancedBinaryTree {
  // @include
  private static class BalanceStatusWithHeight {
    public boolean balanced;
    public int height;

    public BalanceStatusWithHeight(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }
  }

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return CheckBalanced(tree).balanced;
  }

  private static BalanceStatusWithHeight CheckBalanced(
      BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return new BalanceStatusWithHeight(true, -1); // Base case.
    }

    BalanceStatusWithHeight leftResult = CheckBalanced(tree.getLeft());
    if (leftResult.balanced == false) {
      return leftResult; // Left subtree is not balanced.
    }
    BalanceStatusWithHeight rightResult = CheckBalanced(tree.getRight());
    if (rightResult.balanced == false) {
      return rightResult; // Right subtree is not balanced.
    }

    boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
    int height = Math.max(leftResult.height, rightResult.height) + 1;
    return new BalanceStatusWithHeight(isBalanced, height);
  }
  // @exclude

  public static void main(String[] args) {
    // balanced binary tree test
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>();
    tree.setLeft(new BinaryTreeNode<Integer>());
    tree.getLeft().setLeft(new BinaryTreeNode<Integer>());
    tree.setRight(new BinaryTreeNode<Integer>());
    tree.getRight().setLeft(new BinaryTreeNode<Integer>());
    tree.getRight().setRight(new BinaryTreeNode<Integer>());
    assert(isBalanced(tree));
    System.out.println(isBalanced(tree));
    tree = new BinaryTreeNode<>();
    tree.setLeft(new BinaryTreeNode<Integer>());
    tree.getLeft().setLeft(new BinaryTreeNode<Integer>());
    assert(!isBalanced(tree));
    System.out.println(isBalanced(tree));
  }
}
