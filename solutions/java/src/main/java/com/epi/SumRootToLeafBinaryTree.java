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

    partialPathSum = partialPathSum * 2 + tree.getData();
    if (tree.getLeft() == null && tree.getRight() == null) { // Leaf.
      return partialPathSum;
    }
    // Non-leaf.
    return sumRootToLeafHelper(tree.getLeft(), partialPathSum) +
        sumRootToLeafHelper(tree.getRight(), partialPathSum);
  }
  // @exclude

  public static void main(String[] args) {
    // 1
    // 1 0
    // 0 1 0
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1);
    root.setLeft(new BinaryTreeNode<>(1));
    root.getLeft().setLeft(new BinaryTreeNode<>(0));
    root.setRight(new BinaryTreeNode<>(0));
    root.getRight().setLeft(new BinaryTreeNode<>(1));
    root.getRight().setRight(new BinaryTreeNode<>(0));
    int result = sumRootToLeaf(root);
    assert(result == 15);
  }
}
