package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class SearchMinFirstBST {
  // @include
  public static boolean searchMinFirstBST(BinaryTreeNode<Integer> minFirstBST,
                                          Integer k) {
    // First handle the base cases.
    if (minFirstBST == null || Integer.compare(minFirstBST.data, k) > 0) {
      return false;
    } else if (Integer.compare(minFirstBST.data, k) == 0) {
      return true;
    }

    // Recursively search just the right subtree if the smallest key in the
    // right subtree is greater than or equal to k.
    if (minFirstBST.right != null
        && Integer.compare(k, minFirstBST.right.data) >= 0) {
      return searchMinFirstBST(minFirstBST.right, k);
    }
    return searchMinFirstBST(minFirstBST.left, k);
  }
  // @exclude

  public static void main(String[] args) {
    // A min-first BST
    // 1
    // 2 4
    // 3 5 7
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(1);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(3);
    tree.right = new BinaryTreeNode<>(4);
    tree.right.left = new BinaryTreeNode<>(5);
    tree.right.right = new BinaryTreeNode<>(7);
    assert(searchMinFirstBST(tree, 1));
    assert(searchMinFirstBST(tree, 3));
    assert(searchMinFirstBST(tree, 5));
    assert(!searchMinFirstBST(tree, 6));
  }
}
