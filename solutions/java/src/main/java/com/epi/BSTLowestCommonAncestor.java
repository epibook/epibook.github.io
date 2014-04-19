package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BSTLowestCommonAncestor {
  // @include
  public static <T extends Comparable<T>> BinaryTree<T> findLCA(
      BinaryTree<T> x, BinaryTree<T> s, BinaryTree<T> b) {
    BinaryTree<T> p = x;
    while (p.getData().compareTo(s.getData()) < 0
        || p.getData().compareTo(b.getData()) > 0) {
      while (p.getData().compareTo(s.getData()) < 0) {
        p = p.getRight(); // LCA must be in p's right child.
      }
      while (p.getData().compareTo(b.getData()) > 0) {
        p = p.getLeft(); // LCA must be in p's left child.
      }
    }

    // p->data >= s->data && p->data <= b->data.
    return p;
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
    assert (3 == findLCA(root, root.getLeft().getLeft(),
        root.getRight().getLeft()).getData());
    assert (5 == findLCA(root, root.getRight().getLeft(),
        root.getRight().getRight()).getData());
    assert (2 == findLCA(root, root.getLeft().getLeft(), root.getLeft())
        .getData());
  }

}
