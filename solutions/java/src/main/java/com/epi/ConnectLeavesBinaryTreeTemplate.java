package com.epi;

import java.util.ArrayList;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ConnectLeavesBinaryTreeTemplate {
  // @include
  private static <T> void connectLeavesHelper(BinaryTree<T> n,
      ArrayList<BinaryTree<T>> L) {
    if (n != null) {
      if (n.getLeft() == null && n.getRight() == null) {
        L.add(n);
      } else {
        connectLeavesHelper(n.getLeft(), L);
        connectLeavesHelper(n.getRight(), L);
      }
    }
  }

  public static <T> ArrayList<BinaryTree<T>> connectLeaves(BinaryTree<T> n) {
    ArrayList<BinaryTree<T>> L = new ArrayList<BinaryTree<T>>();
    connectLeavesHelper(n, L);
    return L;
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3, null, null);
    root.setLeft(new BinaryTree<Integer>(2, null, null));
    root.getLeft().setLeft(new BinaryTree<Integer>(1, null, null));
    root.setRight(new BinaryTree<Integer>(5, null, null));
    root.getRight().setLeft(new BinaryTree<Integer>(4, null, null));
    root.getRight().setRight(new BinaryTree<Integer>(6, null, null));
    // should output 1, 4, 6
    ArrayList<BinaryTree<Integer>> L = connectLeaves(root);
    ArrayList<Integer> output = new ArrayList<Integer>();
    for (BinaryTree<Integer> l : L) {
      output.add(l.getData());
      System.out.println(l.getData());
    }
    assert (output.size() == 3);
    assert (output.get(0).equals(1) && output.get(1).equals(4) && output.get(2)
        .equals(6));
  }
}
