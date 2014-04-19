package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
// @include
public class BinarySearchTree<T> {
  private T data;
  private BinarySearchTree<T> left, right;

  public BinarySearchTree(T data) {
    this.data = data;
  }

  public BinarySearchTree<T> getLeft() {
    return left;
  }

  public void setLeft(BinarySearchTree<T> left) {
    this.left = left;
  }

  public BinarySearchTree<T> getRight() {
    return right;
  }

  public void setRight(BinarySearchTree<T> right) {
    this.right = right;
  }

  public T getData() {
    return data;
  }
}
// @exclude
