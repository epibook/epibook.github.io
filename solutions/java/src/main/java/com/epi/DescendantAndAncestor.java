package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class DescendantAndAncestor {
  // @include
  public static boolean isRSDescendantAncestorOfM(
      BinaryTree<Integer> r, BinaryTree<Integer> s, BinaryTree<Integer> m) {
    BinaryTree<Integer> curR = r;
    BinaryTree<Integer> curS = s;

    // Interleaving searches from r and s.
    while (curR != null && curR != s && curR != m 
           && curS != null && curS != r && curS != m) {
      curR = curR.getData() > m.getData() ? curR.getLeft() : curR.getRight();
      curS = curS.getData() > m.getData() ? curS.getLeft() : curS.getRight();
    }

    // Reach the other before reaching m.
    if (curR == s || curS == r || (curR != m && curS != m)) {
      return false;
    }

    // Try to search m before reaching the other.
    return searchTarget(m, s) || searchTarget(m, r);
  }

  private static boolean searchTarget(
      BinaryTree<Integer> p, BinaryTree<Integer> t) {
    while (p != null && p != t) {
      p = p.getData() > t.getData() ? p.getLeft() : p.getRight();
    }
    return p == t;
  }
  // @exclude

  private static void smallTest() {
    BinaryTree<Integer> root = new BinaryTree<>(5);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setRight(new BinaryTree<>(4));
    assert (!isRSDescendantAncestorOfM(root, root.getLeft(), root.getLeft()
        .getRight()));

    root = new BinaryTree<>(4);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.getLeft().setRight(new BinaryTree<>(3));
    root.setRight(new BinaryTree<>(6));
    root.getRight().setLeft(new BinaryTree<>(5));
    root.getRight().setRight(new BinaryTree<>(7));
    assert (!isRSDescendantAncestorOfM(root.getRight(), root.getLeft(), root.getRight()
        .getLeft()));
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
