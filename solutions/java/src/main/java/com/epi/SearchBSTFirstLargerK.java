package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchBSTFirstLargerK {
  // @include
  public static BinaryTree<Integer>
  findFirstLargerKWithKExist(BinaryTree<Integer> r, Integer k) {
    boolean foundK = false;
    BinaryTree<Integer> curr = r;
    BinaryTree<Integer> first = null;

    while (curr != null) {
      if (curr.getData().compareTo(k) == 0) {
        foundK = true;
        curr = curr.getRight();
      } else if (curr.getData().compareTo(k) > 0) {
        first = curr;
        curr = curr.getLeft();
      } else { // curr.getData().compareTo(k) < 0
        curr = curr.getRight();
      }
    }
    return foundK ? first : null;
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
    assert (findFirstLargerKWithKExist(root, 1) == root.getLeft());
    assert (findFirstLargerKWithKExist(root, 5) == root.getRight().getRight());
    assert (findFirstLargerKWithKExist(root, 6) == null);
    assert (findFirstLargerKWithKExist(root, 7) == null);
  }
}
