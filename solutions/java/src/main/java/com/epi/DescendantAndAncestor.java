package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class DescendantAndAncestor {
  // @include
  public static boolean isRSDescendantAncestorOfM(
      BinaryTree<Integer> r, BinaryTree<Integer> s, BinaryTree<Integer> m) {
    BinaryTree<Integer> curR = r;
    BinaryTree<Integer> curS = s;

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

  private static boolean searchMBeforeT(
      BinaryTree<Integer> p, BinaryTree<Integer> t, BinaryTree<Integer> m) {
    while (p != null && p != t && p != m) {
      p = p.getData().compareTo(t.getData()) > 0 ? p.getLeft() : p.getRight();
    }
    return p == m;
  }
  // @exclude

  private static void smallTest() {
    BinaryTree<Integer> root = new BinaryTree<>(5);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setRight(new BinaryTree<>(4));
    assert (!isRSDescendantAncestorOfM(root, root.getLeft(), root.getLeft()
        .getRight()));
  }

  public static void main(String[] args) {
    smallTest();
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
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
