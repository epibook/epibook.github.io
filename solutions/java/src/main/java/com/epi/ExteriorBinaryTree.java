package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExteriorBinaryTree {
  // @include
  public static List<BinaryTree<Integer>> exteriorBinaryTree(
      BinaryTree<Integer> tree) {
    List<BinaryTree<Integer>> exterior = new LinkedList<>();
    if (tree != null) {
      exterior.add(tree);
      exterior.addAll(leftBoundaryAndLeaves(tree.getLeft(), true));
      exterior.addAll(rightBoundaryAndLeaves(tree.getRight(), true));
    }
    return exterior;
  }

  // Computes the nodes from the root to the leftmost leaf followed by all the
  // leaves in subtreeRoot.
  private static List<BinaryTree<Integer>> leftBoundaryAndLeaves(
      BinaryTree<Integer> subtreeRoot, boolean isBoundary) {
    List<BinaryTree<Integer>> result = new LinkedList<>();
    if (subtreeRoot != null) {
      if (isBoundary || isLeaf(subtreeRoot)) {
        result.add(subtreeRoot);
      }
      result.addAll(leftBoundaryAndLeaves(subtreeRoot.getLeft(), isBoundary));
      result.addAll(
          leftBoundaryAndLeaves(subtreeRoot.getRight(),
                                isBoundary && subtreeRoot.getLeft() == null));
    }
    return result;
  }

  // Computes the leaves in left-to-right order followed by the rightmost leaf
  // to the root path in subtreeRoot.
  private static List<BinaryTree<Integer>> rightBoundaryAndLeaves(
      BinaryTree<Integer> subtreeRoot, boolean isBoundary) {
    List<BinaryTree<Integer>> result = new LinkedList<>();
    if (subtreeRoot != null) {
      result.addAll(
          rightBoundaryAndLeaves(subtreeRoot.getLeft(),
                                 isBoundary && subtreeRoot.getRight() == null));
      result.addAll(rightBoundaryAndLeaves(subtreeRoot.getRight(), isBoundary));
      if (isBoundary || isLeaf(subtreeRoot)) {
        result.add(subtreeRoot);
      }
    }
    return result;
  }

  private static boolean isLeaf(BinaryTree<Integer> node) {
    return node.getLeft() == null && node.getRight() == null;
  }
  // @exclude

  public static void main(String[] args) {
    //       3
    //    2      5
    //  1   0   4 6
    //    -1 -2
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
    List<BinaryTree<Integer>> L = exteriorBinaryTree(root);
    List<Integer> result = new ArrayList<Integer>();
    for (BinaryTree<Integer> l : L) {
      result.add(l.getData());
      System.out.println(l.getData());
    }
    List<Integer> goldenResult = new ArrayList<Integer>() {{
      add(3);
      add(2);
      add(1);
      add(-1);
      add(-2);
      add(4);
      add(6);
      add(5);
    }};
    assert (goldenResult.equals(result));
  }
}
