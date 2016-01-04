package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class NearestRestaurant {
  private static <T> BinaryTree<T> findSuccessorBST(BinaryTree<T> n) {
    if (n.right != null) {
      // Find the smallest element in n's right subtree.
      n = n.right;
      while (n.left != null) {
        n = n.left;
      }
      return n;
    }

    // Find the first parent which is larger than n.
    while (n.parent != null && n.parent.right == n) {
      n = n.parent;
    }
    // Return nullptr means n is the largest in this BST.
    return n.parent != null ? n.parent : null;
  }

  // @include
  public static List<BinaryTree<Integer>> rangeQueryOnBST(BinaryTree<Integer> n,
                                                          Integer L,
                                                          Integer U) {
    List<BinaryTree<Integer>> res = new ArrayList<>();
    for (BinaryTree<Integer> it = findFirstLargerEqualK(n, L);
         it != null && Integer.compare(it.data, U) <= 0;
         it = findSuccessorBST(it)) {
      res.add(it);
    }
    return res;
  }

  private static BinaryTree<Integer> findFirstLargerEqualK(
      BinaryTree<Integer> r, Integer k) {
    if (r == null) {
      return null;
    } else if (Integer.compare(r.data, k) < 0) {
      return findFirstLargerEqualK(r.right, k);
    }
    // Recursively search the left subtree for first one >= k.
    BinaryTree<Integer> n = findFirstLargerEqualK(r.left, k);
    return n != null ? n : r;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.left = new BinaryTree<>(2, null, null);
    root.left.parent = root;
    root.left.left = new BinaryTree<>(1, null, null);
    root.left.left.parent = root.left;
    root.right = new BinaryTree<>(5, null, null);
    root.right.parent = root;
    root.right.left = new BinaryTree<>(4, null, null);
    root.right.left.parent = root.right;
    root.right.right = new BinaryTree<>(6, null, null);
    root.right.right.parent = root.right;
    List<BinaryTree<Integer>> res = rangeQueryOnBST(root, 2, 5);
    assert(res.size() == 4);
    for (BinaryTree<Integer> l : res) {
      assert(l.data >= 2 && l.data <= 5);
    }
    res = rangeQueryOnBST(root, -1, 0);
    assert(res.isEmpty());
    res = rangeQueryOnBST(root, 10, 25);
    assert(res.isEmpty());
    res = rangeQueryOnBST(root, -10, 30);
    assert(res.size() == 6);
    for (BinaryTree<Integer> l : res) {
      assert(l.data >= 1 && l.data <= 6);
    }
  }
}
