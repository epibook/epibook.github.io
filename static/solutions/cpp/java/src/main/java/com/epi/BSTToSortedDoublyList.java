package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class BSTToSortedDoublyList {
  // @include
  private static class HeadAndTail {
    public BSTNode<Integer> head;
    public BSTNode<Integer> tail;

    public HeadAndTail(BSTNode<Integer> head, BSTNode<Integer> tail) {
      this.head = head;
      this.tail = tail;
    }
  }

  public static BSTNode<Integer> bstToDoublyLinkedList(BSTNode<Integer> tree) {
    return bstToDoublyLinkedListHelper(tree).head;
  }

  // Transforms a BST into a sorted doubly linked list in-place, and return the
  // head and tail of the list.
  private static HeadAndTail bstToDoublyLinkedListHelper(
      BSTNode<Integer> tree) {
    // Empty subtree.
    if (tree == null) {
      return new HeadAndTail(null, null);
    }

    // Recursively build the list from left and right subtrees.
    HeadAndTail left = bstToDoublyLinkedListHelper(tree.left);
    HeadAndTail right = bstToDoublyLinkedListHelper(tree.right);

    // Append tree to the list from left subtree.
    if (left.tail != null) {
      left.tail.right = tree;
    }
    tree.left = left.tail;

    // Append the list from right subtree to tree.
    tree.right = right.head;
    if (right.head != null) {
      right.head.left = tree;
    }

    return new HeadAndTail(left.head != null ? left.head : tree,
                           right.tail != null ? right.tail : tree);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BSTNode<Integer> tree = new BSTNode<>(3);
    tree.left = new BSTNode<>(2);
    tree.left.left = new BSTNode<>(1);
    tree.right = new BSTNode<>(5);
    tree.right.left = new BSTNode<>(4);
    tree.right.right = new BSTNode<>(6);
    BSTNode<Integer> L = bstToDoublyLinkedList(tree);
    BSTNode<Integer> curr = L;
    int pre = Integer.MIN_VALUE;
    do {
      assert(pre <= curr.data);
      System.out.println(curr.data);
      pre = curr.data;
      curr = curr.right;
    } while (curr != null);
  }
}
