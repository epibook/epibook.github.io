package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchBSTForFirstOccurrenceRecursive {
  // @include
  public static BinaryTree<Integer> findFirstEqualK(
      BinaryTree<Integer> T, Integer k) {
    if (T == null) {
      return null; // No match.
    } else if (T.getData().compareTo(k) == 0) {
      // Recursively searches the left subtree for first one == k.
      BinaryTree<Integer> n = findFirstEqualK(T.getLeft(), k);
      return n != null ? n : T;
    }
    // Searches left or right tree according to T.getData() and k.
    return findFirstEqualK(
        T.getData().compareTo(k) < 0 ? T.getRight() : T.getLeft(), k);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 6
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(6));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
    assert (findFirstEqualK(root, 7) == null);
    assert (findFirstEqualK(root, 6).getData().equals(6) && findFirstEqualK(
        root, 6).getRight().getData().equals(6));
  }
}
