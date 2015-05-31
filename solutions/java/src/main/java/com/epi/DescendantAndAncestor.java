package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class DescendantAndAncestor {
  // @include
  public static boolean pairIncludesAncestorAndDescendantOfM(
      BSTNode<Integer> possibleAncOrDesc0, BSTNode<Integer> possibleAncOrDesc1,
      BSTNode<Integer> middle) {
    BSTNode<Integer> search0 = possibleAncOrDesc0, search1 = possibleAncOrDesc1;

    // Perform interleaved searching from possibleAncOrDesc0 and
    // possibleAncOrDesc1 for middle.
    while (search0 != possibleAncOrDesc1 && search0 != middle &&
           search1 != possibleAncOrDesc0 && search1 != middle &&
           (search0 != null || search1 != null)) {
      if (search0 != null) {
        search0 = search0.getData() > middle.getData() ? search0.getLeft()
                                                       : search0.getRight();
      }
      if (search1 != null) {
        search1 = search1.getData() > middle.getData() ? search1.getLeft()
                                                       : search1.getRight();
      }
    }

    // If both searches were unsuccessful, or we got from possibleAncOrDesc0
    // to possibleAncOrDesc1 without seeing middle, or from possibleAncOrDesc1
    // to possibleAncOrDesc0 without seeing middle, middle cannot lie between
    // possibleAncOrDesc0 and possibleAncOrDesc1.
    if (search0 == possibleAncOrDesc1 || search1 == possibleAncOrDesc0 ||
        (search0 != middle && search1 != middle)) {
      return false;
    }

    // If we get here, we already know one of possibleAncOrDesc0 or
    // possibleAncOrDesc1 has a path to middle. Check if middle has a path to
    // possibleAncOrDesc1 or to possibleAncOrDesc0.
    return search0 == middle ? searchTarget(middle, possibleAncOrDesc1)
                             : searchTarget(middle, possibleAncOrDesc0);
  }

  private static boolean searchTarget(BSTNode<Integer> from,
                                      BSTNode<Integer> target) {
    while (from != null && from != target) {
      from = from.getData() > target.getData() ? from.getLeft() : from.getRight();
    }
    return from == target;
  }
  // @exclude

  private static void smallTest() {
    BSTNode<Integer> root = new BSTNode<>(5);
    root.setLeft(new BSTNode<>(2));
    root.getLeft().setRight(new BSTNode<>(4));
    assert(!pairIncludesAncestorAndDescendantOfM(root, root.getLeft(),
                                                 root.getLeft().getRight()));

    // Example of the first figure of BST chapter.
    root = new BSTNode<>(19);
    root.setLeft(new BSTNode<>(7));
    root.getLeft().setLeft(new BSTNode<>(3));
    root.getLeft().getLeft().setLeft(new BSTNode<>(2));
    root.getLeft().getLeft().setRight(new BSTNode<>(5));
    root.getLeft().setRight(new BSTNode<>(11));
    root.getLeft().getRight().setRight(new BSTNode<>(17));
    root.getLeft().getRight().getRight().setLeft(new BSTNode<>(13));
    root.setRight(new BSTNode<>(43));
    root.getRight().setLeft(new BSTNode<>(23));
    root.getRight().getLeft().setRight(new BSTNode<>(37));
    root.getRight().getLeft().getRight().setLeft(new BSTNode<>(29));
    root.getRight().getLeft().getRight().getLeft().setRight(new BSTNode<>(31));
    root.getRight().getLeft().getRight().setRight(new BSTNode<>(41));
    root.getRight().setRight(new BSTNode<>(47));
    root.getRight().getRight().setRight(new BSTNode<>(53));
    assert(!pairIncludesAncestorAndDescendantOfM(root.getRight(), root.getLeft(),
                                                 root.getRight().getLeft()));
    assert(pairIncludesAncestorAndDescendantOfM(
        root, root.getRight().getLeft().getRight().getLeft().getRight(),
        root.getRight().getLeft()));
  }

  public static void main(String[] args) {
    smallTest();
    //    3
    //  2   5
    // 1   4 6
    BSTNode<Integer> root = new BSTNode<>(3);
    root.setLeft(new BSTNode<>(2));
    root.getLeft().setLeft(new BSTNode<>(1));
    root.setRight(new BSTNode<>(5));
    root.getRight().setLeft(new BSTNode<>(4));
    root.getRight().setRight(new BSTNode<>(6));
    assert(pairIncludesAncestorAndDescendantOfM(root, root.getRight().getRight(),
                                                root.getRight()));
    assert(pairIncludesAncestorAndDescendantOfM(root.getRight().getRight(), root,
                                                root.getRight()));
    assert(!pairIncludesAncestorAndDescendantOfM(root, root.getRight(),
                                                 root.getRight().getRight()));
    assert(!pairIncludesAncestorAndDescendantOfM(root.getRight(), root,
                                                 root.getRight().getRight()));
    assert(!pairIncludesAncestorAndDescendantOfM(root.getRight().getLeft(),
                                                 root.getRight().getRight(),
                                                 root.getRight()));
    assert(!pairIncludesAncestorAndDescendantOfM(root.getRight().getLeft(),
                                                 root.getLeft().getLeft(),
                                                 root.getRight()));
  }
}
