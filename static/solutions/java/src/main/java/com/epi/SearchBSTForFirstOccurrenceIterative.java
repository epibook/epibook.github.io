package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class SearchBSTForFirstOccurrenceIterative {
  // @include
  public static BSTNode<Integer> findFirstEqualK(BSTNode<Integer> tree,
                                                 Integer k) {
    BSTNode<Integer> firstSoFar = null, curr = tree;
    while (curr != null) {
      if (curr.data < k) {
        curr = curr.right;
      } else if (curr.data > k) {
        curr = curr.left;
      } else { // curr.data is equal to k
        // Record this node, and search for the first node in the left subtree.
        firstSoFar = curr;
        curr = curr.left;
      }
    }
    return firstSoFar;
  }
  // @exclude

  public static void main(String[] args) {
    //     3
    //   2  5
    // 1   4 6
    BSTNode<Integer> tree = new BSTNode<>(3);
    tree.left = new BSTNode<>(2);
    tree.left.left = new BSTNode<>(1);
    tree.right = new BSTNode<>(5);
    tree.right.left = new BSTNode<>(4);
    tree.right.right = new BSTNode<>(6);
    assert(findFirstEqualK(tree, 7) == null);
    assert(findFirstEqualK(tree, 6).data.equals(6));

    //     3
    //   3  5
    // 2   5 6
    tree = new BSTNode<>(3);
    tree.left = new BSTNode<>(3);
    tree.left.left = new BSTNode<>(2);
    tree.right = new BSTNode<>(5);
    tree.right.left = new BSTNode<>(5);
    tree.right.right = new BSTNode<>(6);
    assert(findFirstEqualK(tree, 7) == null);
    assert(findFirstEqualK(tree, 3) == tree.left);
    assert(findFirstEqualK(tree, 5).equals(tree.right.left));
    assert(findFirstEqualK(tree, 6).data.equals(6));
  }
}
