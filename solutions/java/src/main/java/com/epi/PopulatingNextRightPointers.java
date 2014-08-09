package com.epi;

public class PopulatingNextRightPointers {
  public static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public BinaryTreeNode<T> next; // Populates this field.

    public BinaryTreeNode(T data) {
      this.data = data;
    }
  }

  // @include
  public static void populateNextPointer(BinaryTreeNode<Integer> root) {
    BinaryTreeNode<Integer> leftStart = root;
    while (leftStart != null) {
      BinaryTreeNode<Integer> parent = leftStart;
      while (parent != null) {
        if (parent.left != null) {
          parent.left.next = parent.right;
        }
        if (parent.right != null && parent.next != null) {
          parent.right.next = parent.next.left;
        }
        parent = parent.next;
      }
      leftStart = leftStart.left;
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 7 4 6
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3);
    root.left = new BinaryTreeNode<>(2);
    root.left.right = new BinaryTreeNode<>(7);
    root.left.left = new BinaryTreeNode<>(1);
    root.right = new BinaryTreeNode<>(5);
    root.right.left = new BinaryTreeNode<>(4);
    root.right.right = new BinaryTreeNode<>(6);
    populateNextPointer(root);
    assert (root.next == null);
    assert (root.left.next == root.right);
    assert (root.left.left.next == root.left.right);
    assert (root.left.right.next == root.right.left);
    assert (root.right.left.next == root.right.right);
  }
}
