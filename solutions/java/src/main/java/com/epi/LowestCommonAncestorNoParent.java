package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class LowestCommonAncestorNoParent {
  // @include
  private static class Status {
    int numTargetNodes;
    BinaryTree<Integer> ancestor;

    public Status(int numTargetNodes,  BinaryTree<Integer> node) {
      this.numTargetNodes = numTargetNodes;
      this.ancestor = node;
    }
  }

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> tree,
                                        BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    return LCAHelper(tree, node0, node1).ancestor;
  }

  // Returns an object of int and node; int field is 0, 1, or 2 depending on
  // how many of node0 and node1 are present in tree. If both are present in
  // tree, the node pointer is a common ancestor. It may not be the LCA
  // initially, but it will be LCA when the algorithm terminates.
  private static Status LCAHelper(BinaryTree<Integer> tree,
                                  BinaryTree<Integer> node0,
                                  BinaryTree<Integer> node1) {
    if (tree == null) {
      return new Status(0, null);
    }

    Status leftResult = LCAHelper(tree.getLeft(), node0, node1);
    if (leftResult.numTargetNodes == 2) {
      // Found both nodes in the left subtree.
      return leftResult;
    }
    Status rightResult = LCAHelper(tree.getRight(), node0, node1);
    if (rightResult.numTargetNodes == 2) {
      // Found both nodes in the right subtree.
      return rightResult;
    }
    int numTargetNodes = leftResult.numTargetNodes + rightResult.numTargetNodes
                         + (tree == node0 || tree == node1 ? 1 : 0);
    return new Status(numTargetNodes, numTargetNodes == 2 ? tree : null);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.setLeft(new BinaryTree<>(2, null, null));
    root.getLeft().setLeft(new BinaryTree<>(1, null, null));
    root.setRight(new BinaryTree<>(5, null, null));
    root.getRight().setLeft(new BinaryTree<>(4, null, null));
    root.getRight().setRight(new BinaryTree<>(6, null, null));
    // should output 3
    BinaryTree<Integer> x = LCA(root, root.getLeft(), root.getRight());
    assert (x.getData().equals(3));
    System.out.println(x.getData());
    // should output 5
    x = LCA(root, root.getRight().getLeft(), root.getRight().getRight());
    assert (x.getData().equals(5));
    System.out.println(x.getData());
    // should output 5
    x = LCA(root, root.getRight(), root.getRight().getRight());
    assert (x.getData().equals(5));
    System.out.println(x.getData());
  }
}
