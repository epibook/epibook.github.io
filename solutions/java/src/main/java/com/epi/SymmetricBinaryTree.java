package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class SymmetricBinaryTree {
  // @include
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    return tree == null || checkSymmetric(tree.getLeft(), tree.getRight());
  }

  private static boolean checkSymmetric(BinaryTreeNode<Integer> subtree0,
                                        BinaryTreeNode<Integer> subtree1) {
    if (subtree0 == null && subtree1 == null) {
      return true;
    } else if (subtree0 != null && subtree1 != null) {
      return subtree0.getData() == subtree1.getData() &&
          checkSymmetric(subtree0.getLeft(), subtree1.getRight()) &&
          checkSymmetric(subtree0.getRight(), subtree1.getLeft());
    }
    // One subtree is empty, and the other is not.
    return false;
  }
  // @exclude

  private static void simpleTest() {
    BinaryTreeNode<Integer> symmTree = new BinaryTreeNode<>();
    assert(isSymmetric(symmTree));
    symmTree.setLeft(new BinaryTreeNode<Integer>());
    assert(!isSymmetric(symmTree));
    symmTree.setRight(new BinaryTreeNode<Integer>());
    assert(isSymmetric(symmTree));
    symmTree.getRight().setRight(new BinaryTreeNode<Integer>());
    assert(!isSymmetric(symmTree));
  }

  public static void main(String[] args) {
    simpleTest();
    // Non symmetric tree test
    //    x
    //  x   x
    // x   x x
    BinaryTreeNode<Integer> nonSymmTree = new BinaryTreeNode<>();
    nonSymmTree.setLeft(new BinaryTreeNode<Integer>());
    nonSymmTree.getLeft().setLeft(new BinaryTreeNode<Integer>());
    nonSymmTree.setRight(new BinaryTreeNode<Integer>());
    nonSymmTree.getRight().setLeft(new BinaryTreeNode<Integer>());
    nonSymmTree.getRight().setRight(new BinaryTreeNode<Integer>());
    assert(!isSymmetric(nonSymmTree));
    System.out.println(isSymmetric(nonSymmTree));
    // Symmetric tree test
    BinaryTreeNode<Integer> symmTree = new BinaryTreeNode<>();
    symmTree.setLeft(new BinaryTreeNode<Integer>());
    symmTree.setRight(new BinaryTreeNode<Integer>());
    assert(isSymmetric(symmTree));
    System.out.println(isSymmetric(symmTree));
  }
}
