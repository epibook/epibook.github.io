package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class PathSumBinaryTree {
  // @include
  public static boolean hasPathSum(BinaryTree<Integer> root, int sum) {
    return hasPathSumHelper(root, 0, sum);
  }

  private static boolean hasPathSumHelper(BinaryTree<Integer> root,
      int pathsum, int sum) {
    if (root != null) {
      pathsum += root.getData();
      if (root.getLeft() == null && root.getRight() == null) { // leaf.
        return pathsum == sum;
      }
      // Non-leaf.
      return hasPathSumHelper(root.getLeft(), pathsum, sum)
          || hasPathSumHelper(root.getRight(), pathsum, sum);
    }
    return false;
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
    assert (hasPathSum(root, 6));
    assert (!hasPathSum(root, 7));
    assert (!hasPathSum(root, 100));
  }
}
