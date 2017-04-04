package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuildBSTFromSortedArray {
  // @include
  public static BSTNode<Integer> buildMinHeightBSTFromSortedArray(
      List<Integer> A) {
    return buildMinHeightBSTFromSortedArrayHelper(A, 0, A.size());
  }

  // Build a min-height BST over the entries in A.subList(start, end - 1).
  private static BSTNode<Integer> buildMinHeightBSTFromSortedArrayHelper(
      List<Integer> A, int start, int end) {
    if (start >= end) {
      return null;
    }
    int mid = start + ((end - start) / 2);
    return new BSTNode<>(
        A.get(mid), buildMinHeightBSTFromSortedArrayHelper(A, start, mid),
        buildMinHeightBSTFromSortedArrayHelper(A, mid + 1, end));
  }
  // @exclude

  private static int traversalCheck(BSTNode<Integer> tree, Integer target) {
    if (tree != null) {
      target = traversalCheck(tree.left, target);
      assert(target.equals(tree.data));
      ++target;
      target = traversalCheck(tree.right, target);
    }
    return target;
  }

  private static void simpleTest() {
    BSTNode<Integer> result
        = buildMinHeightBSTFromSortedArray(Arrays.asList(1, 2, 3, 4));
    assert(3 == result.data);
    assert(2 == result.left.data);
    assert(1 == result.left.left.data);
    assert(4 == result.right.data);
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      List<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add(i);
      }
      BSTNode<Integer> tree = buildMinHeightBSTFromSortedArray(A);
      int target = 0;
      traversalCheck(tree, target);
    }
  }
}
