package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class NearestRestaurant {
  private static <T> BinaryTree<T> findSuccessorBST(BinaryTree<T> n) {
    if (n.getRight() != null) {
      // Find the smallest element in n's right subtree.
      n = n.getRight();
      while (n.getLeft() != null) {
        n = n.getLeft();
      }
      return n;
    }

    // Find the first parent which is larger than n.
    while (n.getParent() != null && n.getParent().getRight() == n) {
      n = n.getParent();
    }
    // Return nullptr means n is the largest in this BST.
    return n.getParent() != null ? n.getParent() : null;
  }

  // @include
  public static List<BinaryTree<Integer>> rangeQueryOnBST(BinaryTree<Integer> n,
                                                          Integer L, Integer U) {
    List<BinaryTree<Integer>> res = new ArrayList<>();
    for (BinaryTree<Integer> it = findFirstLargerEqualK(n, L);
         it != null && it.getData().compareTo(U) <= 0;
         it = findSuccessorBST(it)) {
      res.add(it);
    }
    return res;
  }

  private static BinaryTree<Integer> findFirstLargerEqualK(BinaryTree<Integer> r,
                                                           Integer k) {
    if (r == null) {
      return null;
    } else if (r.getData().compareTo(k) < 0) {
      return findFirstLargerEqualK(r.getRight(), k);
    }
    // Recursively search the left subtree for first one >= k.
    BinaryTree<Integer> n = findFirstLargerEqualK(r.getLeft(), k);
    return n != null ? n : r;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.setLeft(new BinaryTree<>(2, null, null));
    root.getLeft().setParent(root);
    root.getLeft().setLeft(new BinaryTree<>(1, null, null));
    root.getLeft().getLeft().setParent(root.getLeft());
    root.setRight(new BinaryTree<>(5, null, null));
    root.getRight().setParent(root);
    root.getRight().setLeft(new BinaryTree<>(4, null, null));
    root.getRight().getLeft().setParent(root.getRight());
    root.getRight().setRight(new BinaryTree<>(6, null, null));
    root.getRight().getRight().setParent(root.getRight());
    List<BinaryTree<Integer>> res = rangeQueryOnBST(root, 2, 5);
    assert(res.size() == 4);
    for (BinaryTree<Integer> l : res) {
      assert(l.getData() >= 2 && l.getData() <= 5);
    }
    res = rangeQueryOnBST(root, -1, 0);
    assert(res.isEmpty());
    res = rangeQueryOnBST(root, 10, 25);
    assert(res.isEmpty());
    res = rangeQueryOnBST(root, -10, 30);
    assert(res.size() == 6);
    for (BinaryTree<Integer> l : res) {
      assert(l.getData() >= 1 && l.getData() <= 6);
    }
  }
}
