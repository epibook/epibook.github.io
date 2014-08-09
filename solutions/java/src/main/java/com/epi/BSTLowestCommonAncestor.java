package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BSTLowestCommonAncestor {
  // @include
  public static BinaryTree<Integer> findLCA(
      BinaryTree<Integer> x, BinaryTree<Integer> s, BinaryTree<Integer> b) {
    BinaryTree<Integer> p = x;
    while (p.getData() < s.getData() || p.getData() > b.getData()) {
      while (p.getData() < s.getData()) {
        p = p.getRight(); // LCA must be in p's right child.
      }
      while (p.getData() > b.getData()) {
        p = p.getLeft(); // LCA must be in p's left child.
      }
    }

    // p.getData() >= s.getData() && p.getData() <= b.getData().
    return p;
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
    assert (3 == findLCA(root, root.getLeft().getLeft(),
        root.getRight().getLeft()).getData());
    assert (5 == findLCA(root, root.getRight().getLeft(),
        root.getRight().getRight()).getData());
    assert (2 == findLCA(root, root.getLeft().getLeft(), root.getLeft())
        .getData());
  }

}
