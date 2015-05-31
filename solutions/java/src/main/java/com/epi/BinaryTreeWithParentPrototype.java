package com.epi;

public class BinaryTreeWithParentPrototype {
  public static class BinaryTree<T> {
    private T data;
    private BinaryTree<T> left, right;
    private BinaryTree<T> parent;

    public BinaryTree() {}

    public BinaryTree(T data) { this.data = data; }

    public BinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public BinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right,
                      BinaryTree<T> parent) {
      this(data, left, right);
      this.parent = parent;
    }

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }

    public BinaryTree<T> getLeft() { return left; }

    public void setLeft(BinaryTree<T> left) { this.left = left; }

    public BinaryTree<T> getRight() { return right; }

    public void setRight(BinaryTree<T> right) { this.right = right; }

    public BinaryTree<T> getParent() { return parent; }

    public void setParent(BinaryTree<T> parent) { this.parent = parent; }
  }
}
