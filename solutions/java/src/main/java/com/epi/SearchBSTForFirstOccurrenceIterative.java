package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class SearchBSTForFirstOccurrenceIterative {
  // @include
  public static BSTNode<Integer> findFirstEqualK(BSTNode<Integer> tree,
                                                 Integer k) {
    BSTNode<Integer> firstSoFar = null, curr = tree;
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
    BSTNode<Integer> tree = new BSTNode<>(3);
    tree.setLeft(new BSTNode<>(2));
    tree.getLeft().setLeft(new BSTNode<>(1));
    tree.setRight(new BSTNode<>(5));
    tree.getRight().setLeft(new BSTNode<>(4));
    tree.getRight().setRight(new BSTNode<>(6));
    assert(findFirstEqualK(tree, 7) == null);
    assert(findFirstEqualK(tree, 6).getData().equals(6));
  }
}
