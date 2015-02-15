package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class BSTLowestCommonAncestor {
  // @include
  public static BinaryTree<Integer> findLCA(
      BinaryTree<Integer> tree, BinaryTree<Integer> s, BinaryTree<Integer> b) {
    BinaryTree<Integer> p = tree;
    while (p.getData() < s.getData() || p.getData() > b.getData()) {
      // Keep searching since p is outside of [s, b].
      while (p.getData() < s.getData()) {
        p = p.getRight(); // LCA must be in p's right child.
      }
      while (p.getData() > b.getData()) {
        p = p.getLeft(); // LCA must be in p's left child.
      }
    }

    // s.getData() >= p.getData() && p.getData() <= b.getData().
    return p;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> tree = new BinaryTree<>(3);
    tree.setLeft(new BinaryTree<>(2));
    tree.getLeft().setLeft(new BinaryTree<>(1));
    tree.setRight(new BinaryTree<>(5));
    tree.getRight().setLeft(new BinaryTree<>(4));
    tree.getRight().setRight(new BinaryTree<>(6));
    assert (3 == findLCA(tree, tree.getLeft().getLeft(),
            tree.getRight().getLeft()).getData());
    assert (5 == findLCA(tree, tree.getRight().getLeft(),
            tree.getRight().getRight()).getData());
    assert (2 == findLCA(tree, tree.getLeft().getLeft(), tree.getLeft()).getData());
  }

}
