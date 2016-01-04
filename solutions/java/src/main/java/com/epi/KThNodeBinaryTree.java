package com.epi;

public class KThNodeBinaryTree {
  public static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public int size;
  }

  // @include
  public static BinaryTreeNode<Integer> findKthNodeBinaryTree(
      BinaryTreeNode<Integer> tree, int k) {
    BinaryTreeNode<Integer> iter = tree;
    while (iter != null) {
      int leftSize = iter.left != null ? iter.left.size : 0;
      if (leftSize + 1 < k) { // k-th node must be in right subtree of iter.
        k -= (leftSize + 1);
        iter = iter.right;
      } else if (leftSize == k - 1) { // k-th is iter itself.
        return iter;
      } else { // k-th node must be in left subtree of iter.
        iter = iter.left;
      }
    }
    // If k is between 1 and the tree size, this line is unreachable.
    return null;
  }
  // @exclude

  public static void main(String[] args) {
    // size field
    //   6
    //  2  3
    // 1  1 1
    //
    // data field
    //   3
    //  2  5
    // 1  4 6
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
    root.size = 6;
    root.data = 3;
    root.left = new BinaryTreeNode<>();
    root.left.size = 2;
    root.left.data = 2;
    root.left.left = new BinaryTreeNode<>();
    root.left.left.size = 1;
    root.left.left.data = 1;
    root.right = new BinaryTreeNode<>();
    root.right.size = 3;
    root.right.data = 5;
    root.right.left = new BinaryTreeNode<>();
    root.right.left.size = 1;
    root.right.left.data = 4;
    root.right.right = new BinaryTreeNode<>();
    root.right.right.size = 1;
    root.right.right.data = 6;
    // 0th node does not exist - leftmost node is node 1
    assert(null == findKthNodeBinaryTree(root, 0));
    // should output 1
    assert(findKthNodeBinaryTree(root, 1).data == 1);
    System.out.println((findKthNodeBinaryTree(root, 1)).data);
    // should output 2
    assert(findKthNodeBinaryTree(root, 2).data == 2);
    System.out.println((findKthNodeBinaryTree(root, 2)).data);
    // should output 3
    assert(findKthNodeBinaryTree(root, 3).data == 3);
    System.out.println((findKthNodeBinaryTree(root, 3)).data);
    // should output 4
    assert(findKthNodeBinaryTree(root, 4).data == 4);
    System.out.println((findKthNodeBinaryTree(root, 4)).data);
    // should output 5
    assert(findKthNodeBinaryTree(root, 5).data == 5);
    System.out.println((findKthNodeBinaryTree(root, 5)).data);
    // should output 6
    assert(findKthNodeBinaryTree(root, 6).data == 6);
    System.out.println((findKthNodeBinaryTree(root, 6)).data);
    // should throw
    assert(null == findKthNodeBinaryTree(root, 7));
  }
}
