package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.Arrays;
import java.util.List;

public class RebuildBSTPreorder {
  // @include
  public static BSTNode<Integer> rebuildBSTFromPreorder(
      List<Integer> preorderSequence) {
    return rebuildBSTFromPreorderHelper(preorderSequence, 0,
                                        preorderSequence.size());
  }

  // Builds a BST from preorderSequence.subList(start, end).
  private static BSTNode<Integer> rebuildBSTFromPreorderHelper(
      List<Integer> preorderSequence, int start, int end) {
    if (start >= end) {
      return null;
    }
    int transitionPoint = start + 1;
    while (transitionPoint < end
           && Integer.compare(preorderSequence.get(transitionPoint),
                              preorderSequence.get(start))
                  < 0) {
      ++transitionPoint;
    }
    return new BSTNode<>(
        preorderSequence.get(start),
        rebuildBSTFromPreorderHelper(preorderSequence, start + 1,
                                     transitionPoint),
        rebuildBSTFromPreorderHelper(preorderSequence, transitionPoint, end));
  }
  // @exclude

  private static void checkAns(BSTNode<Integer> n, Integer pre) {
    if (n != null) {
      checkAns(n.left, pre);
      assert(Integer.compare(pre, n.data) <= 0);
      System.out.println(n.data);
      checkAns(n.right, n.data);
    }
  }

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
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
