package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExteriorBinaryTree {
  // @include
  public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(
      BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> exterior = new LinkedList<>();
    if (tree != null) {
      exterior.add(tree);
      exterior.addAll(leftBoundaryAndLeaves(tree.getLeft(), true));
      exterior.addAll(rightBoundaryAndLeaves(tree.getRight(), true));
    }
    return exterior;
  }

  // Computes the nodes from the root to the leftmost leaf followed by all the
  // leaves in subtreeRoot.
  private static List<BinaryTreeNode<Integer>> leftBoundaryAndLeaves(
      BinaryTreeNode<Integer> subtreeRoot, boolean isBoundary) {
    List<BinaryTreeNode<Integer>> result = new LinkedList<>();
    if (subtreeRoot != null) {
      if (isBoundary || isLeaf(subtreeRoot)) {
        result.add(subtreeRoot);
      }
      result.addAll(leftBoundaryAndLeaves(subtreeRoot.getLeft(), isBoundary));
      result.addAll(leftBoundaryAndLeaves(
          subtreeRoot.getRight(), isBoundary && subtreeRoot.getLeft() == null));
    }
    return result;
  }

  // Computes the leaves in left-to-right order followed by the rightmost leaf
  // to the root path in subtreeRoot.
  private static List<BinaryTreeNode<Integer>> rightBoundaryAndLeaves(
      BinaryTreeNode<Integer> subtreeRoot, boolean isBoundary) {
    List<BinaryTreeNode<Integer>> result = new LinkedList<>();
    if (subtreeRoot != null) {
      result.addAll(rightBoundaryAndLeaves(
          subtreeRoot.getLeft(), isBoundary && subtreeRoot.getRight() == null));
      result.addAll(rightBoundaryAndLeaves(subtreeRoot.getRight(), isBoundary));
      if (isBoundary || isLeaf(subtreeRoot)) {
        result.add(subtreeRoot);
      }
    }
    return result;
  }

  private static boolean isLeaf(BinaryTreeNode<Integer> node) {
    return node.getLeft() == null && node.getRight() == null;
  }
  // @exclude

  public static void main(String[] args) {
    //       3
    //    2      5
    //  1   0   4 6
    //    -1 -2
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3, null, null);
    tree.setLeft(new BinaryTreeNode<>(2, null, null));
    tree.getLeft().setRight(new BinaryTreeNode<>(0, null, null));
    tree.getLeft().getRight().setLeft(new BinaryTreeNode<>(-1, null, null));
    tree.getLeft().getRight().setRight(new BinaryTreeNode<>(-2, null, null));
    tree.getLeft().setLeft(new BinaryTreeNode<>(1, null, null));
    tree.setRight(new BinaryTreeNode<>(5, null, null));
    tree.getRight().setLeft(new BinaryTreeNode<>(4, null, null));
    tree.getRight().setRight(new BinaryTreeNode<>(6, null, null));
    // should output 3 2 1 -1 -2 4 6 5
    List<BinaryTreeNode<Integer>> L = exteriorBinaryTree(tree);
    List<Integer> result = new ArrayList<Integer>();
    for (BinaryTreeNode<Integer> l : L) {
      result.add(l.getData());
      System.out.println(l.getData());
    }
    List<Integer> goldenResult = new ArrayList<Integer>() {
      {
        add(3);
        add(2);
        add(1);
        add(-1);
        add(-2);
        add(4);
        add(6);
        add(5);
      }
    };
    assert(goldenResult.equals(result));
  }
}
