package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;
import com.epi.utils.Pair;

public class KBalancedBinaryTree {
  // @include
  public static BinaryTree<Integer> findKUnBalancedNode(
      BinaryTree<Integer> tree, int k) {
    return findKUnBalancedNodeHelper(tree, k).getFirst();
  }

  private static Pair<BinaryTree<Integer>, Integer> findKUnBalancedNodeHelper(
      BinaryTree<Integer> tree, int k) {
    if (tree == null) {
      return new Pair<>(null, 0);  // Base case.
    }

    // Early return if left subtree is not k-balanced.
    Pair<BinaryTree<Integer>, Integer> leftResult =
        findKUnBalancedNodeHelper(tree.getLeft(), k);
    if (leftResult.getFirst() != null) {
      return leftResult;
    }
    // Early return if right subtree is not k-balanced.
    Pair<BinaryTree<Integer>, Integer> rightResult =
        findKUnBalancedNodeHelper(tree.getRight(), k);
    if (rightResult.getFirst() != null) {
      return rightResult;
    }

    int nodeNum = leftResult.getSecond() + rightResult.getSecond() + 1;
    if (Math.abs(leftResult.getSecond() - rightResult.getSecond()) > k) {
      return new Pair<>(tree, nodeNum);
    }
    return new Pair<>(null, nodeNum);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
    int k = 0;
    BinaryTree<Integer> ans = findKUnBalancedNode(root, k);
    assert (ans.getData().equals(2));
    System.out.println(ans.getData());
  }
}
