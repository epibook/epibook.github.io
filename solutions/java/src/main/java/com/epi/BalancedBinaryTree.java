package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

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

  public static boolean isBalancedBinaryTree(BinaryTreeNode<Integer> tree) {
    return CheckBalanced(tree).balanced;
  }

  private static Status CheckBalanced(BinaryTreeNode<Integer> tree) {
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
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>();
    tree.setLeft(new BinaryTreeNode<Integer>());
    tree.getLeft().setLeft(new BinaryTreeNode<Integer>());
    tree.setRight(new BinaryTreeNode<Integer>());
    tree.getRight().setLeft(new BinaryTreeNode<Integer>());
    tree.getRight().setRight(new BinaryTreeNode<Integer>());
    assert(isBalancedBinaryTree(tree));
    System.out.println(isBalancedBinaryTree(tree));
    tree = new BinaryTreeNode<>();
    tree.setLeft(new BinaryTreeNode<Integer>());
    tree.getLeft().setLeft(new BinaryTreeNode<Integer>());
    assert(!isBalancedBinaryTree(tree));
    System.out.println(isBalancedBinaryTree(tree));
  }
}
