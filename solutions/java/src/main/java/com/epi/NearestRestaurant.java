package com.epi;

import java.util.ArrayList;
import java.util.List;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
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
  public static <T extends Comparable<T>> List<BinaryTree<T>> rangeQueryOnBST(
      BinaryTree<T> n, T L, T U) {
    ArrayList<BinaryTree<T>> res = new ArrayList<BinaryTree<T>>();
    for (BinaryTree<T> it = findFirstLargerEqualK(n, L); it != null
        && it.getData().compareTo(U) <= 0; it = findSuccessorBST(it)) {
      res.add(it);
    }
    return res;
  }

  private static <T extends Comparable<T>> BinaryTree<T> findFirstLargerEqualK(
      BinaryTree<T> r, T k) {
    if (r == null) {
      return null;
    } else if (r.getData().compareTo(k) >= 0) {
      // Recursively search the left subtree for first one >= k.
      BinaryTree<T> n = findFirstLargerEqualK(r.getLeft(), k);
      return n != null ? n : r;
    }
    // r->data < k so search the right subtree.
    return findFirstLargerEqualK(r.getRight(), k);
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3, null, null);
    root.setLeft(new BinaryTree<Integer>(2, null, null));
    root.getLeft().setParent(root);
    root.getLeft().setLeft(new BinaryTree<Integer>(1, null, null));
    root.getLeft().getLeft().setParent(root.getLeft());
    root.setRight(new BinaryTree<Integer>(5, null, null));
    root.getRight().setParent(root);
    root.getRight().setLeft(new BinaryTree<Integer>(4, null, null));
    root.getRight().getLeft().setParent(root.getRight());
    root.getRight().setRight(new BinaryTree<Integer>(6, null, null));
    root.getRight().getRight().setParent(root.getRight());
    List<BinaryTree<Integer>> res = rangeQueryOnBST(root, 2, 5);
    assert (res.size() == 4);
    for (BinaryTree<Integer> l : res) {
      assert (l.getData() >= 2 && l.getData() <= 5);
    }
    res = rangeQueryOnBST(root, -1, 0);
    assert (res.isEmpty());
    res = rangeQueryOnBST(root, 10, 25);
    assert (res.isEmpty());
    res = rangeQueryOnBST(root, -10, 30);
    assert (res.size() == 6);
    for (BinaryTree<Integer> l : res) {
      assert (l.getData() >= 1 && l.getData() <= 6);
    }
  }
}
