package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class IsBinaryTreeABSTConstSpace {
  // @include
  public static boolean isBST(BinaryTreeNode<Integer> tree) {
    BinaryTreeNode<Integer> n = tree;
    // Stores the value of previous visited node.
    Integer last = Integer.MIN_VALUE;
    boolean result = true;

    while (n != null) {
      if (n.getLeft() != null) {
        // Finds the predecessor of n.
        BinaryTreeNode<Integer> pre = n.getLeft();
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
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.setLeft(new BinaryTreeNode<>(2));
    tree.getLeft().setLeft(new BinaryTreeNode<>(1));
    tree.setRight(new BinaryTreeNode<>(5));
    tree.getRight().setLeft(new BinaryTreeNode<>(4));
    tree.getRight().setRight(new BinaryTreeNode<>(6));
    // should output true.
    assert isBST(tree);
    System.out.println(isBST(tree));
    // 10
    // 2 5
    // 1 4 6
    tree.setData(10);
    // should output false.
    assert(!isBST(tree));
    System.out.println(isBST(tree));
    // should output true.
    assert isBST(null);
    System.out.println(isBST(null));
  }
}
