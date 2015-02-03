package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class RebuildBSTPreorder {
  // @include
  public static <T extends Comparable<T>> BinaryTree<T> rebuildBSTFromPreorder(
      List<T> preorderSequence) {
    return rebuildBSTFromPreorderHelper(preorderSequence, 0, 
                                        preorderSequence.size());
  }

  // Builds a BST from preorderSequence[s : e - 1].
  private static <T extends Comparable<T>> BinaryTree<T>
  rebuildBSTFromPreorderHelper(List<T> preorderSequence, int s, int e) {
    if (s < e) {
      int transitionPoint = s + 1;
      while (transitionPoint < e && 
             preorderSequence.get(transitionPoint).compareTo(preorderSequence.get(s)) < 0) {
        ++transitionPoint;
      }
      return new BinaryTree<>(
          preorderSequence.get(s), 
          rebuildBSTFromPreorderHelper(preorderSequence, s + 1, transitionPoint),
          rebuildBSTFromPreorderHelper(preorderSequence, transitionPoint, e));
    }
    return null;
  }
  // @exclude

  private static <T extends Comparable<T>> void checkAns(BinaryTree<T> n, T pre) {
    if (n != null) {
      checkAns(n.getLeft(), pre);
      assert (pre.compareTo(n.getData()) <= 0);
      System.out.println(n.getData());
      checkAns(n.getRight(), n.getData());
    }
  }

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    // should output 1, 2, 3, 4, 5, 6
    // preorder [3, 2, 1, 5, 4, 6]
    List<Integer> preorder = new ArrayList<>();
    preorder.add(3);
    preorder.add(2);
    preorder.add(1);
    preorder.add(5);
    preorder.add(4);
    preorder.add(6);
    BinaryTree<Integer> root = rebuildBSTFromPreorder(preorder);
    checkAns(root, Integer.MIN_VALUE);
  }
}
