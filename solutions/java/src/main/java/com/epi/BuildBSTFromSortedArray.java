package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BuildBSTFromSortedArray {
  // @include
  public static BinaryTree<Integer> buildBSTFromSortedArray(List<Integer> a) {
    return buildBSTFromSortedArrayHelper(a, 0, a.size());
  }

  // Build BST based on subarray A[start : end - 1].
  private static BinaryTree<Integer> buildBSTFromSortedArrayHelper(
      List<Integer> a, int start, int end) {
    if (start < end) {
      int mid = start + ((end - start) / 2);
      return new BinaryTree<>(a.get(mid),
          buildBSTFromSortedArrayHelper(a, start, mid),
          buildBSTFromSortedArrayHelper(a, mid + 1, end));
    }
    return null;
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
      List<Integer> a = new ArrayList<>();
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      for (int i = 0; i < n; ++i) {
        a.add(i);
      }
      BinaryTree<Integer> root = buildBSTFromSortedArray(a);
      int target = 0;
      traversalCheck(root, target);
    }
  }
}
