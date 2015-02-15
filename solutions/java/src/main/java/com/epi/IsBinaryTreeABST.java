package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class IsBinaryTreeABST {
  // @include
  public static boolean isBinaryTreeBST(BinaryTree<Integer> tree) {
    return areKeysInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean areKeysInRange(BinaryTree<Integer> tree,
                                        Integer lower, Integer upper) {
    if (tree == null) {
      return true;
    } else if (tree.getData().compareTo(lower) < 0
               || tree.getData().compareTo(upper) > 0) {
      return false;
    }

    return areKeysInRange(tree.getLeft(), lower, tree.getData())
           && areKeysInRange(tree.getRight(), tree.getData(), upper);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> tree = new BinaryTree<>(3);
    tree.setLeft(new BinaryTree<>(2));
    tree.getLeft().setLeft(new BinaryTree<>(1));
    tree.setRight(new BinaryTree<>(5));
    tree.getRight().setLeft(new BinaryTree<>(4));
    tree.getRight().setRight(new BinaryTree<>(6));
    // should output true.
    assert isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // 10
    // 2 5
    // 1 4 6
    tree.setData(10);
    // should output false.
    assert !isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // should output true.
    assert isBinaryTreeBST(null);
    System.out.println(isBinaryTreeBST(null));
  }
}
