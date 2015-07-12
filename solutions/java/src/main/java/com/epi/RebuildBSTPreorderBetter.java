package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RebuildBSTPreorderBetter {
  // @include
  private static Integer rootIdx;

  public static BSTNode<Integer> rebuildBSTFromPreorder(
      List<Integer> preorderSequence) {
    rootIdx = 0;
    return rebuildBSFromPreorderHelper(preorderSequence, Integer.MIN_VALUE,
                                       Integer.MAX_VALUE);
  }

  // Builds a BST from preorderSequence on keys in (lowerBound : upperBound).
  private static BSTNode<Integer> rebuildBSFromPreorderHelper(
      List<Integer> preorderSequence, Integer lowerBound, Integer upperBound) {
    if (rootIdx == preorderSequence.size()) {
      return null;
    }

    Integer root = preorderSequence.get(rootIdx);
    if (root < lowerBound || root > upperBound) {
      return null;
    }
    ++rootIdx;
    return new BSTNode<>(
        root, rebuildBSFromPreorderHelper(preorderSequence, lowerBound, root),
        rebuildBSFromPreorderHelper(preorderSequence, root, upperBound));
  }
  // @exclude

  private static void checkAns(BSTNode<Integer> n, Integer pre) {
    if (n != null) {
      checkAns(n.getLeft(), pre);
      assert(pre <= n.getData());
      System.out.println(n.getData());
      checkAns(n.getRight(), n.getData());
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
  }
}
