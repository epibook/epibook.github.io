package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class SearchMinFirstBST {
  // @include
  public static boolean searchMinFirstBST(BSTNode<Integer> minFirstBST,
                                          Integer k) {
    // First handle the base cases.
    if (minFirstBST == null || minFirstBST.getData().compareTo(k) > 0) {
      return false;
    } else if (minFirstBST.getData().compareTo(k) == 0) {
      return true;
    }

    // Recursively search just the right subtree if the smallest key in the
    // right subtree is greater than or equal to k.
    if (minFirstBST.getRight() != null &&
        k.compareTo(minFirstBST.getRight().getData()) >= 0) {
      return searchMinFirstBST(minFirstBST.getRight(), k);
    }
    return searchMinFirstBST(minFirstBST.getLeft(), k);
  }
  // @exclude

  public static void main(String[] args) {
    // A min-first BST
    // 1
    // 2 4
    // 3 5 7
    BSTNode<Integer> tree = new BSTNode<>(1);
    tree.setLeft(new BSTNode<>(2));
    tree.getLeft().setLeft(new BSTNode<>(3));
    tree.setRight(new BSTNode<>(4));
    tree.getRight().setLeft(new BSTNode<>(5));
    tree.getRight().setRight(new BSTNode<>(7));
    assert(searchMinFirstBST(tree, 1));
    assert(searchMinFirstBST(tree, 3));
    assert(searchMinFirstBST(tree, 5));
    assert(!searchMinFirstBST(tree, 6));
  }
}
