package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class IsBinaryTreeABST {
  // @include
  public static <T extends Comparable<T>> boolean isBST(BinaryTree<T> r, T min,
      T max) {
    return isBSTHelper(r, min, max);
  }

  private static <T extends Comparable<T>> boolean isBSTHelper(BinaryTree<T> r,
      T lower, T upper) {
    if (r == null) {
      return true;
    } else if (r.getData().compareTo(lower) < 0
        || r.getData().compareTo(upper) > 0) {
      return false;
    }

    return isBSTHelper(r.getLeft(), lower, r.getData())
        && isBSTHelper(r.getRight(), r.getData(), upper);
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
    assert !isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    // should output true.
    assert isBST(null, Integer.MIN_VALUE, Integer.MAX_VALUE);
    System.out.println(isBST(null, Integer.MIN_VALUE, Integer.MAX_VALUE));
  }
}
