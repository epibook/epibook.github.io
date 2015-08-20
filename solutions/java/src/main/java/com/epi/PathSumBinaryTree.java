package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class PathSumBinaryTree {
  // @include
  public static boolean hasPathSum(BinaryTreeNode<Integer> tree, int targetSum) {
    return hasPathSumHelper(tree, 0, targetSum);
  }

  private static boolean hasPathSumHelper(BinaryTreeNode<Integer> node,
                                          int partialPathSum, int targetSum) {
    if (node == null) {
      return false;
    }
    partialPathSum += node.getData();
    if (node.getLeft() == null && node.getRight() == null) { // Leaf.
      return partialPathSum == targetSum;
    }
    // Non-leaf.
    return hasPathSumHelper(node.getLeft(), partialPathSum, targetSum) ||
        hasPathSumHelper(node.getRight(), partialPathSum, targetSum);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    assert(hasPathSum(tree, 3));
    tree.setLeft(new BinaryTreeNode<>(2));
    assert(hasPathSum(tree, 5));
    tree.getLeft().setLeft(new BinaryTreeNode<>(1));
    assert(hasPathSum(tree, 6));
    tree.setRight(new BinaryTreeNode<>(5));
    assert(hasPathSum(tree, 8));
    assert(!hasPathSum(tree, 7));
    tree.getRight().setLeft(new BinaryTreeNode<>(4));
    assert(hasPathSum(tree, 12));
    assert(!hasPathSum(tree, 1));
    assert(!hasPathSum(tree, 3));
    assert(!hasPathSum(tree, 5));
    tree.getRight().setRight(new BinaryTreeNode<>(6));
    assert(hasPathSum(tree, 6));
    assert(!hasPathSum(tree, 7));
    assert(hasPathSum(tree, 14));
    assert(!hasPathSum(tree, -1));
    assert(!hasPathSum(tree, Integer.MAX_VALUE));
    assert(!hasPathSum(tree, Integer.MIN_VALUE));
  }
}
