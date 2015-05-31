package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class SearchBSTForFirstOccurrenceRecursive {
  // @include
  public static BSTNode<Integer> findFirstEqualK(BSTNode<Integer> tree, int k) {
    if (tree == null) {
      return null; // No match.
    } else if (tree.getData() == k) {
      // Recursively search the left subtree for first node containing k.
      BSTNode<Integer> node = findFirstEqualK(tree.getLeft(), k);
      return node != null ? node : tree;
    }
    // Search the left or right tree based on tree.getData() and k.
    return findFirstEqualK(tree.getData() < k ? tree.getRight() : tree.getLeft(),
                           k);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 6
    // 1 4 6
    BSTNode<Integer> root = new BSTNode<>(3);
    root.setLeft(new BSTNode<>(2));
    root.getLeft().setLeft(new BSTNode<>(1));
    root.setRight(new BSTNode<>(6));
    root.getRight().setLeft(new BSTNode<>(4));
    root.getRight().setRight(new BSTNode<>(6));
    assert(findFirstEqualK(root, 7) == null);
    assert(findFirstEqualK(root, 6).getData().equals(6) &&
           findFirstEqualK(root, 6).getRight().getData().equals(6));
  }
}
