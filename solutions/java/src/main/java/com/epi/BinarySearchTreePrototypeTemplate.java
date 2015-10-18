package com.epi;

import java.util.Objects;

public class BinarySearchTreePrototypeTemplate {
  // @include
  public static class BSTNode<T> {
    public T data;
    public BSTNode<T> left, right;
    // @exclude

    public BSTNode() {}

    public BSTNode(T data) { this.data = data; }

    public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
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

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(data, left, right); }
    // clang-format on
  }
  // @include
}
// @exclude
