package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class SearchBSTFirstLargerK {
  // @include
  public static BSTNode<Integer> findFirstGreaterThanK(BSTNode<Integer> tree,
                                                       Integer k) {
    BSTNode<Integer> subtree = tree, firstSoFar = null;
    while (subtree != null) {
      if (subtree.data > k) {
        firstSoFar = subtree;
        subtree = subtree.left;
      } else { // Root and all keys in left-subtree are <= k, so skip them.
        subtree = subtree.right;
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
    assert(findFirstGreaterThanK(tree, 1) == tree);
    assert(findFirstGreaterThanK(tree, 7) == null);
    tree.left = new BSTNode<>(2);
    tree.left.left = new BSTNode<>(1);
    tree.right = new BSTNode<>(5);
    tree.right.left = new BSTNode<>(4);
    tree.right.right = new BSTNode<>(7);
    assert(findFirstGreaterThanK(tree, 1) == tree.left);
    assert(findFirstGreaterThanK(tree, 5) == tree.right.right);
    assert(findFirstGreaterThanK(tree, 6) == tree.right.right);
    assert(findFirstGreaterThanK(tree, 7) == null);
  }
}
