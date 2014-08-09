package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class InorderTraversalNoStack {
  private static List<Integer> result = new ArrayList<>();

  // @include
  public static void inOrderTraversal(BinaryTree<Integer> n) {
    while (n != null) {
      if (n.getLeft() != null) {
        // Finds the predecessor of n.
        BinaryTree<Integer> pre = n.getLeft();
        while (pre.getRight() != null && pre.getRight() != n) {
          pre = pre.getRight();
        }

        // Processes the successor link.
        if (pre.getRight() != null) { // pre.getRight() == n
          // Reverts the successor link if predecessor's successor is n.
          pre.setRight(null);
          System.out.println(n.getData());
          // @exclude
          result.add(n.getData());
          // @include
          n = n.getRight();
        } else { // If predecessor's successor is not n.
          pre.setRight(n);
          n = n.getLeft();
        }
      } else {
        System.out.println(n.getData());
        // @exclude
        result.add(n.getData());
        // @include
        n = n.getRight();
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
    // should output 1 2 3 4 5 6
    inOrderTraversal(root);
    List<Integer> golden_res = new ArrayList<Integer>() {{
      add(1);
      add(2);
      add(3);
      add(4);
      add(5);
      add(6);
    }};
    assert (golden_res.equals(result));
  }
}
