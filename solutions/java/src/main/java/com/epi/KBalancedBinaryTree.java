package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;
import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class KBalancedBinaryTree {
  // @include
  public static BinaryTree<Integer> findKUnBalancedNode(BinaryTree<Integer> T,
                                                        int k) {
    return findKUnBalancedNodeHelper(T, k).getFirst();
  }

  private static Pair<BinaryTree<Integer>, Integer> findKUnBalancedNodeHelper(
      BinaryTree<Integer> T, int k) {
    // Empty tree.
    if (T == null) {
      return new Pair<>(null, 0);
    }

    // Early return if left subtree is not k-balanced.
    Pair<BinaryTree<Integer>, Integer> L =
        findKUnBalancedNodeHelper(T.getLeft(), k);
    if (L.getFirst() != null) {
      return L;
    }
    // Early return if right subtree is not k-balanced.
    Pair<BinaryTree<Integer>, Integer> R =
        findKUnBalancedNodeHelper(T.getRight(), k);
    if (R.getFirst() != null) {
      return R;
    }

    int nodeNum = L.getSecond() + R.getSecond() + 1;
    if (Math.abs(L.getSecond() - R.getSecond()) > k) {
      return new Pair<>(T, nodeNum);
    }
    return new Pair<>(null, nodeNum);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
    int k = 0;
    BinaryTree<Integer> ans = findKUnBalancedNode(root, k);
    assert (ans.getData().equals(2));
    System.out.println(ans.getData());
  }
}
