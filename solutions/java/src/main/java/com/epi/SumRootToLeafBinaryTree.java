package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class SumRootToLeafBinaryTree {
  // @include
  public static int sumRootToLeaf(BinaryTree<Integer> root) {
    return preOrderTraversal(root, 0);
  }

  private static int preOrderTraversal(BinaryTree<Integer> root, int num) {
    if (root == null) {
      return 0;
    }

    num = (num * 2) + root.getData();
    if (root.getLeft() == null && root.getRight() == null) { // Leaf.
      return num;
    }
    // Non-leaf.
    return preOrderTraversal(root.getLeft(), num)
           + preOrderTraversal(root.getRight(), num);
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
