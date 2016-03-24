package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class BSTLowestCommonAncestor {
  // @include
  // Input nodes are not null and the key at s is less than or equal to that at
  // b.
  public static BSTNode<Integer> findLCA(BSTNode<Integer> tree,
                                         BSTNode<Integer> s,
                                         BSTNode<Integer> b) {
    BSTNode<Integer> p = tree;
    while (p.data < s.data || p.data > b.data) {
      // Keep searching since p is outside of [s, b].
      while (p.data < s.data) {
        p = p.right; // LCA must be in p's right child.
      }
      while (p.data > b.data) {
        p = p.left; // LCA must be in p's left child.
      }
    }
    // Now, s.data >= p.data && p.data <= b.data.
    return p;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BSTNode<Integer> tree = new BSTNode<>(3);
    tree.left = new BSTNode<>(2);
    tree.left.left = new BSTNode<>(1);
    tree.right = new BSTNode<>(5);
    tree.right.left = new BSTNode<>(4);
    tree.right.right = new BSTNode<>(6);
    assert(3 == findLCA(tree, tree.left.left, tree.right.left).data);
    assert(5 == findLCA(tree, tree.right.left, tree.right.right).data);
    assert(2 == findLCA(tree, tree.left.left, tree.left).data);
    assert(3 == findLCA(tree, tree.left.left, tree.right).data);
  }
}
