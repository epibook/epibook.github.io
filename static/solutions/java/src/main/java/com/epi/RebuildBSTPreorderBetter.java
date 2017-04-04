package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.Arrays;
import java.util.List;

public class RebuildBSTPreorderBetter {
  // @include
  // Global variable, tracks current subtree.
  private static Integer rootIdx;

  public static BSTNode<Integer> rebuildBSTFromPreorder(
      List<Integer> preorderSequence) {
    rootIdx = 0;
    return rebuildBSFromPreorderOnValueRange(
        preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  // Builds a BST on the subtree rooted at rootIdx from preorderSequence on keys
  // in (lowerBound, upperBound).
  private static BSTNode<Integer> rebuildBSFromPreorderOnValueRange(
      List<Integer> preorderSequence, Integer lowerBound, Integer upperBound) {
    if (rootIdx == preorderSequence.size()) {
      return null;
    }

    Integer root = preorderSequence.get(rootIdx);
    if (root < lowerBound || root > upperBound) {
      return null;
    }
    ++rootIdx;
    // Note that rebuildBSFromPreorderOnValueRange updates rootIdx. So the order
    // of following two calls are critical.
    BSTNode<Integer> leftSubtree
        = rebuildBSFromPreorderOnValueRange(preorderSequence, lowerBound, root);
    BSTNode<Integer> rightSubtree
        = rebuildBSFromPreorderOnValueRange(preorderSequence, root, upperBound);
    return new BSTNode<>(root, leftSubtree, rightSubtree);
  }
  // @exclude

  private static void checkAns(BSTNode<Integer> n, Integer pre) {
    if (n != null) {
      checkAns(n.left, pre);
      assert(pre <= n.data);
      System.out.println(n.data);
      checkAns(n.right, n.data);
    }
  }

  public static void main(String[] args) {
    //      3
    //    2   5
    //  1    4  6
    // should output 1, 2, 3, 4, 5, 6
    // preorder [3, 2, 1, 5, 4, 6]
    List<Integer> preorder = Arrays.asList(3, 2, 1, 5, 4, 6);
    BSTNode<Integer> tree = rebuildBSTFromPreorder(preorder);
    checkAns(tree, Integer.MIN_VALUE);
    assert(3 == tree.data);
    assert(2 == tree.left.data);
    assert(1 == tree.left.left.data);
    assert(5 == tree.right.data);
    assert(4 == tree.right.left.data);
    assert(6 == tree.right.right.data);
  }
}
