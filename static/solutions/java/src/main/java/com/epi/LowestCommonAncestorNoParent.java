package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class LowestCommonAncestorNoParent {
  // @include
  private static class Status {
    public int numTargetNodes;
    public BinaryTreeNode<Integer> ancestor;

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

  // Returns an object consisting of an int and a node. The int field is
  // 0, 1, or 2 depending on how many of {node0, node1} are present in
  // the tree. If both are present in the tree, when ancestor is
  // assigned to a non-null value, it is the LCA.
  private static Status LCAHelper(BinaryTreeNode<Integer> tree,
                                  BinaryTreeNode<Integer> node0,
                                  BinaryTreeNode<Integer> node1) {
    if (tree == null) {
      return new Status(0, null);
    }

    Status leftResult = LCAHelper(tree.left, node0, node1);
    if (leftResult.numTargetNodes == 2) {
      // Found both nodes in the left subtree.
      return leftResult;
    }
    Status rightResult = LCAHelper(tree.right, node0, node1);
    if (rightResult.numTargetNodes == 2) {
      // Found both nodes in the right subtree.
      return rightResult;
    }
    int numTargetNodes = leftResult.numTargetNodes + rightResult.numTargetNodes
                         + (tree == node0 ? 1 : 0) + (tree == node1 ? 1 : 0);
    return new Status(numTargetNodes, numTargetNodes == 2 ? tree : null);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3, null, null);
    tree.left = new BinaryTreeNode<>(2, null, null);
    tree.left.left = new BinaryTreeNode<>(1, null, null);
    tree.right = new BinaryTreeNode<>(5, null, null);
    tree.right.left = new BinaryTreeNode<>(4, null, null);
    tree.right.right = new BinaryTreeNode<>(6, null, null);
    // should output 3
    BinaryTreeNode<Integer> x = LCA(tree, tree.left, tree.right);
    assert(x.data.equals(3));
    System.out.println(x.data);
    // should output 5
    x = LCA(tree, tree.right.left, tree.right.right);
    assert(x.data.equals(5));
    System.out.println(x.data);
    // should output 5
    x = LCA(tree, tree.right, tree.right.right);
    assert(x.data.equals(5));
    System.out.println(x.data);
    // should output 3
    x = LCA(tree, tree.left.left, tree.right.right);
    assert(x.data.equals(3));
    System.out.println(x.data);
    // should output 3
    x = LCA(tree, tree.left.left, tree);
    assert(x.data.equals(3));
    System.out.println(x.data);
    // should output 2
    x = LCA(tree, tree.left, tree.left);
    assert(x.data.equals(2));
    System.out.println(x.data);
  }
}
