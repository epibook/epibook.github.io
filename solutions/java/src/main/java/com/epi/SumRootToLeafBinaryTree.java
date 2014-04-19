package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class SumRootToLeafBinaryTree {
  // @include
  public static int sumRootToLeaf(BinaryTree<Integer> root) {
    return inOrderTraversal(root, 0, 0);
  }

  public static int inOrderTraversal(BinaryTree<Integer> root, int num, int sum) {
    if (root != null) {
      num = (num << 1) + root.getData();
      if (root.getLeft() == null && root.getRight() == null) { // leaf.
        sum += num;
      } else { // non-leaf.
        sum = inOrderTraversal(root.getLeft(), num, sum);
        sum = inOrderTraversal(root.getRight(), num, sum);
      }
    }
    return sum;
  }

  // @exclude

  public static void main(String[] args) {
    // 1
    // 1 0
    // 0 1 0
    BinaryTree<Integer> root = new BinaryTree<Integer>(1);
    root.setLeft(new BinaryTree<Integer>(1));
    root.getLeft().setLeft(new BinaryTree<Integer>(0));
    root.setRight(new BinaryTree<Integer>(0));
    root.getRight().setLeft(new BinaryTree<Integer>(1));
    root.getRight().setRight(new BinaryTree<Integer>(0));
    int res = sumRootToLeaf(root);
    assert (res == 15);
  }
}
