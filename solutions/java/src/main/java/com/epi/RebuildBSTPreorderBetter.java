package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.ArrayList;
import java.util.List;

public class RebuildBSTPreorderBetter {
  // @include
  private static Integer idx;

  public static BSTNode<Integer> rebuildBSTFromPreorder(
      List<Integer> preorderSequence) {
    idx = 0;
    return rebuildBSFromPreorderHelper(preorderSequence,
        Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BSTNode<Integer> rebuildBSFromPreorderHelper(
      List<Integer> preorderSequence, Integer min, Integer max) {
    if (idx == preorderSequence.size()) {
      return null;
    }

    Integer curr = preorderSequence.get(idx);
    if (curr < min || curr > max) {
      return null;
    }
    ++idx;
    return new BSTNode<>(
        curr, rebuildBSFromPreorderHelper(preorderSequence, min, curr),
        rebuildBSFromPreorderHelper(preorderSequence, curr, max));
  }
  // @exclude

  private static void checkAns(BSTNode<Integer> n, Integer pre) {
    if (n != null) {
      checkAns(n.getLeft(), pre);
      assert (pre <= n.getData());
      System.out.println(n.getData());
      checkAns(n.getRight(), n.getData());
    }
  }

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    // should output 1, 2, 3, 4, 5, 6
    // preorder [3, 2, 1, 5, 4, 6]
    List<Integer> preorder = new ArrayList<>();
    preorder.add(3);
    preorder.add(2);
    preorder.add(1);
    preorder.add(5);
    preorder.add(4);
    preorder.add(6);
    BSTNode<Integer> tree = rebuildBSTFromPreorder(preorder);
    checkAns(tree, Integer.MIN_VALUE);
  }
}
