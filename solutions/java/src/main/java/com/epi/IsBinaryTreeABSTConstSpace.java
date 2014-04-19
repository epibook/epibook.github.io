package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class IsBinaryTreeABSTConstSpace {
  // @include
  public static <T extends Comparable<T>> boolean isBST(BinaryTree<T> root,
      T min, T max) {
    BinaryTree<T> n = root;
    // Store the value of previous visited node.
    T last = min;
    boolean res = true;

    while (n != null) {
      if (n.getLeft() != null) {
        // Find the predecessor of n.
        BinaryTree<T> pre = n.getLeft();
        while (pre.getRight() != null && pre.getRight() != n) {
          pre = pre.getRight();
        }

        // Process the successor link.
        if (pre.getRight() != null) { // pre.getRight() == n.
          // Revert the successor link if predecessor's successor is n.
          pre.setRight(null);
          if (last.compareTo(n.getData()) > 0) {
            res = false;
          }
          last = n.getData();
          n = n.getRight();
        } else { // if predecessor's successor is not n.
          pre.setRight(n);
          n = n.getLeft();
        }
      } else {
        if (last.compareTo(n.getData()) > 0) {
          res = false;
        }
        last = n.getData();
        n = n.getRight();
      }
    }
    return res;
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setLeft(new BinaryTree<Integer>(1));
    root.setRight(new BinaryTree<Integer>(5));
    root.getRight().setLeft(new BinaryTree<Integer>(4));
    root.getRight().setRight(new BinaryTree<Integer>(6));
    // should output true.
    assert isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    // 10
    // 2 5
    // 1 4 6
    root.setData(10);
    // should output false.
    assert (!isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    // should output true.
    assert isBST(null, Integer.MIN_VALUE, Integer.MAX_VALUE);
    System.out.println(isBST(null, Integer.MIN_VALUE, Integer.MAX_VALUE));
  }
}
