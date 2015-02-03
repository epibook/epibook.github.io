package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class IsBinaryTreeABST {
  // @include
  public static boolean isBinaryTreeBST(BinaryTree<Integer> root) {
    return areKeysInRange(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean areKeysInRange(BinaryTree<Integer> root,
                                        Integer lower, Integer upper) {
    if (root == null) {
      return true;
    } else if (root.getData().compareTo(lower) < 0
               || root.getData().compareTo(upper) > 0) {
      return false;
    }

    return areKeysInRange(root.getLeft(), lower, root.getData())
           && areKeysInRange(root.getRight(), root.getData(), upper);
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
    // should output true.
    assert isBinaryTreeBST(root);
    System.out.println(isBinaryTreeBST(root));
    // 10
    // 2 5
    // 1 4 6
    root.setData(10);
    // should output false.
    assert !isBinaryTreeBST(root);
    System.out.println(isBinaryTreeBST(root));
    // should output true.
    assert isBinaryTreeBST(null);
    System.out.println(isBinaryTreeBST(null));
  }
}
