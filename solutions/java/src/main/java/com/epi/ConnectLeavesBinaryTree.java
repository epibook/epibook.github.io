package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.LinkedList;
import java.util.List;

public class ConnectLeavesBinaryTree {
  // @include
  public static List<BinaryTree<Integer>> connectLeaves(
      BinaryTree<Integer> tree) {
    List<BinaryTree<Integer>> leaves = new LinkedList<>();
    if (tree != null) {
      if (tree.getLeft() == null && tree.getRight() == null) {
        leaves.add(tree);
      } else {
        // First do the left subtree, and then do the right subtree.
        leaves.addAll(connectLeaves(tree.getLeft()));
        leaves.addAll(connectLeaves(tree.getRight()));
      }
    }
    return leaves;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.setLeft(new BinaryTree<>(2, null, null));
    root.getLeft().setLeft(new BinaryTree<>(1, null, null));
    root.setRight(new BinaryTree<>(5, null, null));
    root.getRight().setLeft(new BinaryTree<>(4, null, null));
    root.getRight().setRight(new BinaryTree<>(6, null, null));
    // should output 1, 4, 6
    List<BinaryTree<Integer>> L = connectLeaves(root);
    for (BinaryTree<Integer> l : L) {
      System.out.println(l.getData());
    }
    assert (L.size() == 3);
    assert (L.get(0).getData().equals(1) && L.get(1).getData().equals(4) && L.get(2).getData().equals(6));
  }
}
