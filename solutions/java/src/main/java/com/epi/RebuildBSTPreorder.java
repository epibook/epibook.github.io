package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class RebuildBSTPreorder {
  // @include
  // Given a preorder traversal of a BST, return its root.
  public static <T extends Comparable<T>> BinaryTree<T> rebuildBSTFromPreorder(
      List<T> preorder) {
    return rebuildBSTFromPreorderHelper(preorder, 0, preorder.size());
  }

  // Build a BST based on preorder[s : e - 1], return its root.
  private static <T extends Comparable<T>> BinaryTree<T>
  rebuildBSTFromPreorderHelper(List<T> preorder, int s, int e) {
    if (s < e) {
      int x = s + 1;
      while (x < e && preorder.get(x).compareTo(preorder.get(s)) < 0) {
        ++x;
      }
      return new BinaryTree<>(preorder.get(s), rebuildBSTFromPreorderHelper(
          preorder, s + 1, x), rebuildBSTFromPreorderHelper(preorder, x, e));
    }
    return null;
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
