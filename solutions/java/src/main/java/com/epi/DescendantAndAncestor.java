package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class DescendantAndAncestor {
  // @include
  public static boolean isRSDescendantAncestorPairForM(
      BSTNode<Integer> r, BSTNode<Integer> s, BSTNode<Integer> m) {
    BSTNode<Integer> curR = r, curS = s;

    // Perform interleaved searching from r and s for m.
    while (curR != null && curR != s && curR != m
           && curS != null && curS != r && curS != m) {
      curR = curR.getData() > m.getData() ? curR.getLeft() : curR.getRight();
      curS = curS.getData() > m.getData() ? curS.getLeft() : curS.getRight();
    }

    // If both searches were unsuccessful, or we got from r to s without
    // seeing m, or from s to r without seeing m, m cannot lie between r and s.
    if (curR == s || curS == r || (curR != m && curS != m)) {
      return false;
    }

    // Check if m has a path to s or to r. If we get here, we already
    // know one of r or s has a path to m.
    return searchTarget(m, s) || searchTarget(m, r);
  }

  private static boolean searchTarget(
      BSTNode<Integer> p, BSTNode<Integer> target) {
    while (p != null && p != target) {
      p = p.getData() > target.getData() ? p.getLeft() : p.getRight();
    }
    return p == target;
  }
  // @exclude

  private static void smallTest() {
    BSTNode<Integer> root = new BSTNode<>(5);
    root.setLeft(new BSTNode<>(2));
    root.getLeft().setRight(new BSTNode<>(4));
    assert (!isRSDescendantAncestorPairForM(root, root.getLeft(),
                                            root.getLeft().getRight()));

    root = new BSTNode<>(4);
    root.setLeft(new BSTNode<>(2));
    root.getLeft().setLeft(new BSTNode<>(1));
    root.getLeft().setRight(new BSTNode<>(3));
    root.setRight(new BSTNode<>(6));
    root.getRight().setLeft(new BSTNode<>(5));
    root.getRight().setRight(new BSTNode<>(7));
    assert (!isRSDescendantAncestorPairForM(root.getRight(), root.getLeft(),
                                            root.getRight().getLeft()));
  }

  public static void main(String[] args) {
    smallTest();
    // 3
    // 2 5
    // 1 4 6
    BSTNode<Integer> root = new BSTNode<>(3);
    root.setLeft(new BSTNode<>(2));
    root.getLeft().setLeft(new BSTNode<>(1));
    root.setRight(new BSTNode<>(5));
    root.getRight().setLeft(new BSTNode<>(4));
    root.getRight().setRight(new BSTNode<>(6));
    assert (isRSDescendantAncestorPairForM(root, root.getRight().getRight(),
                                           root.getRight()));
    assert (isRSDescendantAncestorPairForM(root.getRight().getRight(), root,
                                           root.getRight()));
    assert (!isRSDescendantAncestorPairForM(root, root.getRight(),
                                            root.getRight().getRight()));
    assert (!isRSDescendantAncestorPairForM(root.getRight(), root,
                                            root.getRight().getRight()));
    assert (!isRSDescendantAncestorPairForM(root.getRight().getLeft(),
                                            root.getRight().getRight(),
                                            root.getRight()));
    assert (!isRSDescendantAncestorPairForM(root.getRight().getLeft(),
                                            root.getLeft().getLeft(),
                                            root.getRight()));
  }
}
