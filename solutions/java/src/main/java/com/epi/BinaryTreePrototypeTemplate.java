package com.epi;

import java.util.Objects;

public class BinaryTreePrototypeTemplate {
  // @include
  public static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
    // @exclude

    public BinaryTreeNode() {}

    public BinaryTreeNode(T data) { this.data = data; }

    public BinaryTreeNode(T data, BinaryTreeNode<T> left,
                          BinaryTreeNode<T> right) {
      this.data = data;
      this.left = left;
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

      BinaryTreeNode that = (BinaryTreeNode)o;
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

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(data, left, right); }
    // clang-format on
    // @include
  }
  // @exclude
}
