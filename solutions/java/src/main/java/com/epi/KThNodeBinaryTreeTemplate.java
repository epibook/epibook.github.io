package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class KThNodeBinaryTreeTemplate {
  public static class BinaryTree<T> {
    private T data;
    private BinaryTree<T> left, right;
    private int size;

    public BinaryTree(T data, int size) {
      this.data = data;
      this.size = size;
    }

    public T getData() {
      return data;
    }

    public void setData(T data) {
      this.data = data;
    }

    public BinaryTree<T> getLeft() {
      return left;
    }

    public void setLeft(BinaryTree<T> left) {
      this.left = left;
    }

    public BinaryTree<T> getRight() {
      return right;
    }

    public void setRight(BinaryTree<T> right) {
      this.right = right;
    }

    public int getSize() {
      return size;
    }

    public void setSize(int size) {
      this.size = size;
    }
  }

  // @include
  public static <T> BinaryTree<T> findKthNodeBinaryTree(BinaryTree<T> root,
      int k) {
    BinaryTree<T> n = root;
    while (n != null) {
      int leftSize = n.getLeft() != null ? n.getLeft().getSize() : 0;
      if (leftSize < k - 1) {
        k -= (leftSize + 1);
        n = n.getRight();
      } else if (leftSize == k - 1) {
        return n;
      } else { // leftSize > k - 1.
        n = n.getLeft();
      }
    }
    throw new RuntimeException("no k-th node in binary tree");
  }

  // @exclude

  public static void main(String[] args) {
    // size field
    // 6
    // 2 3
    // 1 1 1
    //
    // data field
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<Integer>(3, 6);
    root.setLeft(new BinaryTree<Integer>(2, 2));
    root.getLeft().setLeft(new BinaryTree<Integer>(1, 1));
    root.setRight(new BinaryTree<Integer>(5, 3));
    root.getRight().setLeft(new BinaryTree<Integer>(4, 1));
    root.getRight().setRight(new BinaryTree<Integer>(6, 1));
    // should throw
    try {
      findKthNodeBinaryTree(root, 0);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    // should output 1
    assert (findKthNodeBinaryTree(root, 1).getData().equals(1));
    System.out.println(findKthNodeBinaryTree(root, 1).getData());
    // should output 2
    assert (findKthNodeBinaryTree(root, 2).getData().equals(2));
    System.out.println(findKthNodeBinaryTree(root, 2).getData());
    // should output 3
    assert (findKthNodeBinaryTree(root, 3).getData().equals(3));
    System.out.println(findKthNodeBinaryTree(root, 3).getData());
    // should output 4
    assert (findKthNodeBinaryTree(root, 4).getData().equals(4));
    System.out.println(findKthNodeBinaryTree(root, 4).getData());
    // should output 5
    assert (findKthNodeBinaryTree(root, 5).getData().equals(5));
    System.out.println(findKthNodeBinaryTree(root, 5).getData());
    // should output 6
    assert (findKthNodeBinaryTree(root, 6).getData().equals(6));
    System.out.println(findKthNodeBinaryTree(root, 6).getData());
    // should throw
    try {
      findKthNodeBinaryTree(root, 7);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }
}
