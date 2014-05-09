package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinaryTreePrototypeTemplate {
  // @include
  public static class BinaryTree<T> {
    private T data;
    private BinaryTree<T> left, right;
    // @exclude

    public BinaryTree() {
    }

    public BinaryTree(T data) {
      this.data = data;
    }

    public BinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
      this.data = data;
      this.left = left;
      this.right = right;
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

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      BinaryTree that = (BinaryTree) o;

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

