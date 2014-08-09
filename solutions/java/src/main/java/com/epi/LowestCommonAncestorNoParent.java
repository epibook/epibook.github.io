package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LowestCommonAncestorNoParent {
  // @include
  public static BinaryTree<Integer> LCA(BinaryTree<Integer> n,
                                        BinaryTree<Integer> a,
                                        BinaryTree<Integer> b) {
    if (n == null) { // Empty subtree.
      return null;
    } else if (n == a || n == b) {
      return n;
    }

    BinaryTree<Integer> lRes = LCA(n.getLeft(), a, b);
    BinaryTree<Integer> rRes = LCA(n.getRight(), a, b);
    if (lRes != null && rRes != null) {
      return n; // Found a and b in different subtrees.
    }
    return lRes != null ? lRes : rRes;
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
