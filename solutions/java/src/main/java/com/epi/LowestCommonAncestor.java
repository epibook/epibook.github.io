package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

public class LowestCommonAncestor {
  // @include
  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int depth0 = getDepth(node0), depth1 = getDepth(node1);
    // Makes node0 as the deeper node in order to simplify the code.
    if (depth1 > depth0) {
      BinaryTree<Integer> temp = node0;
      node0 = node1;
      node1 = temp;
    }
    // Ascends from the deeper node.
    int depthDiff = Math.abs(depth0 - depth1);
    while (depthDiff-- > 0) {
      node0 = node0.getParent();
    }

    // Now ascends both nodes until we reach the LCA.
    while (node0 != node1) {
      node0 = node0.getParent();
      node1 = node1.getParent();
    }
    return node0;
  }

  private static int getDepth(BinaryTree<Integer> node) {
    int depth = 0;
    while (node != null) {
      ++depth;
      node = node.getParent();
    }
    return depth;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null, null);
    root.setLeft(new BinaryTree<>(2, null, null, root));
    root.getLeft().setLeft(new BinaryTree<>(1, null, null, root.getLeft()));
    root.setRight(new BinaryTree<>(5, null, null, root));
    root.getRight().setLeft(new BinaryTree<>(4, null, null, root.getRight()));
    root.getRight().setRight(new BinaryTree<>(6, null, null, root.getRight()));

    // should output 3
    assert(LCA(root.getLeft(), root.getRight()).getData().equals(3));
    System.out.println(LCA(root.getLeft(), root.getRight()).getData());
    // should output 5
    assert(LCA(root.getRight().getLeft(), root.getRight().getRight())
               .getData()
               .equals(5));
    System.out.println(
        LCA(root.getRight().getLeft(), root.getRight().getRight()).getData());
    // should output 3
    assert(LCA(root.getLeft(), root.getRight().getLeft()).getData().equals(3));
    System.out.println(LCA(root.getLeft(), root.getRight().getLeft()).getData());
    // should output 2
    assert(LCA(root.getLeft(), root.getLeft().getLeft()).getData().equals(2));
    System.out.println(LCA(root.getLeft(), root.getLeft().getLeft()).getData());
  }
}
