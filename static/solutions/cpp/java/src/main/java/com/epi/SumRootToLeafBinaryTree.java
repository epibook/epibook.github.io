package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class SumRootToLeafBinaryTree {
  // @include
  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    return sumRootToLeafHelper(tree, 0);
  }

  private static int sumRootToLeafHelper(BinaryTreeNode<Integer> tree,
                                         int partialPathSum) {
    if (tree == null) {
      return 0;
    }

    partialPathSum = partialPathSum * 2 + tree.data;
    if (tree.left == null && tree.right == null) { // Leaf.
      return partialPathSum;
    }
    // Non-leaf.
    return sumRootToLeafHelper(tree.left, partialPathSum)
        + sumRootToLeafHelper(tree.right, partialPathSum);
  }
  // @exclude

  public static void main(String[] args) {
    // 1
    // 1 0
    // 0 1 0
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1);
    int result = sumRootToLeaf(root);
    assert(result == 1);
    root.left = new BinaryTreeNode<>(1);
    result = sumRootToLeaf(root);
    assert(result == 3);
    root.left.left = new BinaryTreeNode<>(0);
    result = sumRootToLeaf(root);
    assert(result == 6);
    root.right = new BinaryTreeNode<>(0);
    result = sumRootToLeaf(root);
    assert(result == 8);
    root.right.left = new BinaryTreeNode<>(1);
    result = sumRootToLeaf(root);
    assert(result == 11);
    root.right.right = new BinaryTreeNode<>(0);
    result = sumRootToLeaf(root);
    assert(result == 15);
  }
}
