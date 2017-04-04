package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExteriorBinaryTree {
  // @include
  public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(
      BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> exterior = new LinkedList<>();
    if (tree != null) {
      exterior.add(tree);
      exterior.addAll(leftBoundaryAndLeaves(tree.left, true));
      exterior.addAll(rightBoundaryAndLeaves(tree.right, true));
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
      result.addAll(leftBoundaryAndLeaves(subtreeRoot.left, isBoundary));
      result.addAll(leftBoundaryAndLeaves(
          subtreeRoot.right, isBoundary && subtreeRoot.left == null));
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
          subtreeRoot.left, isBoundary && subtreeRoot.right == null));
      result.addAll(rightBoundaryAndLeaves(subtreeRoot.right, isBoundary));
      if (isBoundary || isLeaf(subtreeRoot)) {
        result.add(subtreeRoot);
      }
    }
    return result;
  }

  private static boolean isLeaf(BinaryTreeNode<Integer> node) {
    return node.left == null && node.right == null;
  }
  // @exclude

  private static List<Integer> createOutputList(
      List<BinaryTreeNode<Integer>> L) {
    List<Integer> result = new ArrayList<Integer>();
    for (BinaryTreeNode<Integer> l : L) {
      result.add(l.data);
    }
    return result;
  }

  public static void main(String[] args) {
    //       3
    //    2      5
    //  1   0   4 6
    //    -1 -2
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3, null, null);
    List<BinaryTreeNode<Integer>> L = exteriorBinaryTree(tree);
    List<Integer> result = createOutputList(L);
    List<Integer> goldenResult = Arrays.asList(3);
    assert(goldenResult.equals(result));

    tree.left = new BinaryTreeNode<>(2, null, null);
    L = exteriorBinaryTree(tree);
    result = createOutputList(L);
    goldenResult = Arrays.asList(3, 2);
    assert(goldenResult.equals(result));

    tree.left.right = new BinaryTreeNode<>(0, null, null);
    tree.left.right.left = new BinaryTreeNode<>(-1, null, null);
    tree.left.right.right = new BinaryTreeNode<>(-2, null, null);
    tree.left.left = new BinaryTreeNode<>(1, null, null);
    tree.right = new BinaryTreeNode<>(5, null, null);
    tree.right.left = new BinaryTreeNode<>(4, null, null);
    tree.right.right = new BinaryTreeNode<>(6, null, null);
    L = exteriorBinaryTree(tree);
    result = createOutputList(L);
    goldenResult = Arrays.asList(3, 2, 1, -1, -2, 4, 6, 5);
    assert(goldenResult.equals(result));
  }
}
