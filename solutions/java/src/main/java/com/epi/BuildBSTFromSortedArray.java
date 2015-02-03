package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.Random;

public class BuildBSTFromSortedArray {
  // @include
  public static BinaryTree<Integer> buildMinHeightBSTFromSortedArray(int[] A) {
    return buildMinHeightBSTFromSortedArrayHelper(A, 0, A.length);
  }

  // Build min-height BST over the entries in A[start : end - 1].
  private static BinaryTree<Integer> buildMinHeightBSTFromSortedArrayHelper(
      int[] A, int start, int end) {
    if (start >= end) {
      return null;
    }
    int mid = start + ((end - start) / 2);
    return new BinaryTree<>(A[mid],
        buildMinHeightBSTFromSortedArrayHelper(A, start, mid),
        buildMinHeightBSTFromSortedArrayHelper(A, mid + 1, end));
  }
  // @exclude

  private static int traversalCheck(BinaryTree<Integer> root, Integer target) {
    if (root != null) {
      target = traversalCheck(root.getLeft(), target);
      assert (target.equals(root.getData()));
      ++target;
      target = traversalCheck(root.getRight(), target);
    }
    return target;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = i;
      }
      BinaryTree<Integer> root = buildMinHeightBSTFromSortedArray(A);
      int target = 0;
      traversalCheck(root, target);
    }
  }
}
