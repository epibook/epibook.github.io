package com.epi;

import java.util.HashSet;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LowestCommonAncestorHashTemplate {
  // @include
  public static <T> BinaryTree<T> LCA(BinaryTree<T> i, BinaryTree<T> j) {
    HashSet<BinaryTree<T>> hash = new HashSet<BinaryTree<T>>();
    while (i != null || j != null) {
      if (i != null) {
        if (hash.add(i) == false) {
          return i; // adds a failed because a exists in hash.
        }
        i = i.getParent();
      }
      if (j != null) {
        if (hash.add(j) == false) {
          return j; // adds a failed because a exists in hash.
        }
        j = j.getParent();
      }
    }
    // Throw error if a and b are not in the same tree.
    throw new RuntimeException("a and b are not in the same tree");
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3, null, null);
    root.setLeft(new BinaryTree<Integer>(2, null, null));
    root.getLeft().setParent(root);
    root.getLeft().setLeft(new BinaryTree<Integer>(1, null, null));
    root.getLeft().getLeft().setParent(root.getLeft());
    root.setRight(new BinaryTree<Integer>(5, null, null));
    root.getRight().setParent(root);
    root.getRight().setLeft(new BinaryTree<Integer>(4, null, null));
    root.getRight().getLeft().setParent(root.getRight());
    root.getRight().setRight(new BinaryTree<Integer>(6, null, null));
    root.getRight().getRight().setParent(root.getRight());

    // should output 3
    assert (LCA(root.getLeft(), root.getRight()).getData().equals(3));
    System.out.println(LCA(root.getLeft(), root.getRight()).getData());
    // should output 5
    assert (LCA(root.getRight().getLeft(), root.getRight().getRight())
        .getData().equals(5));
    System.out.println(LCA(root.getRight().getLeft(),
        root.getRight().getRight()).getData());
  }
}
