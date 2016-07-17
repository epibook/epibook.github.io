package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.LinkedList;
import java.util.List;

public class ConnectLeavesBinaryTree {
  // @include
  public static List<BinaryTreeNode<Integer>> createListOfLeaves(
      BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> leaves = new LinkedList<>();
    addLeavesLeftToRight(tree, leaves);
    return leaves;
  }

  private static void addLeavesLeftToRight(
      BinaryTreeNode<Integer> tree, List<BinaryTreeNode<Integer>> leaves) {
    if (tree != null) {
      if (tree.left == null && tree.right == null) {
        leaves.add(tree);
      } else {
        addLeavesLeftToRight(tree.left, leaves);
        addLeavesLeftToRight(tree.right, leaves);
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3, null, null);
    tree.left = new BinaryTreeNode<>(2, null, null);
    List<BinaryTreeNode<Integer>> L = createListOfLeaves(tree);
    assert(L.size() == 1);
    assert(L.get(0).data.equals(2));

    tree.left.left = new BinaryTreeNode<>(1, null, null);
    tree.right = new BinaryTreeNode<>(5, null, null);
    tree.right.left = new BinaryTreeNode<>(4, null, null);
    tree.right.right = new BinaryTreeNode<>(6, null, null);
    // should output 1, 4, 6
    L = createListOfLeaves(tree);
    for (BinaryTreeNode<Integer> l : L) {
      System.out.println(l.data);
    }
    assert(L.size() == 3);
    assert(L.get(0).data.equals(1) && L.get(1).data.equals(4)
           && L.get(2).data.equals(6));
  }
}
