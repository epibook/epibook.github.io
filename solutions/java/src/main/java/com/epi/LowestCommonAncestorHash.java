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
      // Ascend tree in tandem from these two nodes.
      if (node0 != null) {
        if (!hash.add(node0)) {
          return node0;
        }
        node0 = node0.parent;
      }
      if (node1 != null) {
        if (!hash.add(node1)) {
          return node1;
        }
        node1 = node1.parent;
      }
    }
    throw new IllegalArgumentException(
        "node0 and node1 are not in the same tree");
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.left = new BinaryTree<>(2, null, null);
    root.left.parent = root;
    root.left.left = new BinaryTree<>(1, null, null);
    root.left.left.parent = root.left;
    root.right = new BinaryTree<>(5, null, null);
    root.right.parent = root;
    root.right.left = new BinaryTree<>(4, null, null);
    root.right.left.parent = root.right;
    root.right.right = new BinaryTree<>(6, null, null);
    root.right.right.parent = root.right;

    // should output 3
    assert(LCA(root.left, root.right).data.equals(3));
    System.out.println(LCA(root.left, root.right).data);
    // should output 5
    assert(LCA(root.right.left, root.right.right).data.equals(5));
    System.out.println(LCA(root.right.left, root.right.right).data);
  }
}
