package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.Random;

public class BuildBSTFromSortedArray {
  // @include
  public static BinaryTree<Integer> buildBSTFromSortedArray(int[] A) {
    return buildBSTFromSortedArrayHelper(A, 0, A.length);
  }

  // Build BST based on subarray A[start : end - 1].
  private static BinaryTree<Integer> buildBSTFromSortedArrayHelper(
      int[] A, int start, int end) {
    if (start >= end) {
      return null;
    }
    int mid = start + ((end - start) / 2);
    return new BinaryTree<>(A[mid],
        buildBSTFromSortedArrayHelper(A, start, mid),
        buildBSTFromSortedArrayHelper(A, mid + 1, end));
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
      BinaryTree<Integer> root = buildBSTFromSortedArray(A);
      int target = 0;
      traversalCheck(root, target);
    }
  }
}
