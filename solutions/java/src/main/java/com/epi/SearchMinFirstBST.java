package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchMinFirstBST {
  // @include
  public static boolean searchMinFirstBST(
      BinaryTree<Integer> T, Integer k) {
    if (T == null || T.getData().compareTo(k) > 0) {
      return false;
    } else if (T.getData().compareTo(k) == 0) {
      return true;
    }

    // Search the right subtree if the smallest key in the right subtree is
    // greater than or equal to k.
    if (T.getRight() != null && k.compareTo(T.getRight().getData()) >= 0) {
      return searchMinFirstBST(T.getRight(), k);
    }
    return searchMinFirstBST(T.getLeft(), k);
  }
  // @exclude

  public static void main(String[] args) {
    // A min-first BST
    // 1
    // 2 4
    // 3 5 7
    BinaryTree<Integer> root = new BinaryTree<>(1);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(3));
    root.setRight(new BinaryTree<>(4));
    root.getRight().setLeft(new BinaryTree<>(5));
    root.getRight().setRight(new BinaryTree<>(7));
    assert (searchMinFirstBST(root, 1));
    assert (searchMinFirstBST(root, 3));
    assert (searchMinFirstBST(root, 5));
    assert (!searchMinFirstBST(root, 6));
  }
}
