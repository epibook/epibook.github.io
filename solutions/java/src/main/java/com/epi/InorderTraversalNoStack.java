package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversalNoStack {
  private static List<Integer> result = new ArrayList<>();

  // @include
  public static void inOrderTraversal(BinaryTreeNode<Integer> tree) {
    while (tree != null) {
      if (tree.getLeft() != null) {
        // Finds the predecessor of tree.
        BinaryTreeNode<Integer> pre = tree.getLeft();
        while (pre.getRight() != null && pre.getRight() != tree) {
          pre = pre.getRight();
        }

        // Processes the successor link.
        if (pre.getRight() != null) { // pre.getRight() == tree
          // Reverts the successor link if predecessor's successor is tree.
          pre.setRight(null);
          System.out.println(tree.getData());
          // @exclude
          result.add(tree.getData());
          // @include
          tree = tree.getRight();
        } else { // If predecessor's successor is not tree.
          pre.setRight(tree);
          tree = tree.getLeft();
        }
      } else {
        System.out.println(tree.getData());
        // @exclude
        result.add(tree.getData());
        // @include
        tree = tree.getRight();
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.setLeft(new BinaryTreeNode<>(2));
    tree.getLeft().setLeft(new BinaryTreeNode<>(1));
    tree.setRight(new BinaryTreeNode<>(5));
    tree.getRight().setLeft(new BinaryTreeNode<>(4));
    tree.getRight().setRight(new BinaryTreeNode<>(6));
    // should output 1 2 3 4 5 6
    inOrderTraversal(tree);
    List<Integer> golden_res = new ArrayList<Integer>() {
      {
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
      }
    };
    assert(golden_res.equals(result));
  }
}
