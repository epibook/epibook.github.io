package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class SearchBSTFirstLargerK {
  // @include
  public static BinaryTree<Integer>
  findFirstGreaterThanK(BinaryTree<Integer> r, Integer k) {
    boolean foundK = false;
    BinaryTree<Integer> subtree = r;
    BinaryTree<Integer> firstSoFar = null;

    while (subtree != null) {
      if (subtree.getData().compareTo(k) == 0) {
        foundK = true;
        subtree = subtree.getRight();
      } else if (subtree.getData().compareTo(k) > 0) {
        firstSoFar = subtree;
        subtree = subtree.getLeft();
      } else { // subtree.getData().compareTo(k) < 0
        subtree = subtree.getRight();
      }
    }
    return foundK ? firstSoFar : null;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 7
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(7));
    assert (findFirstGreaterThanK(root, 1) == root.getLeft());
    assert (findFirstGreaterThanK(root, 5) == root.getRight().getRight());
    assert (findFirstGreaterThanK(root, 6) == null);
    assert (findFirstGreaterThanK(root, 7) == null);
  }
}
