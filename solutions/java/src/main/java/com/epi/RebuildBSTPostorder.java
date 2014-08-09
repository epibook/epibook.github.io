package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class RebuildBSTPostorder {
  // @include
  // Given a postorder traversal of a BST, return its root.
  public static <T extends Comparable<T>> BinaryTree<T> rebuildBSTFromPostorder(
      List<T> postorder) {
    return rebuildBSTPostorderHelper(postorder, 0, postorder.size());
  }

  // Build a BST based on postorder[s : e - 1], return its root.
  private static <T extends Comparable<T>> BinaryTree<T> rebuildBSTPostorderHelper(
      List<T> postorder, int s, int e) {
    if (s < e) {
      int x = s;
      while (x < e && postorder.get(x).compareTo(postorder.get(e - 1)) < 0) {
        ++x;
      }
      return new BinaryTree<>(postorder.get(e - 1), rebuildBSTPostorderHelper(
          postorder, s, x), rebuildBSTPostorderHelper(postorder, x, e - 1));
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
    // 1
    // 2
    // 3
    // 4
    // 5
    // 6
    // should output 1, 2, 3, 4, 5, 6
    // postorder [6, 5, 4, 3, 2, 1]
    List<Integer> postorder = new ArrayList<>();
    postorder.add(6);
    postorder.add(5);
    postorder.add(4);
    postorder.add(3);
    postorder.add(2);
    postorder.add(1);
    BinaryTree<Integer> root = rebuildBSTFromPostorder(postorder);
    checkAns(root, Integer.MIN_VALUE);
  }
}
