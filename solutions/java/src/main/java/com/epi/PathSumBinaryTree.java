package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class PathSumBinaryTree {
  // @include
  public static boolean hasPathSum(BinaryTree<Integer> root, int targetSum) {
    return hasPathSumHelper(root, 0, targetSum);
  }

  private static boolean hasPathSumHelper(BinaryTree<Integer> node,
                                          int partialPathSum, int targetSum) {
    if (node == null) {
      return false;
    }
    partialPathSum += node.getData();
    if (node.getLeft() == null && node.getRight() == null) { // Leaf.
      return partialPathSum == targetSum;
    }
    // Non-leaf.
    return hasPathSumHelper(node.getLeft(), partialPathSum, targetSum)
           || hasPathSumHelper(node.getRight(), partialPathSum, targetSum);
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
    assert (hasPathSum(root, 6));
    assert (!hasPathSum(root, 7));
    assert (!hasPathSum(root, 100));
  }
}
