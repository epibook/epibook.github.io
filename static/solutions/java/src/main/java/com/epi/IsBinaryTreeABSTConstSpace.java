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
      if (n.left != null) {
        // Finds the predecessor of n.
        BinaryTreeNode<Integer> pre = n.left;
        while (pre.right != null && pre.right != n) {
          pre = pre.right;
        }

        // Processes the successor link.
        if (pre.right != null) { // pre.right == n.
          // Reverts the successor link if predecessor's successor is n.
          pre.right = null;
          if (Integer.compare(last, n.data) > 0) {
            result = false;
          }
          last = n.data;
          n = n.right;
        } else { // If predecessor's successor is not n.
          pre.right = n;
          n = n.left;
        }
      } else {
        if (Integer.compare(last, n.data) > 0) {
          result = false;
        }
        last = n.data;
        n = n.right;
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
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    // should output true.
    assert isBST(tree);
    System.out.println(isBST(tree));
    // 10
    // 2 5
    // 1 4 6
    tree.data = 10;
    // should output false.
    assert(!isBST(tree));
    System.out.println(isBST(tree));
    // should output true.
    assert isBST(null);
    System.out.println(isBST(null));
  }
}
