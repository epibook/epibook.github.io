package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchBSTForFirstOccurrenceIterative {
  // @include
  public static <T extends Comparable<T>> BinaryTree<T> findFirstEqualK(
      BinaryTree<T> r, T k) {
    BinaryTree<T> first = null;
    BinaryTree<T> curr = r;
    while (curr != null) {
      if (curr.getData().compareTo(k) < 0) {
        curr = curr.getRight();
      } else if (curr.getData().compareTo(k) > 0) {
        curr = curr.getLeft();
      } else { // curr.getData().compareTo(k) == 0
        // Search for the leftmost in the left subtree.
        first = curr;
        curr = curr.getLeft();
      }
    }
    return first;
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setLeft(new BinaryTree<Integer>(1));
    root.setRight(new BinaryTree<Integer>(5));
    root.getRight().setLeft(new BinaryTree<Integer>(4));
    root.getRight().setRight(new BinaryTree<Integer>(6));
    assert (findFirstEqualK(root, 7) == null);
    assert (findFirstEqualK(root, 6).getData().equals(6));
  }
}
