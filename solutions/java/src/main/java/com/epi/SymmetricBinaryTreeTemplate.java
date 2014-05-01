package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SymmetricBinaryTreeTemplate {
  // @include
  private static <T> boolean isSymmetricHelper(BinaryTree<T> l, BinaryTree<T> r) {
    if (l == null && r == null) {
      return true;
    } else if (l != null && r != null) {
      return equals(l.getData(), r.getData())
          && isSymmetricHelper(l.getLeft(), r.getRight())
          && isSymmetricHelper(l.getRight(), r.getLeft());
    } else { // (l != null && r == null) || (l == null && r != null)
      return false;
    }
  }

  public static <T> boolean isSymmetric(BinaryTree<T> n) {
    return n == null || isSymmetricHelper(n.getLeft(), n.getRight());
  }

  // @exclude

  private static <T> boolean equals(T t1, T t2) {
    return t1 == null && t2 == null || t1 != null && t1.equals(t2);
  }

  public static void main(String[] args) {
    // non symmetric tree test
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> nonSymmRoot = new BinaryTree<Integer>();
    nonSymmRoot.setLeft(new BinaryTree<Integer>());
    nonSymmRoot.getLeft().setLeft(new BinaryTree<Integer>());
    nonSymmRoot.setRight(new BinaryTree<Integer>());
    nonSymmRoot.getRight().setLeft(new BinaryTree<Integer>());
    nonSymmRoot.getRight().setRight(new BinaryTree<Integer>());
    assert (!isSymmetric(nonSymmRoot));
    System.out.println(isSymmetric(nonSymmRoot));
    // symmetric tree test
    BinaryTree<Integer> symmRoot = new BinaryTree<Integer>();
    symmRoot.setLeft(new BinaryTree<Integer>());
    symmRoot.setRight(new BinaryTree<Integer>());
    assert (isSymmetric(symmRoot));
    System.out.println(isSymmetric(symmRoot));
  }
}
