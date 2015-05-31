package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class LowestCommonAncestorNoParent {
  // @include
  private static class Status {
    int numTargetNodes;
    BinaryTreeNode<Integer> ancestor;

    public Status(int numTargetNodes, BinaryTreeNode<Integer> node) {
      this.numTargetNodes = numTargetNodes;
      this.ancestor = node;
    }
  }

  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    return LCAHelper(tree, node0, node1).ancestor;
  }

  // Returns an object of int and node; int field is 0, 1, or 2 depending on
  // how many of node0 and node1 are present in tree. If both are present in
  // tree, the node pointer is a common ancestor. It may not be the LCA
  // initially, but it will be LCA when the algorithm terminates.
  private static Status LCAHelper(BinaryTreeNode<Integer> tree,
                                  BinaryTreeNode<Integer> node0,
                                  BinaryTreeNode<Integer> node1) {
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
    int numTargetNodes = leftResult.numTargetNodes + rightResult.numTargetNodes +
                         (tree == node0 || tree == node1 ? 1 : 0);
    return new Status(numTargetNodes, numTargetNodes == 2 ? tree : null);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3, null, null);
    tree.setLeft(new BinaryTreeNode<>(2, null, null));
    tree.getLeft().setLeft(new BinaryTreeNode<>(1, null, null));
    tree.setRight(new BinaryTreeNode<>(5, null, null));
    tree.getRight().setLeft(new BinaryTreeNode<>(4, null, null));
    tree.getRight().setRight(new BinaryTreeNode<>(6, null, null));
    // should output 3
    BinaryTreeNode<Integer> x = LCA(tree, tree.getLeft(), tree.getRight());
    assert(x.getData().equals(3));
    System.out.println(x.getData());
    // should output 5
    x = LCA(tree, tree.getRight().getLeft(), tree.getRight().getRight());
    assert(x.getData().equals(5));
    System.out.println(x.getData());
    // should output 5
    x = LCA(tree, tree.getRight(), tree.getRight().getRight());
    assert(x.getData().equals(5));
    System.out.println(x.getData());
  }
}
