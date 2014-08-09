package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ConnectLeavesBinaryTree {
  // @include
  public static List<BinaryTree<Integer>> connectLeaves(BinaryTree<Integer> n) {
    List<BinaryTree<Integer>> L = new ArrayList<>();
    connectLeavesHelper(n, L);
    return L;
  }

  private static void connectLeavesHelper(BinaryTree<Integer> n,
                                          List<BinaryTree<Integer>> L) {
    if (n != null) {
      if (n.getLeft() == null && n.getRight() == null) {
        L.add(n);
      } else {
        connectLeavesHelper(n.getLeft(), L);
        connectLeavesHelper(n.getRight(), L);
      }
    }
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
    List<Integer> output = new ArrayList<>();
    for (BinaryTree<Integer> l : L) {
      output.add(l.getData());
      System.out.println(l.getData());
    }
    assert (output.size() == 3);
    assert (output.get(0).equals(1) && output.get(1).equals(4) && output.get(2)
        .equals(6));
  }
}
