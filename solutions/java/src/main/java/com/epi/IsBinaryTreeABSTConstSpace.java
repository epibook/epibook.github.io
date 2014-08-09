package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class IsBinaryTreeABSTConstSpace {
  // @include
  public static boolean isBST(BinaryTree<Integer> root) {
    BinaryTree<Integer> n = root;
    // Stores the value of previous visited node.
    Integer last = Integer.MIN_VALUE;
    boolean result = true;

    while (n != null) {
      if (n.getLeft() != null) {
        // Finds the predecessor of n.
        BinaryTree<Integer> pre = n.getLeft();
        while (pre.getRight() != null && pre.getRight() != n) {
          pre = pre.getRight();
        }

        // Processes the successor link.
        if (pre.getRight() != null) { // pre.getRight() == n.
          // Reverts the successor link if predecessor's successor is n.
          pre.setRight(null);
          if (last.compareTo(n.getData()) > 0) {
            result = false;
          }
          last = n.getData();
          n = n.getRight();
        } else { // If predecessor's successor is not n.
          pre.setRight(n);
          n = n.getLeft();
        }
      } else {
        if (last.compareTo(n.getData()) > 0) {
          result = false;
        }
        last = n.getData();
        n = n.getRight();
      }
    }
    return result;
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
    // should output true.
    assert isBST(root);
    System.out.println(isBST(root));
    // 10
    // 2 5
    // 1 4 6
    root.setData(10);
    // should output false.
    assert (!isBST(root));
    System.out.println(isBST(root));
    // should output true.
    assert isBST(null);
    System.out.println(isBST(null));
  }
}
