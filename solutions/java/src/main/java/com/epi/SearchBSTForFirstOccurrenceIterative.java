package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class SearchBSTForFirstOccurrenceIterative {
  // @include
  public static BinaryTree<Integer> findFirstEqualK(
      BinaryTree<Integer> T, Integer k) {
    BinaryTree<Integer> firstSoFar = null;
    BinaryTree<Integer> curr = T;
    while (curr != null) {
      if (curr.getData().compareTo(k) < 0) {
        curr = curr.getRight();
      } else if (curr.getData().compareTo(k) > 0) {
        curr = curr.getLeft();
      } else { // curr.getData().compareTo(k) == 0
        // Record this node, and search for the first node in the left subtree.
        firstSoFar = curr;
        curr = curr.getLeft();
      }
    }
    return firstSoFar;
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
    assert (findFirstEqualK(root, 7) == null);
    assert (findFirstEqualK(root, 6).getData().equals(6));
  }
}
