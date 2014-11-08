package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class SymmetricBinaryTree {
  // @include
  public static boolean isSymmetric(BinaryTree<Integer> tree) {
    return tree == null || checkSymmetric(tree.getLeft(), tree.getRight());
  }

  private static boolean
  checkSymmetric(BinaryTree<Integer> subtree0, BinaryTree<Integer> subtree1) {
    if (subtree0 == null && subtree1 == null) {
      return true;
    } else if (subtree0 != null && subtree1 != null) {
      return subtree0.getData() == subtree1.getData()
             && checkSymmetric(subtree0.getLeft(), subtree1.getRight())
             && checkSymmetric(subtree0.getRight(), subtree1.getLeft());
    }
    // One subtree is empty, and the other is not.
    return false;
  }
  // @exclude

  public static void main(String[] args) {
    // non symmetric tree test
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> nonSymmRoot = new BinaryTree<>();
    nonSymmRoot.setLeft(new BinaryTree<Integer>());
    nonSymmRoot.getLeft().setLeft(new BinaryTree<Integer>());
    nonSymmRoot.setRight(new BinaryTree<Integer>());
    nonSymmRoot.getRight().setLeft(new BinaryTree<Integer>());
    nonSymmRoot.getRight().setRight(new BinaryTree<Integer>());
    assert (!isSymmetric(nonSymmRoot));
    System.out.println(isSymmetric(nonSymmRoot));
    // symmetric tree test
    BinaryTree<Integer> symmRoot = new BinaryTree<>();
    symmRoot.setLeft(new BinaryTree<Integer>());
    symmRoot.setRight(new BinaryTree<Integer>());
    assert (isSymmetric(symmRoot));
    System.out.println(isSymmetric(symmRoot));
  }
}
