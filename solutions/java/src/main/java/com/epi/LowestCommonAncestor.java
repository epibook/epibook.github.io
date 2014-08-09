package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LowestCommonAncestor {
  // @include
  public static BinaryTree<Integer> LCA(BinaryTree<Integer> i,
                                        BinaryTree<Integer> j) {
    int depthI = getDepth(i), depthJ = getDepth(j);
    if (depthJ > depthI) {
      BinaryTree<Integer> temp = i;
      i = j;
      j = temp;
    }

    // Advances deeper node first.
    int depthDiff = Math.abs(depthI - depthJ);
    while (depthDiff-- > 0) {
      i = i.getParent();
    }

    // Both pointers advance until they found a common ancestor.
    while (i != j) {
      i = i.getParent();
      j = j.getParent();
    }
    return i;
  }

  private static int getDepth(BinaryTree<Integer> n) {
    int d = 0;
    while (n != null) {
      ++d;
      n = n.getParent();
    }
    return d;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null, null);
    root.setLeft(new BinaryTree<>(2, null, null, root));
    root.getLeft().setLeft(
        new BinaryTree<>(1, null, null, root.getLeft()));
    root.setRight(new BinaryTree<>(5, null, null, root));
    root.getRight().setLeft(
        new BinaryTree<>(4, null, null, root.getRight()));
    root.getRight().setRight(
        new BinaryTree<>(6, null, null, root.getRight()));

    // should output 3
    assert (LCA(root.getLeft(), root.getRight()).getData().equals(3));
    System.out.println(LCA(root.getLeft(), root.getRight()).getData());
    // should output 5
    assert (LCA(root.getRight().getLeft(), root.getRight().getRight())
        .getData().equals(5));
    System.out.println(LCA(root.getRight().getLeft(),
        root.getRight().getRight()).getData());
    // should output 3
    assert (LCA(root.getLeft(), root.getRight().getLeft()).getData().equals(3));
    System.out
        .println(LCA(root.getLeft(), root.getRight().getLeft()).getData());
    // should output 2
    assert (LCA(root.getLeft(), root.getLeft().getLeft()).getData().equals(2));
    System.out.println(LCA(root.getLeft(), root.getLeft().getLeft()).getData());
  }
}
