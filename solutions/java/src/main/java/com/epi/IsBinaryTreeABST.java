package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class IsBinaryTreeABST {
  // @include
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return areKeysInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean areKeysInRange(BinaryTreeNode<Integer> tree,
                                        Integer lower, Integer upper) {
    if (tree == null) {
      return true;
    } else if (Integer.compare(tree.data, lower) < 0
               || Integer.compare(tree.data, upper) > 0) {
      return false;
    }

    return areKeysInRange(tree.left, lower, tree.data)
        && areKeysInRange(tree.right, tree.data, upper);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    // should output true.
    assert isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // 10
    // 2 5
    // 1 4 6
    tree.data = 10;
    // should output false.
    assert !isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // should output true.
    assert isBinaryTreeBST(null);
    System.out.println(isBinaryTreeBST(null));
  }
}
