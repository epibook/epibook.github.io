package com.epi;

public class BinarySearchTreePrototypeTemplate {
  // @include
  public static class BSTNode<T> {
    private T data;
    private BSTNode<T> left, right;
    // @exclude

    public BSTNode() {}

    public BSTNode(T data) { this.data = data; }

    public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }

    public BSTNode<T> getLeft() { return left; }

    public void setLeft(BSTNode<T> left) { this.left = left; }

    public BSTNode<T> getRight() { return right; }

    public void setRight(BSTNode<T> right) { this.right = right; }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      BSTNode that = (BSTNode)o;

      if (data != null ? !data.equals(that.data) : that.data != null) {
        return false;
      }
      if (left != null ? !left.equals(that.left) : that.left != null) {
        return false;
      }
      if (right != null ? !right.equals(that.right) : that.right != null) {
        return false;
      }

      return true;
    }
  }
  // @include
}
// @exclude
