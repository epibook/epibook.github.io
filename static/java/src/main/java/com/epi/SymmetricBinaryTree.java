package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class SymmetricBinaryTree {
  // @include
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    return tree == null || checkSymmetric(tree.left, tree.right);
  }

  private static boolean checkSymmetric(BinaryTreeNode<Integer> subtree0,
                                        BinaryTreeNode<Integer> subtree1) {
    if (subtree0 == null && subtree1 == null) {
      return true;
    } else if (subtree0 != null && subtree1 != null) {
      return subtree0.data == subtree1.data
          && checkSymmetric(subtree0.left, subtree1.right)
          && checkSymmetric(subtree0.right, subtree1.left);
    }
    // One subtree is empty, and the other is not.
    return false;
  }
  // @exclude

  private static void simpleTest() {
    BinaryTreeNode<Integer> symmTree = new BinaryTreeNode<>();
    assert(isSymmetric(symmTree));
    symmTree.left = new BinaryTreeNode<Integer>();
    assert(!isSymmetric(symmTree));
    symmTree.right = new BinaryTreeNode<Integer>();
    assert(isSymmetric(symmTree));
    symmTree.right.right = new BinaryTreeNode<Integer>();
    assert(!isSymmetric(symmTree));
  }

  public static void main(String[] args) {
    simpleTest();
    // Non symmetric tree test
    //    x
    //  x   x
    // x   x x
    BinaryTreeNode<Integer> nonSymmTree = new BinaryTreeNode<>();
    nonSymmTree.left = new BinaryTreeNode<Integer>();
    nonSymmTree.left.left = new BinaryTreeNode<Integer>();
    nonSymmTree.right = new BinaryTreeNode<Integer>();
    nonSymmTree.right.left = new BinaryTreeNode<Integer>();
    nonSymmTree.right.right = new BinaryTreeNode<Integer>();
    assert(!isSymmetric(nonSymmTree));
    System.out.println(isSymmetric(nonSymmTree));
    // Symmetric tree test
    BinaryTreeNode<Integer> symmTree = new BinaryTreeNode<>();
    symmTree.left = new BinaryTreeNode<Integer>();
    symmTree.right = new BinaryTreeNode<Integer>();
    assert(isSymmetric(symmTree));
    System.out.println(isSymmetric(symmTree));
  }
}
