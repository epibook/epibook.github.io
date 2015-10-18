package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

public class BSTToSortedDoublyList {
  // @include
  // Transform a BST into a circular sorted doubly linked list in-place,
  // return the head of the list.
  public static BSTNode<Integer> bstToDoublyLinkedList(BSTNode<Integer> tree) {
    // Empty subtree.
    if (tree == null) {
      return null;
    }

    // Recursively build the list from left and right subtrees.
    BSTNode<Integer> lHead = bstToDoublyLinkedList(tree.left);
    BSTNode<Integer> rHead = bstToDoublyLinkedList(tree.right);

    // Append tree to the list from left subtree.
    BSTNode<Integer> lTail = null;
    if (lHead != null) {
      lTail = lHead.left;
      lTail.right = tree;
      tree.left = lTail;
      lTail = tree;
    } else {
      lHead = lTail = tree;
    }

    // Append the list from right subtree to tree.
    BSTNode<Integer> rTail = null;
    if (rHead != null) {
      rTail = rHead.left;
      lTail.right = rHead;
      rHead.left = lTail;
    } else {
      rTail = lTail;
    }
    rTail.right = lHead;
    lHead.left = rTail;

    return lHead;
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
    } while (curr != L);
  }
}
