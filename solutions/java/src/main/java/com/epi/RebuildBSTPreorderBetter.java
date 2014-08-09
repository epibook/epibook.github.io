package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class RebuildBSTPreorderBetter {
  // @include
  private static Integer idx;

  public static BinaryTree<Integer> rebuildBSTFromPreorder(
      List<Integer> preorder) {
    idx = 0;
    return rebuildBSFromPreorderHelper(preorder,
        Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BinaryTree<Integer> rebuildBSFromPreorderHelper(
      List<Integer> preorder, Integer min, Integer max) {
    if (idx == preorder.size()) {
      return null;
    }

    Integer curr = preorder.get(idx);
    if (curr < min || curr > max) {
      return null;
    }

    ++idx;
    return new BinaryTree<>(
        curr,
        rebuildBSFromPreorderHelper(preorder, min, curr),
        rebuildBSFromPreorderHelper(preorder, curr, max));
  }
  // @exclude

  private static void checkAns(BinaryTree<Integer> n, Integer pre) {
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
    BinaryTree<Integer> root = rebuildBSTFromPreorder(preorder);
    checkAns(root, Integer.MIN_VALUE);
  }
}
