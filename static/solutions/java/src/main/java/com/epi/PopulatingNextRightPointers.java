package com.epi;

public class PopulatingNextRightPointers {
  public static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public BinaryTreeNode<T> next; // Populates this field.

    public BinaryTreeNode(T data) { this.data = data; }
  }

  // @include
  public static void constructRightSibling(BinaryTreeNode<Integer> tree) {
    BinaryTreeNode<Integer> leftStart = tree;
    while (leftStart != null && leftStart.left != null) {
      populateLowerLevelNextField(leftStart);
      leftStart = leftStart.left;
    }
  }

  private static void populateLowerLevelNextField(
      BinaryTreeNode<Integer> startNode) {
    BinaryTreeNode<Integer> iter = startNode;
    while (iter != null) {
      // Populate left child's next field.
      iter.left.next = iter.right;
      // Populate right child's next field if iter is not the last node of this
      // level.
      if (iter.next != null) {
        iter.right.next = iter.next.left;
      }
      iter = iter.next;
    }
  }
  // @exclude

  private static void simpleTest() {
    //     3
    //  2     5
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3);
    root.left = new BinaryTreeNode<>(2);
    root.right = new BinaryTreeNode<>(5);
    constructRightSibling(root);
    assert(root.next == null);
    assert(root.left.next == root.right);
    assert(root.right.next == null);
  }

  public static void main(String[] args) {
    simpleTest();
    //     3
    //  2     5
    // 1 7   4 6
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3);
    root.left = new BinaryTreeNode<>(2);
    root.left.right = new BinaryTreeNode<>(7);
    root.left.left = new BinaryTreeNode<>(1);
    root.right = new BinaryTreeNode<>(5);
    root.right.left = new BinaryTreeNode<>(4);
    root.right.right = new BinaryTreeNode<>(6);
    constructRightSibling(root);
    assert(root.next == null);
    assert(root.left.next == root.right);
    assert(root.left.left.next == root.left.right);
    assert(root.left.right.next == root.right.left);
    assert(root.right.left.next == root.right.right);
  }
}
