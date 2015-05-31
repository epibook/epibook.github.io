package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;
import com.epi.utils.Pair;

public class KBalancedBinaryTree {
  // @include
  public static BinaryTreeNode<Integer> findKUnBalancedNode(
      BinaryTreeNode<Integer> tree, int k) {
    return findKUnBalancedNodeHelper(tree, k).getFirst();
  }

  // If there is any k-unbalanced node in tree, the first value of the return
  // value is a k-unbalanced node; otherwise, null.  If the first is not null,
  // the second value of the return value is the number of nodes in tree.
  private static Pair<BinaryTreeNode<Integer>, Integer> findKUnBalancedNodeHelper(
      BinaryTreeNode<Integer> tree, int k) {
    if (tree == null) {
      return new Pair<>(null, 0); // Base case.
    }

    // Early return if left subtree is not k-balanced.
    Pair<BinaryTreeNode<Integer>, Integer> leftResult =
        findKUnBalancedNodeHelper(tree.getLeft(), k);
    if (leftResult.getFirst() != null) {
      return new Pair<>(leftResult.getFirst(), 0);
    }
    // Early return if right subtree is not k-balanced.
    Pair<BinaryTreeNode<Integer>, Integer> rightResult =
        findKUnBalancedNodeHelper(tree.getRight(), k);
    if (rightResult.getFirst() != null) {
      return new Pair<>(rightResult.getFirst(), 0);
    }

    int nodeNum = leftResult.getSecond() + rightResult.getSecond() + 1;
    if (Math.abs(leftResult.getSecond() - rightResult.getSecond()) > k) {
      // tree is not k-balanced but its children are k-balanced.
      return new Pair<>(tree, nodeNum);
    }
    return new Pair<>(null, nodeNum);
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
