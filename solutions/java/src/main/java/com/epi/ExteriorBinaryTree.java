package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ExteriorBinaryTree {
  private static List<Integer> result = new ArrayList<>();

  // @include
  public static void exteriorBinaryTree(BinaryTree<Integer> T) {
    if (T != null) {
      System.out.print(T.getData() + " ");
      // @exclude
      result.add(T.getData());
      // @include
      leftBoundaryBinaryTree(T.getLeft(), true);
      rightBoundaryBinaryTree(T.getRight(), true);
    }
  }

  private static void leftBoundaryBinaryTree(
      BinaryTree<Integer> T, boolean isBoundary) {
    if (T != null) {
      if (isBoundary || (T.getLeft() == null && T.getRight() == null)) {
        System.out.print(T.getData() + " ");
        // @exclude
        result.add(T.getData());
        // @include
      }
      leftBoundaryBinaryTree(T.getLeft(), isBoundary);
      leftBoundaryBinaryTree(T.getRight(), isBoundary && T.getLeft() == null);
    }
  }

  private static void rightBoundaryBinaryTree(
      BinaryTree<Integer> T, boolean isBoundary) {
    if (T != null) {
      rightBoundaryBinaryTree(T.getLeft(), isBoundary && T.getRight() == null);
      rightBoundaryBinaryTree(T.getRight(), isBoundary);
      if (isBoundary || (T.getLeft() == null && T.getRight() == null)) {
        System.out.print(T.getData() + " ");
        // @exclude
        result.add(T.getData());
        // @include
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 0 4 6
    // -1 -2
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.setLeft(new BinaryTree<>(2, null, null));
    root.getLeft().setRight(new BinaryTree<>(0, null, null));
    root.getLeft().getRight().setLeft(new BinaryTree<>(-1, null, null));
    root.getLeft().getRight().setRight(new BinaryTree<>(-2, null, null));
    root.getLeft().setLeft(new BinaryTree<>(1, null, null));
    root.setRight(new BinaryTree<>(5, null, null));
    root.getRight().setLeft(new BinaryTree<>(4, null, null));
    root.getRight().setRight(new BinaryTree<>(6, null, null));
    // should output 3 2 1 -1 -2 4 6 5
    exteriorBinaryTree(root);
    List<Integer> golden_res = new ArrayList<Integer>() {{
      add(3);
      add(2);
      add(1);
      add(-1);
      add(-2);
      add(4);
      add(6);
      add(5);
    }};
    assert (golden_res.equals(result));
  }
}
