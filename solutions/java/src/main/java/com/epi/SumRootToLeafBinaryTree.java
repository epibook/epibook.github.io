package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class SumRootToLeafBinaryTree {
  // @include
  public static int sumRootToLeaf(BinaryTree<Integer> tree) {
    return sumRootToLeafHelper(tree, 0);
  }

  private static int sumRootToLeafHelper(BinaryTree<Integer> node, 
                                         int partialPathSum) {
    if (node == null) {
      return 0;
    }

    partialPathSum = partialPathSum * 2 + node.getData();
    if (node.getLeft() == null && node.getRight() == null) { // Leaf.
      return partialPathSum;
    }
    // Non-leaf.
    return sumRootToLeafHelper(node.getLeft(), partialPathSum)
           + sumRootToLeafHelper(node.getRight(), partialPathSum);
  }
  // @exclude

  public static void main(String[] args) {
    // 1
    // 1 0
    // 0 1 0
    BinaryTree<Integer> root = new BinaryTree<>(1);
    root.setLeft(new BinaryTree<>(1));
    root.getLeft().setLeft(new BinaryTree<>(0));
    root.setRight(new BinaryTree<>(0));
    root.getRight().setLeft(new BinaryTree<>(1));
    root.getRight().setRight(new BinaryTree<>(0));
    int result = sumRootToLeaf(root);
    assert (result == 15);
  }
}
