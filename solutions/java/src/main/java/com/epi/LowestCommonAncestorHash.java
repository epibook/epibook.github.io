package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorHash {
  // @include
  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    Set<BinaryTree<Integer>> hash = new HashSet<>();
    while (node0 != null || node1 != null) {
      // Ascend in tandem for iter_0 and iter_1.
      if (node0 != null) {
        if (!hash.add(node0)) {
          return node0;
        }
        node0 = node0.getParent();
      }
      if (node1 != null) {
        if (!hash.add(node1)) {
          return node1;
        }
        node1 = node1.getParent();
      }
    }
    throw new RuntimeException("node0 and node1 are not in the same tree");
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.setLeft(new BinaryTree<>(2, null, null));
    root.getLeft().setParent(root);
    root.getLeft().setLeft(new BinaryTree<>(1, null, null));
    root.getLeft().getLeft().setParent(root.getLeft());
    root.setRight(new BinaryTree<>(5, null, null));
    root.getRight().setParent(root);
    root.getRight().setLeft(new BinaryTree<>(4, null, null));
    root.getRight().getLeft().setParent(root.getRight());
    root.getRight().setRight(new BinaryTree<>(6, null, null));
    root.getRight().getRight().setParent(root.getRight());

    // should output 3
    assert(LCA(root.getLeft(), root.getRight()).getData().equals(3));
    System.out.println(LCA(root.getLeft(), root.getRight()).getData());
    // should output 5
    assert(LCA(root.getRight().getLeft(), root.getRight().getRight())
               .getData()
               .equals(5));
    System.out.println(
        LCA(root.getRight().getLeft(), root.getRight().getRight()).getData());
  }
}
