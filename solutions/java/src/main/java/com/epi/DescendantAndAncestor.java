package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class DescendantAndAncestor {
  // @include
  public static <T extends Comparable<T>> boolean isRSDescendantAncestorOfM(
      BinaryTree<T> r, BinaryTree<T> s, BinaryTree<T> m) {
    BinaryTree<T> curR = r;
    BinaryTree<T> curS = s;

    // Interleaving searches from r and s.
    while (curR != null && curR != s && curS != null && curS != r) {
      if (curR == m || curS == m) {
        return true;
      }
      curR = curR.getData().compareTo(s.getData()) > 0 ? curR.getLeft() : curR
          .getRight();
      curS = curS.getData().compareTo(r.getData()) > 0 ? curS.getLeft() : curS
          .getRight();
    }

    // Reach the other before reaching m.
    if (curR == s || curS == r) {
      return false;
    }
    // Try to search m before reaching the other.
    return searchMBeforeT(curR, s, m) || searchMBeforeT(curS, r, m);
  }

  private static <T extends Comparable<T>> boolean searchMBeforeT(
      BinaryTree<T> p, BinaryTree<T> t, BinaryTree<T> m) {
    while (p != null && p != t && p != m) {
      p = p.getData().compareTo(t.getData()) > 0 ? p.getLeft() : p.getRight();
    }
    return p == m;
  }

  // @exclude

  private static void smallTest() {
    BinaryTree<Integer> root = new BinaryTree<Integer>(5);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setRight(new BinaryTree<Integer>(4));
    assert (!isRSDescendantAncestorOfM(root, root.getLeft(), root.getLeft()
        .getRight()));
  }

  public static void main(String[] args) {
    smallTest();
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setLeft(new BinaryTree<Integer>(1));
    root.setRight(new BinaryTree<Integer>(5));
    root.getRight().setLeft(new BinaryTree<Integer>(4));
    root.getRight().setRight(new BinaryTree<Integer>(6));
    assert (isRSDescendantAncestorOfM(root, root.getRight().getRight(),
        root.getRight()));
    assert (isRSDescendantAncestorOfM(root.getRight().getRight(), root,
        root.getRight()));
    assert (!isRSDescendantAncestorOfM(root, root.getRight(), root.getRight()
        .getRight()));
    assert (!isRSDescendantAncestorOfM(root.getRight(), root, root.getRight()
        .getRight()));
    assert (!isRSDescendantAncestorOfM(root.getRight().getLeft(), root
        .getRight().getRight(), root.getRight()));
    assert (!isRSDescendantAncestorOfM(root.getRight().getLeft(), root
        .getLeft().getLeft(), root.getRight()));
  }
}
