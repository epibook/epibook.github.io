package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class SearchBSTFirstLargerK {
  // @include
  public static BSTNode<Integer> findFirstGreaterThanK(BSTNode<Integer> tree,
                                                       Integer k) {
    BSTNode<Integer> subtree = tree, firstSoFar = null;

    while (subtree != null) {
      if (subtree.getData() > k) {
        firstSoFar = subtree;
        subtree = subtree.getLeft();
      } else { // subtree.getData() <= k
        subtree = subtree.getRight();
      }
    }
    return firstSoFar;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 7
    BSTNode<Integer> tree = new BSTNode<>(3);
    tree.setLeft(new BSTNode<>(2));
    tree.getLeft().setLeft(new BSTNode<>(1));
    tree.setRight(new BSTNode<>(5));
    tree.getRight().setLeft(new BSTNode<>(4));
    tree.getRight().setRight(new BSTNode<>(7));
    assert(findFirstGreaterThanK(tree, 1) == tree.getLeft());
    assert(findFirstGreaterThanK(tree, 5) == tree.getRight().getRight());
    assert(findFirstGreaterThanK(tree, 6) == tree.getRight().getRight());
    assert(findFirstGreaterThanK(tree, 7) == null);
  }
}
