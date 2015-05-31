package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.Random;

public class BuildBSTFromSortedArray {
  // @include
  public static BSTNode<Integer> buildMinHeightBSTFromSortedArray(int[] A) {
    return buildMinHeightBSTFromSortedArrayHelper(A, 0, A.length);
  }

  // Build a min-height BST over the entries in A[start : end - 1].
  private static BSTNode<Integer> buildMinHeightBSTFromSortedArrayHelper(
      int[] A, int start, int end) {
    if (start >= end) {
      return null;
    }
    int mid = start + ((end - start) / 2);
    return new BSTNode<>(A[mid],
                         buildMinHeightBSTFromSortedArrayHelper(A, start, mid),
                         buildMinHeightBSTFromSortedArrayHelper(A, mid + 1, end));
  }
  // @exclude

  private static int traversalCheck(BSTNode<Integer> tree, Integer target) {
    if (tree != null) {
      target = traversalCheck(tree.getLeft(), target);
      assert(target.equals(tree.getData()));
      ++target;
      target = traversalCheck(tree.getRight(), target);
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
      BSTNode<Integer> tree = buildMinHeightBSTFromSortedArray(A);
      int target = 0;
      traversalCheck(tree, target);
    }
  }
}
