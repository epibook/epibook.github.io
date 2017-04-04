package com.epi;

/*
@slug
path-sum-binary-tree

@summary
You are given a binary tree where each node is labeled with an integer.
The path weight of a node in such a tree is the sum of the integers
on the unique path from the root to that node.
For the example shown in the figure,
the path weight of E is 591.

@problem
Write a program
which takes as input an integer and a binary tree with
integer node weights,
and checks if there exists a leaf whose path weight equals the given integer.

*/

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class PathSumBinaryTree {
  // @include
  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int targetSum) {
    return hasPathSumHelper(tree, 0, targetSum);
  }

  private static boolean hasPathSumHelper(BinaryTreeNode<Integer> node,
                                          int partialPathSum, int targetSum) {
    if (node == null) {
      return false;
    }
    partialPathSum += node.data;
    if (node.left == null && node.right == null) { // Leaf.
      return partialPathSum == targetSum;
    }
    // Non-leaf.
    return hasPathSumHelper(node.left, partialPathSum, targetSum)
        || hasPathSumHelper(node.right, partialPathSum, targetSum);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    assert(hasPathSum(tree, 3));
    tree.left = new BinaryTreeNode<>(2);
    assert(hasPathSum(tree, 5));
    tree.left.left = new BinaryTreeNode<>(1);
    assert(hasPathSum(tree, 6));
    tree.right = new BinaryTreeNode<>(5);
    assert(hasPathSum(tree, 8));
    assert(!hasPathSum(tree, 7));
    tree.right.left = new BinaryTreeNode<>(4);
    assert(hasPathSum(tree, 12));
    assert(!hasPathSum(tree, 1));
    assert(!hasPathSum(tree, 3));
    assert(!hasPathSum(tree, 5));
    tree.right.right = new BinaryTreeNode<>(6);
    assert(hasPathSum(tree, 6));
    assert(!hasPathSum(tree, 7));
    assert(hasPathSum(tree, 14));
    assert(!hasPathSum(tree, -1));
    assert(!hasPathSum(tree, Integer.MAX_VALUE));
    assert(!hasPathSum(tree, Integer.MIN_VALUE));
  }
}
