package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class SearchMinFirstBST {
  // @include
  public static boolean searchMinFirstBST(BinaryTreeNode<Integer> minFirstBST,
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
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(1);
    tree.setLeft(new BinaryTreeNode<>(2));
    tree.getLeft().setLeft(new BinaryTreeNode<>(3));
    tree.setRight(new BinaryTreeNode<>(4));
    tree.getRight().setLeft(new BinaryTreeNode<>(5));
    tree.getRight().setRight(new BinaryTreeNode<>(7));
    assert(searchMinFirstBST(tree, 1));
    assert(searchMinFirstBST(tree, 3));
    assert(searchMinFirstBST(tree, 5));
    assert(!searchMinFirstBST(tree, 6));
  }
}
