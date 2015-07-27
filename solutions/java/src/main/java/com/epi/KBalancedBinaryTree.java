package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class KBalancedBinaryTree {
  // @include

  private static class TreeWithSize {
    public BinaryTreeNode<Integer> root;
    public Integer size;

    public TreeWithSize(BinaryTreeNode<Integer> root, Integer size) {
      this.root = root;
      this.size = size;
    }
  }

  public static BinaryTreeNode<Integer> findKUnBalancedNode(
      BinaryTreeNode<Integer> tree, int k) {
    return findKUnBalancedNodeHelper(tree, k).root;
  }

  // If there is any k-unbalanced node in tree, the root field is
  // a k-unbalanced node; otherwise, it is null.  If the root is
  // not null, the size field is the number of nodes in tree.
  private static TreeWithSize findKUnBalancedNodeHelper(
      BinaryTreeNode<Integer> tree, int k) {
    if (tree == null) {
      return new TreeWithSize(null, 0); // Base case.
    }

    // Early return if left subtree is not k-balanced.
    TreeWithSize leftResult = findKUnBalancedNodeHelper(tree.getLeft(), k);
    if (leftResult.root != null) {
      return new TreeWithSize(leftResult.root, 0);
    }
    // Early return if right subtree is not k-balanced.
    TreeWithSize rightResult = findKUnBalancedNodeHelper(tree.getRight(), k);
    if (rightResult.root != null) {
      return new TreeWithSize(rightResult.root, 0);
    }

    int nodeNum = leftResult.size + rightResult.size + 1;
    if (Math.abs(leftResult.size - rightResult.size) > k) {
      // tree is not k-balanced but its children are k-balanced.
      return new TreeWithSize(tree, nodeNum);
    }
    return new TreeWithSize(null, nodeNum);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3);
    root.setLeft(new BinaryTreeNode<>(2));
    root.getLeft().setLeft(new BinaryTreeNode<>(1));
    root.setRight(new BinaryTreeNode<>(5));
    root.getRight().setLeft(new BinaryTreeNode<>(4));
    root.getRight().setRight(new BinaryTreeNode<>(6));
    int k = 0;
    BinaryTreeNode<Integer> ans = findKUnBalancedNode(root, k);
    assert(ans.getData().equals(2));
    System.out.println(ans.getData());
  }
}
