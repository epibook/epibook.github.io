package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SymmetricBinaryTree {
  // @include
  public static boolean isSymmetric(BinaryTree<Integer> T) {
    return T == null || isSymmetricHelper(T.getLeft(), T.getRight());
  }

  private static boolean
  isSymmetricHelper(BinaryTree<Integer> l, BinaryTree<Integer> r) {
    if (l == null && r == null) {
      return true;
    } else if (l != null && r != null) {
      return l.getData() == r.getData()
          && isSymmetricHelper(l.getLeft(), r.getRight())
          && isSymmetricHelper(l.getRight(), r.getLeft());
    } else { // (l != null && r == null) || (l == null && r != null)
      return false;
    }
  }
  // @exclude

  public static void main(String[] args) {
    // non symmetric tree test
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> nonSymmRoot = new BinaryTree<>();
    nonSymmRoot.setLeft(new BinaryTree<Integer>());
    nonSymmRoot.getLeft().setLeft(new BinaryTree<Integer>());
    nonSymmRoot.setRight(new BinaryTree<Integer>());
    nonSymmRoot.getRight().setLeft(new BinaryTree<Integer>());
    nonSymmRoot.getRight().setRight(new BinaryTree<Integer>());
    assert (!isSymmetric(nonSymmRoot));
    System.out.println(isSymmetric(nonSymmRoot));
    // symmetric tree test
    BinaryTree<Integer> symmRoot = new BinaryTree<>();
    symmRoot.setLeft(new BinaryTree<Integer>());
    symmRoot.setRight(new BinaryTree<Integer>());
    assert (isSymmetric(symmRoot));
    System.out.println(isSymmetric(symmRoot));
  }
}
