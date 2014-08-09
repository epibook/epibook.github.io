package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class IsBinaryTreeABST {
  // @include
  public static boolean isBST(BinaryTree<Integer> root) {
    return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBSTHelper(BinaryTree<Integer> root,
                                     Integer lower, Integer upper) {
    if (root == null) {
      return true;
    } else if (root.getData().compareTo(lower) < 0
               || root.getData().compareTo(upper) > 0) {
      return false;
    }

    return isBSTHelper(root.getLeft(), lower, root.getData())
        && isBSTHelper(root.getRight(), root.getData(), upper);
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
    assert !isBST(root);
    System.out.println(isBST(root));
    // should output true.
    assert isBST(null);
    System.out.println(isBST(null));
  }
}
