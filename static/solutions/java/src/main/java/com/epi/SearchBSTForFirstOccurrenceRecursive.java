package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class SearchBSTForFirstOccurrenceRecursive {
  // @include
  public static BSTNode<Integer> findFirstEqualK(BSTNode<Integer> tree, int k) {
    if (tree == null) {
      return null; // No match.
    } else if (tree.data == k) {
      // Recursively search the left subtree for first node containing k.
      BSTNode<Integer> node = findFirstEqualK(tree.left, k);
      return node != null ? node : tree;
    }
    // Search the left or right subtree based on relative values of
    // tree.data and k.
    return findFirstEqualK(tree.data < k ? tree.right : tree.left, k);
  }
  // @exclude

  public static void main(String[] args) {
    //     3
    //   2   6
    // 1    4 6
    BSTNode<Integer> root = new BSTNode<>(3);
    root.left = new BSTNode<>(2);
    root.left.left = new BSTNode<>(1);
    root.right = new BSTNode<>(6);
    root.right.left = new BSTNode<>(4);
    root.right.right = new BSTNode<>(6);
    assert(findFirstEqualK(root, 7) == null);
    assert(findFirstEqualK(root, 6).data.equals(6)
           && findFirstEqualK(root, 6).right.data.equals(6));

    //     3
    //   3   5
    // 2    5 6
    root = new BSTNode<>(3);
    root.left = new BSTNode<>(3);
    root.left.left = new BSTNode<>(2);
    root.right = new BSTNode<>(5);
    root.right.left = new BSTNode<>(5);
    root.right.right = new BSTNode<>(6);
    assert(findFirstEqualK(root, 7) == null);
    assert(findFirstEqualK(root, 3) == root.left);
    assert(findFirstEqualK(root, 5).equals(root.right.left));
    assert(findFirstEqualK(root, 6).data.equals(6));
  }
}
