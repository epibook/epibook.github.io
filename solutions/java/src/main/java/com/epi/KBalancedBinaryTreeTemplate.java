package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;
import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class KBalancedBinaryTreeTemplate {
  // @include
  private static <T> Pair<BinaryTree<T>, Integer> findNonKBalancedNodeHelper(
      BinaryTree<T> n, int k) {
    // Empty tree.
    if (n == null) {
      return new Pair<BinaryTree<T>, Integer>(null, 0);
    }

    // Early return if left subtree is not k-balanced.
    Pair<BinaryTree<T>, Integer> L = findNonKBalancedNodeHelper(n.getLeft(), k);
    if (L.getFirst() != null) {
      return L;
    }
    // Early return if right subtree is not k-balanced.
    Pair<BinaryTree<T>, Integer> R = findNonKBalancedNodeHelper(n.getRight(), k);
    if (R.getFirst() != null) {
      return R;
    }

    int nodeNum = L.getSecond() + R.getSecond() + 1; // # of nodes in n.
    if (Math.abs(L.getSecond() - R.getSecond()) > k) {
      return new Pair<BinaryTree<T>, Integer>(n, nodeNum);
    }
    return new Pair<BinaryTree<T>, Integer>(null, nodeNum);
  }

  private static <T> BinaryTree<T> findNonKBalancedNode(BinaryTree<T> n, int k) {
    return findNonKBalancedNodeHelper(n, k).getFirst();
  }

  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setLeft(new BinaryTree<Integer>(1));
    root.setRight(new BinaryTree<Integer>(5));
    root.getRight().setLeft(new BinaryTree<Integer>(4));
    root.getRight().setRight(new BinaryTree<Integer>(6));
    int k = 0;
    BinaryTree<Integer> ans = findNonKBalancedNode(root, k);
    assert (ans.getData().equals(2));
    System.out.println(ans.getData());
  }
}
