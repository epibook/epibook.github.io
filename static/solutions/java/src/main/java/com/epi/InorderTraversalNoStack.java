package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class InorderTraversalNoStack {
  private static List<Integer> result = new ArrayList<>();

  // @include
  public static void inorderTraversal(BinaryTreeNode<Integer> tree) {
    while (tree != null) {
      if (tree.left != null) {
        // Finds the predecessor of tree.
        BinaryTreeNode<Integer> pre = tree.left;
        while (pre.right != null && pre.right != tree) {
          pre = pre.right;
        }

        // Processes the successor link.
        if (pre.right != null) { // pre.right == tree
          // Reverts the successor link if predecessor's successor is tree.
          pre.right = null;
          System.out.println(tree.data);
          // @exclude
          result.add(tree.data);
          // @include
          tree = tree.right;
        } else { // If predecessor's successor is not tree.
          pre.right = tree;
          tree = tree.left;
        }
      } else {
        System.out.println(tree.data);
        // @exclude
        result.add(tree.data);
        // @include
        tree = tree.right;
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    // should output 1 2 3 4 5 6
    inorderTraversal(tree);
    List<Integer> goldenRes = Arrays.asList(1, 2, 3, 4, 5, 6);
    assert(goldenRes.equals(result));
  }
}
