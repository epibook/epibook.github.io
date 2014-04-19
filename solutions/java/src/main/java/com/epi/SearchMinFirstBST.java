package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchMinFirstBST {
  // @include
  public static <T extends Comparable<T>> boolean searchMinFirstBST(
      BinaryTree<T> r, T k) {
    if (r == null || r.getData().compareTo(k) > 0) {
      return false;
    } else if (r.getData().compareTo(k) == 0) {
      return true;
    }

    // Search the right subtree if the smallest key in the right subtree is
    // greater than or equal to k.
    if (r.getRight() != null && k.compareTo(r.getRight().getData()) >= 0) {
      return searchMinFirstBST(r.getRight(), k);
    }
    return searchMinFirstBST(r.getLeft(), k);
  }

  // @exclude

  public static void main(String[] args) {
    // A min-first BST
    // 1
    // 2 4
    // 3 5 7
    BinaryTree<Integer> root = new BinaryTree<Integer>(1);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setLeft(new BinaryTree<Integer>(3));
    root.setRight(new BinaryTree<Integer>(4));
    root.getRight().setLeft(new BinaryTree<Integer>(5));
    root.getRight().setRight(new BinaryTree<Integer>(7));
    assert (searchMinFirstBST(root, 1));
    assert (searchMinFirstBST(root, 3));
    assert (searchMinFirstBST(root, 5));
    assert (!searchMinFirstBST(root, 6));
  }
}
