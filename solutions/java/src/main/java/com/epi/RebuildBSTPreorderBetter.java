package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;
import com.epi.utils.Ref;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class RebuildBSTPreorderBetter {
  // @include
  public static <T extends Comparable<T>> BinaryTree<T> rebuildBSTFromPreorder(
      List<T> preorder) {
    Ref<Integer> idx = new Ref<Integer>(0);
    return rebuildBSFromPreorderHelper(preorder, idx,
        Collections.min(preorder), Collections.max(preorder));
  }

  private static <T extends Comparable<T>> BinaryTree<T> rebuildBSFromPreorderHelper(
      List<T> preorder, Ref<Integer> idx, T min, T max) {
    if (idx.value == preorder.size()) {
      return null;
    }

    T curr = preorder.get(idx.value);
    if (curr.compareTo(min) < 0 || curr.compareTo(max) > 0) {
      return null;
    }

    idx.value = idx.value + 1;
    return new BinaryTree<T>(curr, rebuildBSFromPreorderHelper(preorder, idx,
        min, curr), rebuildBSFromPreorderHelper(preorder, idx, curr, max));
  }

  // @exclude

  private static <T extends Comparable<T>> void checkAns(BinaryTree<T> n, T pre) {
    if (n != null) {
      checkAns(n.getLeft(), pre);
      assert (pre.compareTo(n.getData()) <= 0);
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
    ArrayList<Integer> preorder = new ArrayList<Integer>();
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
