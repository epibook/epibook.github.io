package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.LinkedList;
import java.util.List;

public class ConnectLeavesBinaryTree {
  // @include
  public static List<BinaryTreeNode<Integer>> createListOfLeaves(
      BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> leaves = new LinkedList<>();
    if (tree != null) {
      if (tree.getLeft() == null && tree.getRight() == null) {
        leaves.add(tree);
      } else {
        // First do the left subtree, and then do the right subtree.
        leaves.addAll(createListOfLeaves(tree.getLeft()));
        leaves.addAll(createListOfLeaves(tree.getRight()));
      }
    }
    return leaves;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3, null, null);
    tree.setLeft(new BinaryTreeNode<>(2, null, null));
    List<BinaryTreeNode<Integer>> L = createListOfLeaves(tree);
    assert(L.size() == 1);
    assert(L.get(0).getData().equals(2));

    tree.getLeft().setLeft(new BinaryTreeNode<>(1, null, null));
    tree.setRight(new BinaryTreeNode<>(5, null, null));
    tree.getRight().setLeft(new BinaryTreeNode<>(4, null, null));
    tree.getRight().setRight(new BinaryTreeNode<>(6, null, null));
    // should output 1, 4, 6
    L = createListOfLeaves(tree);
    for (BinaryTreeNode<Integer> l : L) {
      System.out.println(l.getData());
    }
    assert(L.size() == 3);
    assert(L.get(0).getData().equals(1) && L.get(1).getData().equals(4) &&
           L.get(2).getData().equals(6));
  }
}
